package mygdx

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.scenes.scene2d.Group


/**
 * Max tiles, vertical or horisontal is 26(characters amount)
 */
class GridCreator(val fieldWidth: Int, val color: Color, val font: BitmapFont){

    var thickness = 2
    var spaceAfterLines = 8 + thickness


    private fun createEmptyGrid( rows: Int,  colls: Int) : Image{
        val maxX =(colls+1)*fieldWidth-1
        val maxY =(rows+1)*fieldWidth-1

        var pixmap = Pixmap(maxX+1, maxY+1, Pixmap.Format.RGBA4444)
        pixmap.setColor(color)

        for(i in 1..colls+1){
            for(n in 0..thickness){
                pixmap.drawLine(i*fieldWidth-n-1 ,maxY,i*fieldWidth-n-1,0)
            }
        }
        for(i in 0..rows){
            for(n in 0..thickness){
                pixmap.drawLine(0,i*fieldWidth+n ,maxX,i*fieldWidth+n)
            }
        }

// for debug, do not remove yet
//        pixmap.fillCircle(0, 0, 8) // lg
//        pixmap.fillCircle(0, maxY, 8) // ld
//        pixmap.fillCircle(maxX, 0, 8) // pg
//        pixmap.fillCircle(maxX, maxY, 8) // pd
//
//        pixmap.drawLine(0, 0, 0, maxY) // lg-ld
//        pixmap.drawLine(0, maxY, maxX, maxY) // ld-pd
//        pixmap.drawLine(maxX, maxY,maxX, 0) // pd-pg
//        pixmap.drawLine(maxX,0,0,0) //pg-lg

        return Image(Texture(pixmap))
    }

    @Throws(IllegalArgumentException::class)
    fun makeLabeledGrid( x: Float, y: Float, rows: Int, colls: Int): Group {

        if(colls>26){
            throw IllegalArgumentException("There can not be more than 26 collumns!")
        }
        if(rows>40){//because why more?
            throw IllegalArgumentException("There can not be more than 40 rows!")
        }

        var group = Group()
        var grid = createEmptyGrid(rows, colls)
        grid.setName("grid1")
        grid.setPosition(x, y)

        group.addActor(grid)
        val labelStyle1 = Label.LabelStyle(font, color)

        //digits
        var rowLabel = 1
        for (i in 1..rows) {
            var label = Label(rowLabel.toString(), labelStyle1)
            label.setPosition(x, (i) * fieldWidth + y + spaceAfterLines)
            group.addActor(label)
            rowLabel++
        }

        //chars
        for (i in 1..colls) {
            var label = Label(((i + 64).toChar()).toString(), labelStyle1)
            label.setPosition((i) * fieldWidth + x + spaceAfterLines, y)
            group.addActor(label)
        }

        return group
    }

}