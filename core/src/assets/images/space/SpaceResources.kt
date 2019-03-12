package assets.images.space

import assets.images.space.ships.ShipsResources

class SpaceResources {

    var ships = ShipsResources()

    fun asteroidPNG(): String {
        return this.javaClass.getResource("asteroid.png").path
    }

    fun moonPNG(): String {
        return this.javaClass.getResource("moon.png").path
    }

    fun uranusPNG(): String {
        return this.javaClass.getResource("uranus.png").path
    }

}
