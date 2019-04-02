package assets.images.particles

class ParticlesResources{

    fun bubleNoteP(): String {
        return this.javaClass.getResource("bubleNote.p").path
    }

    fun notePNG(): String {
        return this.javaClass.getResource("note.png").path
    }
}