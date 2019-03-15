package assets.sounds

/*
* Sound files from https://freesound.org/
*/

class SoundssResources {


    fun chipfork_laser01revWAV(): String {
        return this.javaClass.getResource("chipfork_laser01rev.wav").path
    }

    fun kastenfrosch_gotitemMP3(): String {
        return this.javaClass.getResource("kastenfrosch_gotitem.mp3").path
    }

    fun kastenfrosch_cannonballMP3(): String {
        return this.javaClass.getResource("kastenfrosch_cannonball.mp3").path
    }

    fun dzcozamanisAtlanticOceanInKeyWestMP3(): String {
        return this.javaClass.getResource("dzcozamanis_atlantic-ocean-in-key-west.mp3").path
    }

}



