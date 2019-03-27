package mygdx.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import mygdx.testone.MyGdxScreenTest

/**
 * Created by julienvillegas on 17/01/2017.
 */
class GameScreenOne(private val game: Game, private val skin: Skin) : Screen {

    private var stage: Stage

    init {
        stage = Stage(ScreenViewport())

        stage.addActor(createTitleLabel())
        stage.addActor(createBackButton())
    }

    override fun show() {
        Gdx.app.log(this.javaClass.name, "show")
        Gdx.input.inputProcessor = stage

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
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