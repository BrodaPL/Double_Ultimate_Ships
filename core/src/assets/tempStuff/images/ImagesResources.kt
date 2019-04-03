package assets.tempStuff.images

import assets.tempStuff.images.particles.ParticlesResources
import assets.tempStuff.images.space.SpaceResources


/*
* Icons from Icons made by https://www.freepik.com/ from  https://www.flaticon.com/	.
* Flaticon is licensed by http://creativecommons.org/licenses/by/3.0/
*
* Other images from https://libgdx.info/ tutorial.
*/

class ImagesResources {

    val space = SpaceResources()

    val particles = ParticlesResources()

    fun badlogicJPG(): String {
        return this.javaClass.getResource("badlogic.jpg").path
    }

    fun badlogic2JPG(): String {
        return this.javaClass.getResource("badlogic2.jpg").path
    }

    fun animTest1PNG(): String {
        return this.javaClass.getResource("anim_test_1.png").path
    }

    fun switch_offPNG(): String {
        return this.javaClass.getResource("switch_off.png").path
    }

    fun switch_onPNG(): String {
        return this.javaClass.getResource("switch_on.png").path
    }

    fun temp_map__to_replaceJPG(): String{
        return this.javaClass.getResource("temp_map__to_replace.jpg").path
    }

    fun flameGIF(): String{
        return this.javaClass.getResource("flame.gif").path
    }

    fun magnifierPNG(): String{
        return this.javaClass.getResource("magnifier.png").path
    }

    fun ringPNG(): String{
        return this.javaClass.getResource("ring.png").path
    }


}



