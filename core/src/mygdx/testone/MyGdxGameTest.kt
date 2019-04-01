package mygdx.testone

import assets.images.SkinsResources
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import mygdx.screens.TitleScreenOne

class MyGdxGameTest : Game() {

    internal companion object {
        internal lateinit var gameSkin: Skin
    }
    internal lateinit var skins: SkinsResources


    override fun create() {
        skins = SkinsResources()
        gameSkin = Skin(Gdx.files.internal(skins.neon.skin.neon_uiJSON()))
        this.setScreen(TitleScreenOne(this, gameSkin))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        super.dispose()
    }


}