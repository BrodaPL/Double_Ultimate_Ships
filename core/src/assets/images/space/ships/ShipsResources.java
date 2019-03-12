package assets.images.space.ships;

public class ShipsResources {

    public String spaceShipPNG(){
        return this.getClass().getResource("space-ship.png").getPath();
    }
    public String spaceShuttle_1PNG() {
        return this.getClass().getResource("space-shuttle_1.png").getPath();
    }
    public String spaceShuttle_2PNG() {
        return this.getClass().getResource("space-shuttle_2.png").getPath();
    }
}
