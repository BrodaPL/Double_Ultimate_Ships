package mygdx.tempStuff.screens

import assets.tempStuff.images.ImagesResources
import com.badlogic.gdx.*
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.*
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
import com.badlogic.gdx.scenes.scene2d.utils.DragListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import mygdx.tempStuff.other.MuzykalnaNuta

class GameScreenGuiTest(private val game: Game, private val skin: Skin) : Screen {

    private var gameStage: Stage
    private var guiStage: Stage
    private var camera: OrthographicCamera

    private var slider: Slider
    private var multiplexer: InputMultiplexer
    private var imageResources: ImagesResources

//    private var shockWaveShader: ShockWave

    init {
        imageResources = ImagesResources()

        gameStage = Stage(ScreenViewport())
        guiStage = Stage(ScreenViewport())
        slider = createGuiSlider()

        multiplexer = InputMultiplexer(guiStage, gameStage)

        camera = gameStage.viewport.camera as OrthographicCamera
        camera.translate(200f, 250f)
        camera.zoom = 1.5f

        gameStage.addActor(createPhotoRealisticSea()) // comment if testing ShockWave shader
        gameStage.addActor(createRing())
        gameStage.addActor(createMusicalPlanet())
        guiStage.addActor(createGuiMagnifier())
        guiStage.addActor(slider)
        guiStage.addActor(createBackToTitleButton())

        // TODO: shader not working properly
//        gameStage.addActor(ShockWave.instance)
//        ShockWave.instance.addActor(createPhotoRealisticSea())

        gameStage.addListener(object : InputListener() {
            override fun scrolled(event: InputEvent?, x: Float, y: Float, amount: Int): Boolean {
                slider.setValue(slider.value + (amount*-1*0.1f))
                Gdx.app.log("scrolled", "amount:" + amount)
                return super.scrolled(event, x, y, amount)
            }
        })
    }

    override fun show() {
        Gdx.app.log(this.javaClass.name, "show()")
        Gdx.input.setInputProcessor(multiplexer)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.zoom = slider.value
        camera.update()

        guiStage.act()
        gameStage.act()

        gameStage.draw()
        guiStage.draw()
    }

    override fun hide() {
        Gdx.app.log(this.javaClass.name, object {}.javaClass.enclosingMethod.name + "() not implemented")
    }

    override fun pause() {
        Gdx.app.log(this.javaClass.name, object {}.javaClass.enclosingMethod.name + "() not implemented")
    }

    override fun resume() {
        Gdx.app.log(this.javaClass.name, object {}.javaClass.enclosingMethod.name + "() not implemented")
    }

    override fun resize(width: Int, height: Int) {
        Gdx.app.log(this.javaClass.name, object {}.javaClass.enclosingMethod.name + "() not implemented")
    }

    override fun dispose() {
        Gdx.app.log(this.javaClass.name, "dispose()")
        guiStage.dispose()
        gameStage.dispose()
    }

    private fun createPhotoRealisticSea(): Image {
        var photoRealisticSea = Image(Texture(imageResources.temp_map__to_replaceJPG()))
//        ShockWave.instance.addActor(photoRealisticSea)
        photoRealisticSea.addListener(object : ActorGestureListener() {
            override fun pan(event: InputEvent?, x: Float, y: Float, deltaX: Float, deltaY: Float) {
                super.pan(event, x, y, deltaX, deltaY)
                camera.position.x -= (deltaX*Gdx.graphics.density)
                camera.position.y -= (deltaY*Gdx.graphics.density)
            }

            // TODO: shader not working properly
//            override fun tap(event: InputEvent?, x: Float, y: Float, count: Int, button: Int) {
//                ShockWave.instance.start(x,y)
//            }
        })

        return photoRealisticSea
    }

    private fun createRing(): Image {
        var img = Image(Texture(imageResources.ringPNG()))
        img.setPosition(900f, 900f)

        img.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                img.setSize((button + 0.5f)*img.imageWidth, (button + 0.5f)*img.imageHeight)
                Gdx.app.log("touchDown", "val:" + pointer + " " + button)
                return super.touchDown(event, x, y, pointer, button)
            }

            // TODO WHY NOT WORKING?!
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                img.setSize((button + 0.5f)*img.imageWidth, (button + 0.5f)*img.imageHeight)
                Gdx.app.log("touchUp", "val:" + pointer + " " + button)
                super.touchUp(event, x, y, pointer, button)
            }
        })

        img.addListener(object : DragListener() {
            override fun drag(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                img.moveBy(x - img.getWidth() / 2, y - img.getHeight() / 2)
                Gdx.app.log("drag", "draging:" + pointer)
                super.drag(event, x, y, pointer)
            }
        })

        return img
    }

    private fun createMusicalPlanet(): Image {
        var img = MuzykalnaNuta(Texture(imageResources.space.uranusPNG()))
        img.setSize(img.width / 2f, img.width / 2f)
        img.setPosition(1500f, 100f)
        img.addAction(Actions.repeat(-1, Actions.sequence(Actions.moveTo(Gdx.graphics.width*3 / 5f, Gdx.graphics.height*3 / 5f, 2f, Interpolation.sine),
                                                               Actions.moveTo(Gdx.graphics.width*3 / 5f, Gdx.graphics.height*1 / 5f, 2f, Interpolation.sine),
                                                                Actions.moveTo(1500f, 100f, 2f, Interpolation.sine))))

        return img
    }

    private fun createGuiMagnifier(): Image {
        var img = Image(Texture(imageResources.magnifierPNG()))
        img.setPosition(Gdx.graphics.getWidth() / 2 - img.getWidth() / 4, Gdx.graphics.getHeight() / 2 - img.getHeight() / 2)
        return img
    }

    private fun createGuiSlider(): Slider {
        var slider = Slider(1f, 2f, 0.01f, true, skin)
        slider.setAnimateInterpolation(Interpolation.smooth)
//        slider.setAnimateDuration(0.1f)
        slider.setHeight(Gdx.graphics.height*0.8f)
        slider.setPosition(Gdx.graphics.getWidth() / 12f, Gdx.graphics.getHeight() / 10f)
        slider.setValue(1.5f)
        slider.addListener(object : InputListener() {
            override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                Gdx.app.log("touchDragged", "slider Value:" + slider.getValue())
                super.touchDragged(event, x, y, pointer)
            }
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                Gdx.app.log("up", "slider Value:" + slider.getValue())
                super.touchUp(event, x, y, pointer, button)
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                Gdx.app.log("down", "slider Value:" + slider.value)
                return true
            }
        })

        return slider
    }

    private fun createBackToTitleButton(): TextButton {
        var btn = TextButton("Back to menu", skin)
        btn.width = (Gdx.graphics.width / 3).toFloat()
        btn.setPosition(Gdx.graphics.width / 2 - btn.width / 2, Gdx.graphics.height / 24 - btn.height / 2)
        btn.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.screen = TitleScreenOne(game, skin)
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return btn
    }
}