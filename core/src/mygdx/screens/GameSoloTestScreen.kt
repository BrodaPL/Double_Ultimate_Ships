package mygdx.screens

import assets.tempStuff.images.ImagesResources
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import mygdx.DoubleUltimateShips
import mygdx.GridCreator

class GameSoloTestScreen(private val game: DoubleUltimateShips) : Screen {

    private val FIELD_WIDTH = 64

    private var gameStage: Stage
    private var guiStage: Stage
    private var camera: OrthographicCamera
    private val skin: Skin

    private var slider: Slider
    private var multiplexer: InputMultiplexer
    private var imageResources: ImagesResources
    private var gridCreator: GridCreator

    private val grid1: GridCreator.Grid

    private var selectionBox: Image

    init {
        imageResources = ImagesResources()
        skin = game.gameSkin
        gridCreator = GridCreator(FIELD_WIDTH, Color.BLACK, BitmapFont(Gdx.files.internal(game.fonts.stencilFNT())))
        selectionBox = createSelectedBox()

        gameStage = Stage(ScreenViewport())
        guiStage = Stage(ScreenViewport())
        slider = createGuiSlider()

        multiplexer = InputMultiplexer(guiStage, gameStage)

        camera = gameStage.viewport.camera as OrthographicCamera
        camera.translate(200f, 250f)
        camera.zoom = 1.5f

        gameStage.addActor(createPhotoRealisticSea())
        grid1 = createGrid(200f, 100f, 8, 12)
        gameStage.addActor(grid1)
        gameStage.addActor(selectionBox)
        guiStage.addActor(createRandomSelectionButton())
        guiStage.addActor(createPlayerNameLabel())
        guiStage.addActor(slider)
        guiStage.addActor(createBackToTitleButton())

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
        photoRealisticSea.addListener(object : ActorGestureListener() {
            override fun pan(event: InputEvent?, x: Float, y: Float, deltaX: Float, deltaY: Float) {
                super.pan(event, x, y, deltaX, deltaY)
                camera.position.x -= (deltaX* Gdx.graphics.density)
                camera.position.y -= (deltaY* Gdx.graphics.density)
            }
        })

        return photoRealisticSea
    }

    private fun createGrid(x: Float, y: Float, rows: Int, colls: Int): GridCreator.Grid {
        var grid = gridCreator.makeLabeledGrid(x, y, rows, colls)
        grid.setPosition(x, y)
        val board = grid.board

        grid.addListener(object : ActorGestureListener() {
            override fun pan(event: InputEvent?, x: Float, y: Float, deltaX: Float, deltaY: Float) {
                super.pan(event, x, y, deltaX, deltaY)
                camera.position.x -= (deltaX* Gdx.graphics.density)
                camera.position.y -= (deltaY* Gdx.graphics.density)
            }

            override fun tap(event: InputEvent?, x: Float, y: Float, count: Int, button: Int) {
                super.tap(event, x, y, count, button)

                if (x in board.x..(board.x + board.width) && y in board.y..(board.y + board.height)) {
                    selectionBox.setVisible(true)

                    val cordX = (x - board.x).toInt() / FIELD_WIDTH
                    val cordY = (y - board.y).toInt() / FIELD_WIDTH
                    Gdx.app.log("name:" + board.name, "cordX:" + cordX + " cordY:" + cordY)

                    val targetX = cordX*FIELD_WIDTH + board.x*2 - gridCreator.thickness
                    val targetY = cordY*FIELD_WIDTH + board.y*2 - gridCreator.thickness - 1

//                    Gdx.app.log("name:"+grid.name,"targetX:"+targetX+" targetY:"+targetY)
//                    Gdx.app.log("name:"+grid.name,"gridX:"+grid.x+" gridY:"+grid.y)
//                    Gdx.app.log("name:"+grid.name,"x:"+(x)+" y:"+(y))

                    selectionBox.setPosition(targetX, targetY)
                }
            }
        })
        return grid
    }

    private fun createPlayerNameLabel(): Label {
        var label = Label(game.config.playerName, game.gameSkin, "default-black")
        label.setPosition(Gdx.graphics.getWidth() / 1.1f, Gdx.graphics.getHeight() / 1.1f)
        return label
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
        var btn = TextButton(game.labels.getString("button.back.main.menu"), skin)
        btn.width = (Gdx.graphics.width / 3).toFloat()
        btn.setPosition(Gdx.graphics.width / 2 - btn.width / 2, Gdx.graphics.height / 24 - btn.height / 2)
        btn.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.screen = MainMenuScreen(game)
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return btn
    }

    private fun createRandomSelectionButton(): TextButton {
        var btn = TextButton(game.labels.getString("game.screen.select.random.field"), skin)
        btn.width = (Gdx.graphics.width / 3.5f)
        btn.setPosition(0f, Gdx.graphics.height.toFloat() - btn.height)
        btn.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                selectRandomField(grid1)
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return btn
    }

    private fun selectRandomField(grid: GridCreator.Grid) {
        val targetX = (0..grid.colls).random() * FIELD_WIDTH + grid.x * 2 - gridCreator.thickness
        val targetY = (0..grid.rows).random() * FIELD_WIDTH + grid.y * 2 - gridCreator.thickness - 1
        selectionBox.setPosition(targetX, targetY)
    }

    private fun createSelectedBox(): Image {
        val thickenss = gridCreator.thickness
        var pixmap = Pixmap(FIELD_WIDTH + thickenss, FIELD_WIDTH + thickenss, Pixmap.Format.RGBA4444)
        val max = FIELD_WIDTH + thickenss - 1

        pixmap.setColor(Color.YELLOW)

        for (i in 0..gridCreator.thickness + 1) {
            pixmap.drawRectangle(0 + i, 0 + i, max - i*2, max - i*2)
        }

        return Image(Texture(pixmap))
    }
}