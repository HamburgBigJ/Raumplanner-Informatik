package cho.info.furniture;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class Cabinet extends Furniture {
    public Cabinet() {
        this(200, 80, Color.RED, 0, 110, 60);
    }

    public Cabinet(int xPosition, int yPosition, Color color, int rotation, int width, int depth) {
        super(xPosition, yPosition, color, width, depth);
        setRotation(rotation);
    }

    @Override
    protected Shape createShape() {
        GeneralPath cabinet = new GeneralPath();
        cabinet.moveTo(0, 0);
        cabinet.lineTo(getWidth(), 0);
        cabinet.lineTo(getWidth(), getDepth());
        cabinet.lineTo(0, getDepth());
        cabinet.lineTo(0, 0);
        cabinet.lineTo(getWidth(), getDepth());
        cabinet.moveTo(0, getDepth());
        cabinet.lineTo(getWidth(), 0);
        return cabinet;
    }
}
