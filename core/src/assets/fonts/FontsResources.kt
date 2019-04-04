package assets.fonts

class FontsResources{

    fun stencilFNT(): String {
        return this.javaClass.getResource("Stencil.fnt").path
    }
}