package cho.info.furniture;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

/** A top-down washing machine outline for the room planner.
 *
 * @author Claus Albowski
 */
public class WashingMachine extends Furniture {
    public WashingMachine() {
        super(150, 120, "red", 60, 60);
        show();
    }

    @Override
    protected Shape createShape() {
        GeneralPath washingMachine = new GeneralPath();
        Rectangle2D frame = new Rectangle2D.Double(0, 0, getWidth(), getDepth());
        Shape drum = new Ellipse2D.Double(
                getWidth() * 0.1, getDepth() * 0.1, getWidth() * 0.8, getDepth() * 0.8);
        Shape laundry = new Ellipse2D.Double(
                getWidth() * 0.4, getDepth() * 0.4, getWidth() * 0.2, getDepth() * 0.2);
        washingMachine.append(frame, false);
        washingMachine.append(drum, false);
        washingMachine.append(laundry, false);
        return washingMachine;
    }
}
