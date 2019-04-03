package mygdx.tempStuff.screens

import assets.tempStuff.images.ImagesResources
import assets.tempStuff.sounds.SoundssResources
import com.badlogic.gdx.*
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import mygdx.tempStuff.TestStates


class BasicStuffTestingScreen(val game :Game, val skin: Skin) : Screen, InputProcessor {

    internal lateinit var batch: SpriteBatch


    internal var imagesRes = ImagesResources()
    internal var soundRes = SoundssResources()

    internal lateinit var texture: Texture
    internal lateinit var texture2: Texture
    internal lateinit var texture3_1x5: Texture
    internal lateinit var sprite1: Sprite
    internal lateinit var sprite2: Sprite
    internal lateinit var sprite3: Sprite

    internal var elapsedTime = 0f

    internal lateinit var soundBackground: Music
    internal lateinit var soundShoot: Sound
    internal lateinit var soundPick: Sound


    internal var currentState = TestStates.IMAGES

    internal var animClipsList = com.badlogic.gdx.utils.Array<TextureRegion>()
    internal lateinit var font: BitmapFont
    internal lateinit var animation1: Animation<TextureRegion>

    internal var inputVal = "nothing"


    override fun hide() {

    }

    override fun show() {
        //initializing resources
        batch = SpriteBatch()
        texture = Texture(imagesRes.badlogicJPG())
        texture2 = Texture(imagesRes.space.ships.spaceShuttle_1PNG())
        texture3_1x5 = Texture(imagesRes.animTest1PNG())
        sprite1 = Sprite(texture2)
        sprite1.setX(64f)
        sprite1.setY(0f)

        sprite2 = Sprite(createPixmapTexture(128,128))
        sprite3 = Sprite(Texture(imagesRes.space.moonPNG()))

        for( i in 0..4){
            animClipsList.add(TextureRegion(texture3_1x5,i*64,0,64,64))
        }

        animation1 = Animation<TextureRegion>(1/2f, animClipsList, Animation.PlayMode.LOOP)


        soundBackground = Gdx.audio.newMusic(Gdx.files.internal(soundRes.dzcozamanisAtlanticOceanInKeyWestMP3()))
        soundShoot = Gdx.audio.newSound(Gdx.files.internal(soundRes.kastenfrosch_cannonballMP3()))
//        soundShoot = Gdx.audio.newSound(Gdx.files.internal(soundRes.chipfork_laser01revWAV())) // NOT WORKING! WHY?
        soundPick = Gdx.audio.newSound(Gdx.files.internal(soundRes.kastenfrosch_gotitemMP3()))


        font = BitmapFont()
        font.setColor(Color.GREEN)
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {

    }




    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        //poor input chandling
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
                currentState= TestStates.IMAGES
            }else if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
                currentState= TestStates.ANIMATION
            }else if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
                currentState= TestStates.INPUT
            }else if(Gdx.input.isKeyPressed(Input.Keys.NUM_4)){
                currentState= TestStates.SOUND
            }else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
                currentState= TestStates.IMAGES
                if(soundBackground.isPlaying){
                    soundBackground.stop()
                }
                game.setScreen(TitleScreenOne(game, skin))
            }
        }


        if(currentState== TestStates.IMAGES){
            batch.draw(texture, 700f-texture.width, 500f-texture.height)

            sprite1.y=0f
            sprite1.draw(batch)

            sprite2.draw(batch)




        }else if(currentState== TestStates.ANIMATION){
            elapsedTime += Gdx.graphics.deltaTime
            var currentFrame: TextureRegion = animation1.getKeyFrame(elapsedTime, true)
            batch.draw(currentFrame, 600f-64, 800f-64)


            sprite1.setY(sprite1.getY()+ Gdx.graphics.deltaTime*100)
            if(sprite1.y>800f){
                sprite1.y=-512f
            }
            sprite1.draw(batch)

        }else if(currentState== TestStates.INPUT){
            Gdx.input.setInputProcessor(this)

            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                font.draw(batch,"LEFT",8f, 700f)
            }else if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
                font.draw(batch,"RIGHT",8f, 700f)
            }

            sprite3.setX(Gdx.input.getX().toFloat()-sprite3.width/2)
            sprite3.setY(-Gdx.input.getY().toFloat()+sprite3.height) // <- WTF?!
            sprite3.draw(batch)

            font.draw(batch,"y: "+ Gdx.input.getY(),8f, 32f)
            font.draw(batch,"x: "+ Gdx.input.getX(),8f, 16f)

        }else if(currentState== TestStates.SOUND){
            if(! soundBackground.isPlaying){
                soundBackground.play()
            }
            inputVal ="Click mouse buttons to do sound."
        }

        if(currentState!= TestStates.SOUND){
            if(soundBackground.isPlaying){
                soundBackground.stop()
            }
        }

        drawInfo()

        batch.end()
    }


    override fun dispose() {
        batch.dispose()
        texture.dispose()
        texture2.dispose()
        texture3_1x5.dispose()
        font.dispose()
        sprite1.texture.dispose()
        sprite2.texture.dispose()
        sprite3.texture.dispose()

        soundBackground.dispose()
        soundPick.dispose()
        soundShoot.dispose()
    }




    /**
     * SLOW AS F*@#, do not use
     */
    fun createPixmapTexture(x: Int, y:Int): Texture {
        var pixmap = Pixmap(x,y, Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.BLUE)
        pixmap.fill()

        pixmap.setColor(Color.BLACK)
        pixmap.drawLine(0, 0, pixmap.getWidth()-1, pixmap.getHeight()-1)
        pixmap.drawLine(0, pixmap.getHeight()-1, pixmap.getWidth()-1, 0)

        //Draw a circle about the middle
        pixmap.setColor(Color.YELLOW)
        pixmap.drawCircle(pixmap.getWidth()/2, pixmap.getHeight()/2, pixmap.getHeight()/2 - 1)

        var resultTexture = Texture(pixmap)
        pixmap.dispose()
        return resultTexture
    }


    fun drawInfo(){
        font.draw(batch,"Press 1,2,3,4 to change. ESC to exit.",8f, 770f)
        font.draw(batch,currentState.name,8f, 738f)
        font.draw(batch, inputVal,8f, 64f)
    }











    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        inputVal = "keyTyped "+screenX+" "+screenY
        return true
    }

    override fun keyTyped(character: Char): Boolean {
        inputVal = "keyTyped "+Char
        return true
    }

    override fun scrolled(amount: Int): Boolean {
        inputVal = "SCROLLING "+amount
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        return true
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        inputVal = "DRAG "+pointer
        return true
    }

    override fun keyDown(keycode: Int): Boolean {
        if(keycode == Input.Keys.ANY_KEY){
            inputVal = "keyDown "+keycode
            if(keycode == Input.Keys.NUM_1){
                currentState= TestStates.IMAGES
            }else if(keycode == Input.Keys.NUM_2){
                currentState= TestStates.ANIMATION
            }else if(keycode == Input.Keys.NUM_4){
                currentState= TestStates.SOUND
            }
        }
        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        inputVal = "touchUp "+button
        return true
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        inputVal = "touchDown "+button

        when(currentState){
            TestStates.SOUND ->{
                if(button== Input.Buttons.LEFT){
                    soundShoot.play()
                }
                if(button== Input.Buttons.RIGHT){
                    soundPick.play()
                }
            }
            else ->{

            }

        }

        return true
    }


}
