package assets.tempStuff.shaders.shockWave

class ShockWaveResources {


    fun fragmentGLSL(): String {
        return this.javaClass.getResource("fragment.glsl").path
    }

    fun vertexGLSL(): String {
        return this.javaClass.getResource("vertex.glsl").path
    }
}