package assets.images.space;

import assets.images.space.ships.ShipsResources;

public class SpaceResources {

    public ShipsResources ships = new ShipsResources();

    public String asteroidPNG(){
        return this.getClass().getResource("asteroid.png").getPath();
    }
    public String moonPNG(){
        return this.getClass().getResource("moon.png").getPath();
    }
    public String uranusPNG(){
        return this.getClass().getResource("uranus.png").getPath();
    }

}
