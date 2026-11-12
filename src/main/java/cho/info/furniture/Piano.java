package cho.info.furniture;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;


public class Piano extends Furniture {
    public Piano() {
        super(200, 80, "red", 140, 60);
        show();
    }

    @Override
    protected Shape createShape() {
        GeneralPath piano = new GeneralPath();
        Rectangle2D body = new Rectangle2D.Double(0, 0, getWidth(), getDepth());
        Rectangle2D keyboard = new Rectangle2D.Double(
                0.1 * getWidth(), 0.8 * getDepth(), 0.8 * getWidth(), 0.2 * getDepth());
        piano.append(body, false);
        piano.append(keyboard, false);
        for (int keyIndex = 0; keyIndex < 12; keyIndex++) {
            double keyX = (0.8 * getWidth() / 12) * keyIndex + 0.1 * getWidth();
            Line2D key = new Line2D.Double(keyX, 0.8 * getDepth(), keyX, getDepth());
            piano.append(key, false);
        }
        return piano;
    }
}
