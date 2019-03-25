package assets.images

import assets.images.space.SpaceResources


/*
* Icons from Icons made by https://www.freepik.com/ from  https://www.flaticon.com/	.
* Flaticon is licensed by http://creativecommons.org/licenses/by/3.0/
*/

class ImagesResources {

    var space = SpaceResources()

    fun badlogicJPG(): String {
        return this.javaClass.getResource("badlogic.jpg").path
    }

    fun badlogic2JPG(): String {
        return this.javaClass.getResource("badlogic2.jpg").path
    }

    fun animTest1PNG(): String {
        return this.javaClass.getResource("anim_test_1.png").path
    }

    fun bswitch_offPNG(): String {
        return this.javaClass.getResource("switch_off.png").path
    }

    fun bswitch_onPNG(): String {
        return this.javaClass.getResource("switch_on.png").path
    }

}



