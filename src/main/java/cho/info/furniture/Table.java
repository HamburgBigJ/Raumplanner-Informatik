package cho.info.furniture;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/** A top-down table outline for the room planner.
 *
 * @author Claus Albowski
 */
public class Table extends Furniture {
    public Table() {
        super(150, 120, "red", 120, 100);
        show();
    }

    @Override
    protected Shape createShape() {
        return new Ellipse2D.Double(0, 0, getWidth(), getDepth());
    }
}
