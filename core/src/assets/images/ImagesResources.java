package assets.images;

import assets.images.space.SpaceResources;


/*
* Icons from Icons made by https://www.freepik.com/ from  https://www.flaticon.com/	.
* Flaticon is licensed by http://creativecommons.org/licenses/by/3.0/
*
*/

public class ImagesResources {

    public SpaceResources space = new SpaceResources();

    public String badlogicJPG(){
        return this.getClass().getResource("badlogic.jpg").getPath();
    }

}



