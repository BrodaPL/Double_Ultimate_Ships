package mygdx

import assets.shaders.ShadersResources
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.scenes.scene2d.Group



//TODO Initial stuff. Not working yet.
class ShockWave : Group() {
    private var shaderResources: ShadersResources

    private val fbo: FrameBuffer
    private val vertexShader: String
    private val fragmentShader: String
    private val shaderProgram: ShaderProgram
    private var time: Float = 0.toFloat()

    private var disabled: Boolean = false

    private var shockWavePositionX: Float = 0.toFloat()
    private var shockWavePositionY: Float = 0.toFloat()


    companion object {
        var instance = ShockWave()
    }

//    static private ShockWave shockWave;
//
//    static public ShockWave getInstance(){
//        if(shockWave==null){
//            shockWave=new ShockWave();
//        }
//        return shockWave;
//    }


    init {
        shaderResources = ShadersResources()
        disabled = true
        time = 0f
        vertexShader = Gdx.files.internal(shaderResources.shockWave.vertexGLSL()).readString()
        fragmentShader = Gdx.files.internal(shaderResources.shockWave.fragmentGLSL()).readString()
        shaderProgram = ShaderProgram(vertexShader, fragmentShader)
        ShaderProgram.pedantic = false

        fbo = FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.width, Gdx.graphics.height, true)
    }

    fun start(posX: Float, posY: Float) {
        this.shockWavePositionX = posX
        this.shockWavePositionY = posY
        val enable = RunnableAction()
        enable.runnable = Runnable { disabled = true }
        this.addAction(Actions.delay(1f, enable))
        disabled = false
        time = 0f

    }

    override fun act(delta: Float) {
        super.act(delta)
        time += delta
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        if (disabled) {
            super.draw(batch, parentAlpha)
        } else {
            batch.end()
            batch.flush()
            fbo.begin()
            batch.begin()
            Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
            super.draw(batch, parentAlpha)
            batch.end()
            batch.flush()
            fbo.end()
            batch.begin()
            batch.shader = shaderProgram
//            var v = Vector2()
            var v = Vector2(shockWavePositionX, shockWavePositionY)
            v.x = v.x / Gdx.graphics.width
            v.y = v.y / Gdx.graphics.height
            shaderProgram.setUniformf("time", time)
            shaderProgram.setUniformf("center", v)
            val texture = fbo.colorBufferTexture
            val textureRegion = TextureRegion(texture)
            // and.... FLIP!  V (vertical) only
            textureRegion.flip(false, true)
            batch.draw(textureRegion, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
            batch.shader = null
        }
    }

}