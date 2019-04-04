package mygdx

import assets.fonts.FontsResources
import assets.tempStuff.images.ImagesResources
import assets.tempStuff.images.SkinsResources
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import mygdx.screens.MainMenuScreen

class DoubleUltimateShips : Game() {

    lateinit var  config: DusConfig
    val skins = SkinsResources()
    val images = ImagesResources()
    val fonts = FontsResources()
    internal lateinit var gameSkin: Skin


    override fun create() {
        config = DusConfig()
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