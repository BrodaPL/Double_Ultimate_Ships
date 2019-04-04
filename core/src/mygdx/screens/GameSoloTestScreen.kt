package mygdx.screens

import assets.tempStuff.images.ImagesResources
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import mygdx.DoubleUltimateShips
import mygdx.GridCreator
import java.util.*


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

    private var selectionBox = createSelectedBox()

    init{
        imageResources = ImagesResources()
        skin =game.gameSkin
        gridCreator = GridCreator(FIELD_WIDTH,Color.BLACK, BitmapFont(Gdx.files.internal(game.fonts.stencilFNT())) )

        gameStage = Stage(ScreenViewport())
        guiStage = Stage(ScreenViewport())
        slider = createGuiSlider()

        multiplexer = InputMultiplexer(guiStage, gameStage)

        camera = gameStage.viewport.camera as OrthographicCamera
        camera.translate(200f,250f)
        camera.zoom= 1.5f





        gameStage.addActor(createPhotoRealisticSea())
        gameStage.addActor(createGrid(200f,100f,8,12))
        gameStage.addActor(selectionBox)
        guiStage.addActor(createPlayerNameLabel())
        guiStage.addActor(slider)
        guiStage.addActor(createBackToTitleButton())


        gameStage.addListener(object: InputListener(){
            override fun scrolled(event: InputEvent?, x: Float, y: Float, amount: Int): Boolean {
                slider.setValue(slider.value + (amount*-1*0.1f))
                Gdx.app.log("scrolled", "amount:"+amount)
                return super.scrolled(event, x, y, amount)
            }
        })


    }


    override fun show() {
        Gdx.app.log(this.javaClass.name, "show()")
        Gdx.input.setInputProcessor(multiplexer)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f,1f,1f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.zoom = slider.value
        camera.update()


        guiStage.act()
        gameStage.act()

        gameStage.draw()
        guiStage.draw()
    }

    override fun hide() {
        Gdx.app.log(this.javaClass.name, object{}.javaClass.enclosingMethod.name+"() not implemented")
    }

    override fun pause() {
        Gdx.app.log(this.javaClass.name, object{}.javaClass.enclosingMethod.name+"() not implemented")
    }

    override fun resume() {
        Gdx.app.log(this.javaClass.name, object{}.javaClass.enclosingMethod.name+"() not implemented")
    }

    override fun resize(width: Int, height: Int) {
        Gdx.app.log(this.javaClass.name, object{}.javaClass.enclosingMethod.name+"() not implemented")
    }

    override fun dispose() {
        Gdx.app.log(this.javaClass.name, "dispose()")
        guiStage.dispose()
        gameStage.dispose()
    }

    private fun createPhotoRealisticSea(): Image {
        var photoRealisticSea = Image(Texture(imageResources.temp_map__to_replaceJPG()))
        photoRealisticSea.addListener(object: ActorGestureListener(){
            override fun pan(event: InputEvent?, x: Float, y: Float, deltaX: Float, deltaY: Float) {
                super.pan(event, x, y, deltaX, deltaY)
                camera.position.x -= (deltaX* Gdx.graphics.density)
                camera.position.y -= (deltaY* Gdx.graphics.density)
            }
        })

        return photoRealisticSea
    }


    private fun createGrid(x: Float, y: Float, rows: Int, colls: Int): Group {
        var group = gridCreator.makeLabeledGrid(x,y,rows,colls)
        group.setPosition(x, y)
        val grid = group.findActor<Image>("grid1")

        group.addListener(object: ActorGestureListener(){
            override fun pan(event: InputEvent?, x: Float, y: Float, deltaX: Float, deltaY: Float) {
                super.pan(event, x, y, deltaX, deltaY)
                camera.position.x -= (deltaX* Gdx.graphics.density)
                camera.position.y -= (deltaY* Gdx.graphics.density)
            }

            override fun tap(event: InputEvent?, x: Float, y: Float, count: Int, button: Int) {
                super.tap(event, x, y, count, button)

                if(x in grid.x..(grid.x+grid.width) && y in grid.y..(grid.y+grid.height)){
                    selectionBox.setVisible(true)

                    val cordX = (x-grid.x).toInt()/FIELD_WIDTH
                    val cordY = (y-grid.y).toInt()/FIELD_WIDTH
                    Gdx.app.log("name:"+grid.name,"cordX:"+cordX+" cordY:"+cordY)

                    val targetX = cordX*FIELD_WIDTH+grid.x*2 -gridCreator.thickness
                    val targetY = cordY*FIELD_WIDTH+grid.y*2
                    Gdx.app.log("name:"+grid.name,"targetX:"+targetX+" targetY:"+targetY)
                    Gdx.app.log("name:"+grid.name,"gridX:"+grid.x+" gridY:"+grid.y)

                    selectionBox.setPosition(targetX,targetY)

                }
                Gdx.app.log("name:"+grid.name,"x:"+(x)+" y:"+(y))
            }
        })
        return group
    }


    private fun createPlayerNameLabel(): Label {
        var label = Label(game.config.playerName, game.gameSkin, "default-black")
        label.setPosition(Gdx.graphics.getWidth()/1.1f, Gdx.graphics.getHeight()/1.1f)
        return label
    }

    private fun createGuiSlider(): Slider {
        var slider = Slider(1f,2f,0.01f,true, skin)
        slider.setAnimateInterpolation(Interpolation.smooth);
//        slider.setAnimateDuration(0.1f)
        slider.setHeight(Gdx.graphics.height*0.8f)
        slider.setPosition(Gdx.graphics.getWidth()/12f, Gdx.graphics.getHeight()/10f)
        slider.setValue(1.5f)
        slider.addListener(object: InputListener(){
            override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                Gdx.app.log("touchDragged","slider Value:"+slider.getValue());
                super.touchDragged(event, x, y, pointer)
            }
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                Gdx.app.log("up","slider Value:"+slider.getValue())
                super.touchUp(event, x, y, pointer, button)
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                Gdx.app.log("down", "slider Value:"+slider.value)
                return true
            }
        })

        return slider
    }

    private fun createBackToTitleButton(): TextButton {
        var btn = TextButton("Back to main menu", skin)
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

    private fun createSelectedBox(): Image{
        var pixmap = Pixmap(FIELD_WIDTH+2, FIELD_WIDTH+2, Pixmap.Format.RGBA4444)
        pixmap.setColor(Color.YELLOW)

        pixmap.drawRectangle(0,0,FIELD_WIDTH+2-1,FIELD_WIDTH+2-1)
        pixmap.drawRectangle(1,1,FIELD_WIDTH+2-3,FIELD_WIDTH+2-3)
        pixmap.drawRectangle(2,2,FIELD_WIDTH+2-5,FIELD_WIDTH+2-5)
        pixmap.drawRectangle(3,3,FIELD_WIDTH+2-7,FIELD_WIDTH+2-7)


        return Image(Texture(pixmap))
    }
}