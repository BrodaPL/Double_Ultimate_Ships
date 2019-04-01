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

class OptionScreenOne(val game: Game, val skin: Skin) : Screen{

    internal var stage: Stage

    init{
        stage = Stage(ScreenViewport())
        stage.addActor(createTitleLabel())
        stage.addActor(createLabel2())
        stage.addActor(createBackButton())
    }

    override fun hide() {

    }

    override fun show() {
        Gdx.app.log(this.javaClass.name, "show")
        Gdx.input.setInputProcessor(stage)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    override fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
        stage.dispose()
    }

    private fun createTitleLabel() : Label{
        var titleLbl = Label("Options Screen", skin, "default");
        titleLbl.setAlignment(Align.center);
        titleLbl.setY(Gdx.graphics.getHeight()*2/3f);
        titleLbl.setWidth(Gdx.graphics.getWidth().toFloat())
        return titleLbl
    }

    private fun createLabel2() : Label{
        var titleLbl = Label("So many options here...", skin, "default");
        titleLbl.setAlignment(Align.center);
        titleLbl.setY(Gdx.graphics.getHeight()*2/4f);
        titleLbl.setWidth(Gdx.graphics.getWidth().toFloat())
        return titleLbl
    }

    private fun createBackButton():TextButton{
        var backBtn = TextButton("Back", skin)
        backBtn.setWidth(Gdx.graphics.getWidth()/2f);
        backBtn.setPosition(Gdx.graphics.getWidth()/2-backBtn.getWidth()/2,Gdx.graphics.getHeight()/4-backBtn.getHeight()/2);
        backBtn.addListener(object : InputListener(){
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.setScreen(TitleScreenOne(game,skin))
            }
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        return backBtn
    }
}