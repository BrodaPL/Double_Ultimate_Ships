package mygdx.testone.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import mygdx.testone.MyGdxTestOne
import mygdx.testone.MyGdxTestTwo

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.title = "Double Ultimate"
//        config.useGL30 = false
        config.height = 800
        config.width = 600

        //run MyGdxTestOne() to se some testing stuff
//        LwjglApplication(MyGdxTestOne(), config)

        //stage, texture rotarion and scalling and buttons with skin
        LwjglApplication(MyGdxTestTwo(), config)
    }
}
