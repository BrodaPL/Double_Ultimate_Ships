package mygdx

import assets.fonts.FontsResources
import assets.tempStuff.images.ImagesResources
import assets.tempStuff.images.SkinsResources
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import mygdx.screens.MainMenuScreen
import java.util.*

class DoubleUltimateShips : Game() {

    lateinit var config: DusConfig
    val skins = SkinsResources()
    val images = ImagesResources()
    val fonts = FontsResources()
    var labels: ResourceBundle
    internal lateinit var gameSkin: Skin

    init {
//        Locale.setDefault(Locale("pl","PL"))
        labels = ResourceBundle.getBundle("assets/labels/DusLabels")
    }

    override fun create() {
        config = DusConfig()
        gameSkin = Skin(Gdx.files.internal(skins.neon.skin.neon_uiJSON()))
        this.setScreen(MainMenuScreen(this))

        Gdx.app.log("DUS", "Current Locale:" + Locale.getDefault())
        Gdx.app.log("DUS", "Current Language is:" + labels.getString("game.language"))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        super.dispose()
    }
}