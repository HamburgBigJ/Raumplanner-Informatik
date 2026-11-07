package cho.info.furniture;

import java.awt.Shape;
import java.awt.geom.GeneralPath;

/** A top-down chair outline for the room planner.
 *
 * @author Claus Albowski
 */
public class Chair extends Furniture {
    public Chair() {
        this(10, 10, "red");
        show();
    }

    public Chair(int xPosition, int yPosition, String color) {
        super(xPosition, yPosition, color, 40, 40);
    }

    @Override
    protected Shape createShape() {
        GeneralPath chair = new GeneralPath();
        chair.moveTo(0, 0);
        chair.lineTo(getWidth(), 0);
        chair.lineTo(getWidth() + (getWidth() / 20 + 1), getDepth());
        chair.lineTo(-(getWidth() / 20 + 1), getDepth());
        chair.lineTo(0, 0);
        // Add the backrest inside the outline.
        chair.moveTo(0, getWidth() / 10 + 1);
        chair.lineTo(getWidth(), getWidth() / 10 + 1);
        return chair;
    }
}
