package mygdx

import assets.tempStuff.images.ImagesResources
import assets.tempStuff.images.SkinsResources
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import mygdx.screens.MainMenuScreen

class DoubleUltimateShips : Game() {

    lateinit var  config: DusConfig
    lateinit var  skins: SkinsResources
    lateinit var  images: ImagesResources
    internal lateinit var gameSkin: Skin

    override fun create() {
        skins = SkinsResources()
        config = DusConfig()
        images = ImagesResources()
        gameSkin = Skin(Gdx.files.internal(skins.neon.skin.neon_uiJSON()))
        this.setScreen(MainMenuScreen(this))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        super.dispose()
    }


}