package mygdx.tempStuff.other

import assets.tempStuff.images.ImagesResources
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image

class MuzykalnaNuta(texture: Texture) : Image(texture){

    var effect: ParticleEffect
    val imagesResources=  ImagesResources()

    init{
        effect = ParticleEffect()
        var textureAtlas = TextureAtlas()
        textureAtlas.addRegion("note",TextureRegion(texture))
        effect.load(Gdx.files.internal(imagesResources.particles.bubleNoteP()), textureAtlas)
        effect.start()
        effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY())
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        effect.draw(batch)
    }

    override fun act(delta: Float) {
        super.act(delta)
        effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());
        effect.update(delta);
    }
}