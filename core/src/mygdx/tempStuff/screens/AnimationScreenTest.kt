package mygdx.tempStuff.screens

import assets.tempStuff.images.ImagesResources
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import java.awt.Point
import java.time.Instant

/**
 * Created by julienvillegas on 17/01/2017.
 */
class AnimationScreenTest(private val game: Game, private val skin: Skin) : Screen {

    private var stage: Stage
    private var camera: OrthographicCamera

    internal val imagesRes = ImagesResources()

    private val startPoint = Point(800,800) // X -Gdx.graphics.getFieldWidth()/2;
    private val endPoint = Point(1600,400) // X -Gdx.graphics.getFieldWidth()/2;

    private val minAltitude = 0.5f
    private val maxAltitude = 2.5f

    private var percent = 0f
    private var counter = 0f
    private var startTime = 0L
    private val animationDuration = 10000

    init {
        val photoRealisticSea = Image(Texture(imagesRes.temp_map__to_replaceJPG()))

        stage = Stage(ScreenViewport())
        stage.addActor(photoRealisticSea)

        camera = stage.viewport.camera as OrthographicCamera
//        camera.translate(startPoint.x.toFloat(), startPoint.y.toFloat())
        camera.translate(Gdx.graphics.width / 2 - 128 / 2f, Gdx.graphics.height / 4 - 32 / 2f)
//

        stage.addActor(createTitleLabel())
        stage.addActor(createBackButton())

        startTime = Instant.now().toEpochMilli()
    }

    override fun show() {
        Gdx.app.log(this.javaClass.name, "show")
        Gdx.input.inputProcessor = stage

    }

    override fun render(delta: Float) {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(TitleScreenOne(game, skin))
        }

        val secondFromStart = Instant.now().toEpochMilli()-startTime
        percent = (secondFromStart%animationDuration)/animationDuration.toFloat()
        percent = Math.cos(percent*Math.PI*2).toFloat()  /2+0.5f

        Gdx.app.log("render","secondFromStart:"+ secondFromStart+", %:"+percent);
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        moveCamera()
        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {
        stage.dispose()
    }

    private fun moveCamera(){
        val currentX = startPoint.x + (endPoint.x-startPoint.x) *percent
        val currentY = startPoint.y + (endPoint.y-startPoint.y) *percent
        val percentZ = Math.abs(percent -0.5f)*2
        val currentZ = maxAltitude - (maxAltitude-minAltitude)*percentZ

        camera.position.x = currentX
        camera.position.y = currentY
        camera.zoom = currentZ
        camera.update()
    }

    private fun createTitleLabel(): Label{
        val titleLbl = Label("Playing Screen", skin, "default")
        titleLbl.setAlignment(Align.center)
        titleLbl.y = (Gdx.graphics.height * 2 / 3).toFloat()
        titleLbl.width = Gdx.graphics.width.toFloat()
        return titleLbl
    }

    private fun createBackButton() : TextButton{
        val backButton = TextButton("Back", skin)
        backButton.width = (Gdx.graphics.width / 2).toFloat()
        backButton.setPosition(Gdx.graphics.width / 2 - backButton.width / 2, Gdx.graphics.height / 4 - backButton.height / 2)
        backButton.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.screen = TitleScreenOne(game, skin)
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return backButton
    }
}