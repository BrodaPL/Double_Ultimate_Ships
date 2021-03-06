package mygdx.tempStuff.screens

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

class TitleScreenOne(val _game: Game, val _skin: Skin) : Screen {
    internal var stage: Stage
    internal var game: Game
    internal var skin: Skin

    init {
        game = _game
        skin = _skin
        stage = Stage(ScreenViewport())

        stage.addActor(createTitleLabel())
        stage.addActor(createGameScreenGuiButton())
        stage.addActor(createPlayButton())
        stage.addActor(createTestingOneButton())
        stage.addActor(createTestingTwoButton())
        stage.addActor(createOptionsButton())
    }

    override fun hide() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun show() {
        Gdx.app.log(this.javaClass.name, "show")
        Gdx.input.setInputProcessor(stage)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
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
        var titleLbl = Label("Title Screen", skin, "default")
        titleLbl.setAlignment(Align.center)
        titleLbl.setY(Gdx.graphics.height*2f / 3)
        titleLbl.setWidth(Gdx.graphics.width.toFloat())
        return titleLbl
    }

    private fun createGameScreenGuiButton(): TextButton {
        var guiTestBtn = TextButton("Gui Test", skin)
        guiTestBtn.setWidth(Gdx.graphics.getWidth() / 2f)
        guiTestBtn.setPosition(Gdx.graphics.getWidth() / 2 - guiTestBtn.getWidth() / 2, Gdx.graphics.getHeight() / 1.8f - guiTestBtn.getHeight() / 1.8f)
        guiTestBtn.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
               game.setScreen(GameScreenGuiTest(game, skin))
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return guiTestBtn
    }

    private fun createPlayButton(): TextButton {
        var playBtn = TextButton("Animation test", skin)
        playBtn.setWidth(Gdx.graphics.getWidth() / 2f)
        playBtn.setPosition(Gdx.graphics.getWidth() / 2 - playBtn.getWidth() / 2, Gdx.graphics.getHeight() / 2 - playBtn.getHeight() / 2)
        playBtn.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.setScreen(AnimationScreenTest(game, skin))
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return playBtn
    }

    private fun createTestingOneButton(): TextButton {
        var testOneBtn = TextButton("Features test 1", skin)
        testOneBtn.setWidth(Gdx.graphics.getWidth() / 2f)
        testOneBtn.setPosition(Gdx.graphics.getWidth() / 2 - testOneBtn.getWidth() / 2, Gdx.graphics.getHeight() / 2.5f - testOneBtn.getHeight() / 2)
        testOneBtn.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.setScreen(BasicStuffTestingScreen(game, skin))
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return testOneBtn
    }

    private fun createTestingTwoButton(): TextButton {
        var testTwoBtn = TextButton("Features test 2", skin)
        testTwoBtn.setWidth(Gdx.graphics.getWidth() / 2f)
        testTwoBtn.setPosition(Gdx.graphics.getWidth() / 2 - testTwoBtn.getWidth() / 2, Gdx.graphics.getHeight() / 3 - testTwoBtn.getHeight() / 2)
        testTwoBtn.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.setScreen(ButtonsTestingScreen(game, skin))
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return testTwoBtn
    }

    private fun createOptionsButton(): TextButton {
        var optBtn = TextButton("Options and Shader test", skin)
        optBtn.setWidth(Gdx.graphics.getWidth() / 2f)
        optBtn.setPosition(Gdx.graphics.getWidth() / 2 - optBtn.getWidth() / 2, Gdx.graphics.getHeight() / 4 - optBtn.getHeight() / 2)
        optBtn.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.setScreen(OptionScreenOne(game, skin))
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return optBtn
    }
}