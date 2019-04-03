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
import mygdx.DoubleUltimateShips
import mygdx.DusConfig
import mygdx.tempStuff.screens.*

class MainMenuScreen(val _game: DoubleUltimateShips) : Screen {
    internal var stage : Stage
    internal var game : DoubleUltimateShips
    internal var skin : Skin

    init{
        game = _game
        skin = _game.gameSkin
        stage = Stage(ScreenViewport())

        stage.addActor(createTitleLabel())
        stage.addActor(createPlaySoloButton())
        stage.addActor(createPlayLanButton())
        stage.addActor(createOptionsButton())
        stage.addActor(createTestingStuffButton())
    }

    override fun hide() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun show() {
        Gdx.app.log(this.javaClass.name, "show")
        Gdx.input.setInputProcessor(stage)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f,1f,1f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dispose() {
        stage.dispose()
    }

    private fun createTitleLabel(): Label {
        var titleLbl = Label("Double Ultimate Ships", skin, "default-black")
        titleLbl.setAlignment(Align.center)
        titleLbl.setY(Gdx.graphics.height*1.8f)
        titleLbl.setWidth(Gdx.graphics.width.toFloat())
        return titleLbl
    }

    private fun createPlaySoloButton(): TextButton {
        var guiTestBtn = TextButton("Play Solo", skin)
        guiTestBtn.setWidth(Gdx.graphics.getWidth()/2f)
        guiTestBtn.setPosition(Gdx.graphics.getWidth()/2-guiTestBtn.getWidth()/2, Gdx.graphics.getHeight()/1.8f-guiTestBtn.getHeight()/1.8f)
        guiTestBtn.addListener(object: InputListener(){
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.setScreen(GameSoloTestScreen(game))
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return guiTestBtn
    }

    private fun createPlayLanButton(): TextButton {
        var playBtn = TextButton("Play LAN", skin)
        playBtn.setWidth(Gdx.graphics.getWidth()/2f)
        playBtn.setPosition(Gdx.graphics.getWidth()/2-playBtn.getWidth()/2, Gdx.graphics.getHeight()/2-playBtn.getHeight()/2)
        playBtn.addListener(object: InputListener(){
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                Gdx.app.log(this.javaClass.name,object{}.javaClass.enclosingMethod.name+"() not implemented")
//                game.setScreen(LanMenuScreen(game, skin))
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        playBtn.isDisabled = true

        return playBtn
    }



    private fun createOptionsButton() : TextButton {
        var optBtn = TextButton("Options", skin)
        optBtn.setWidth(Gdx.graphics.getWidth()/2f)
        optBtn.setPosition(Gdx.graphics.getWidth()/2-optBtn.getWidth()/2, Gdx.graphics.getHeight()/4-optBtn.getHeight()/2)
        optBtn.addListener(object : InputListener(){
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                Gdx.app.log(this.javaClass.name,object{}.javaClass.enclosingMethod.name+"() not implemented")
//                game.setScreen(OptionScreenOne(game, skin))
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return optBtn
    }

    private fun createTestingStuffButton() : TextButton {
        var optBtn = TextButton("Testing stuff", skin)
        optBtn.setWidth(Gdx.graphics.getWidth()/4f)
        optBtn.setPosition(Gdx.graphics.getWidth()/4-optBtn.getWidth()/4, Gdx.graphics.getHeight()/24-optBtn.getHeight()/2)
        optBtn.addListener(object : InputListener(){
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.setScreen(TitleScreenOne(game, skin))
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return optBtn
    }

}