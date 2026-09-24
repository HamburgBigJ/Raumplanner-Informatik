package cho.info.Furniture.objects;

import cho.info.Furniture.Furniture;

import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class Klavier extends Furniture {
    @Override
    public void ListShape() {
        GeneralPath klav = new GeneralPath();
        Rectangle2D base = new Rectangle2D.Double(xPos, yPos, height, height);
        var tastx = length*0.05;
        var tasty = height*0.2;
        for (int i = 0; i <= 15; i++) {
            Rectangle2D taste = new Rectangle2D.Double(
                    (xPos)+(i*tastx)+(xPos*0.15),
                    (yPos)+(height*0.8),
                    tastx,
                    tasty
            );
            klav.append(taste, false);
        }
        klav.append(base, false);
        addShape(klav);
    }
}
