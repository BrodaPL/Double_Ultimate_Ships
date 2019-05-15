package assets.tempStuff.images.space.ships

class ShipsResources {

    fun spaceShipPNG(): String {
        return this.javaClass.getResource("space-ship.png").path
    }

    fun spaceShuttle_1PNG(): String {
        return this.javaClass.getResource("space-shuttle_1.png").path
    }

    fun spaceShuttle_2PNG(): String {
        return this.javaClass.getResource("space-shuttle_2.png").path
    }
}