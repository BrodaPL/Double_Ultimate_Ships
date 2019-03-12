import os
import pathlib

KNOWN_EXTENSIONS = ('.txt', '.cfg', '.png', '.jpg', '.jpeg', '.bmp', '.vaw', '.mp3', '.ogg')

current_dir = pathlib.Path(__file__).parent
current_file = pathlib.Path(__file__)

print(current_dir)
print(current_file)

print('------STARTING WALK--------')
for(dirPath,dirNames,fileNames) in os.walk(current_dir):
    print('*** dirPatch:')
    print(dirPath)
    print('*** dirNames:')
    print(dirNames)
    print('*** fileNames:')
    print(fileNames)
    print('--------------')








#RESULT EXAMPLE
#ShipsResources is similar generated class
'''
package assets.images.space;

/*
Code generated by PoorResourceClassMaker
*/


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

'''











