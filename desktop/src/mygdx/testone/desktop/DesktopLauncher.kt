package mygdx.testone.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import mygdx.DoubleUltimateShips

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.title = "Double Ultimate"
//        config.useGL30 = false
        config.height = 800
        config.width = 600


        //test menu and screens
        LwjglApplication(DoubleUltimateShips(),config)
    }
}
