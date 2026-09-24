package cho.info.Furniture.objects;

import cho.info.Furniture.Furniture;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Tisch extends Furniture {
    @Override
    public void ListShape() {
        Shape tisch = new Ellipse2D.Double(0 , 0, length, height);
        addShape(tisch);
    }
}
