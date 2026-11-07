package cho.info.furniture;

import java.awt.Shape;
import java.awt.geom.GeneralPath;

/** A top-down cabinet outline for the room planner.
 *
 * @author Claus Albowski
 */
public class Cabinet extends Furniture {
    public Cabinet() {
        this(200, 80, "red", 0, 110, 60);
    }

    public Cabinet(int xPosition, int yPosition, String color, int rotation, int width, int depth) {
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
