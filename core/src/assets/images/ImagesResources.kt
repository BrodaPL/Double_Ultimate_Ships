package assets.images

import assets.images.space.SpaceResources


/*
* Icons from Icons made by https://www.freepik.com/ from  https://www.flaticon.com/	.
* Flaticon is licensed by http://creativecommons.org/licenses/by/3.0/
*
*/

class ImagesResources {

    var space = SpaceResources()

    fun badlogicJPG(): String {
        return this.javaClass.getResource("badlogic.jpg").path
    }

}



