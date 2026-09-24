package cho.info.Furniture.objects;

import cho.info.Furniture.Furniture;

import java.awt.geom.GeneralPath;


public class Stuhl extends Furniture
{
    @Override
    public void ListShape() {
        GeneralPath stuhl = new GeneralPath();
        stuhl.moveTo(0 , 0);
        stuhl.lineTo(length, 0);
        stuhl.lineTo(length+(length/20+1), height);
        stuhl.lineTo(-(length/20+1), length);
        stuhl.lineTo(0 , 0);
        // Das ist die Umrandung. Das Stuhl bekommt noch eine Lehne:
        stuhl.moveTo(0 , (length/10+1));
        stuhl.lineTo(length, (length/10+1));
        addShape(stuhl);
    }

}
