package mygdx.testone

import assets.images.ImagesResources
import assets.images.SkinsResources
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport

class MyGdxTestTwo : ApplicationAdapter() {
    internal lateinit var stage: Stage
    internal lateinit var resources: ImagesResources
    internal lateinit var skins: SkinsResources
    internal lateinit var texture: Texture

    internal lateinit var outputLabel: Label

    override fun create() {
        super.create()
        stage = Stage(ScreenViewport())
        resources = ImagesResources()
        skins = SkinsResources()
        texture = Texture(resources.badlogic2JPG())
        Gdx.input.inputProcessor = stage

        val image1 = Image(texture)
        image1.setPosition(Gdx.graphics.width/3-image1.width/2,Gdx.graphics.height*2/3-image1.height/2)
        image1.rotateBy(45f)
        image1.setSize(texture.width/2f,texture.height/2f)
        stage.addActor(image1)

        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat)
        val textureRegion = TextureRegion(texture)
        textureRegion.setRegion(0,0,texture.width*8, texture.height*4)
        val image2 = Image(textureRegion)
        image2.setSize(400f,200f)
        image2.setPosition(Gdx.graphics.width*2/3-image2.width/2,Gdx.graphics.height*2/3-image2.height/2)
        stage.addActor(image2)

        val mySkin = Skin(Gdx.files.internal(skins.neon.skin.neon_uiJSON()))
        val helpGiudes = 12
        val rowHeight = Gdx.graphics.getWidth() / 12
        val colWidth = Gdx.graphics.getWidth() / 12

        //TITLE
        var title = Label("Buttons with Skins",mySkin,"over")
        title.setSize(Gdx.graphics.getWidth().toFloat(),rowHeight*2f);
        title.setPosition(0f,Gdx.graphics.getHeight()-colWidth*2f);
        title.setAlignment(Align.center);
        stage.addActor(title);

        val basicButtonInputListener = object : InputListener(){
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
               outputLabel.setText("Press a Button.")
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                outputLabel.setText("Button is under pressure! Realase it, quick!")
                return true
            }
        }

        //BASIC BUTTON
        var basicButton = Button(mySkin,"default")
        basicButton.setSize(colWidth*4f,rowHeight.toFloat())
        basicButton.setPosition(colWidth.toFloat(),Gdx.graphics.getHeight()-rowHeight*3f)
        basicButton.addListener(basicButtonInputListener)
        stage.addActor(basicButton)



        outputLabel = Label("Press a Button",mySkin,"default");
        outputLabel.setSize(Gdx.graphics.getWidth().toFloat(),rowHeight.toFloat());
        outputLabel.setPosition(0f,rowHeight.toFloat());
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);

    }

    override fun render() {
        super.render()
        Gdx.gl.glClearColor(1f,1f,1f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()

    }

}
