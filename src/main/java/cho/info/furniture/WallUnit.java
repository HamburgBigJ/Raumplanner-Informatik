package cho.info.furniture;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

/** A top-down wall unit outline for the room planner. */
public class WallUnit extends Furniture {
    private final List<Cabinet> cabinets = new ArrayList<>();

    public WallUnit(int cabinetCount) {
        super(40, 80, "blue", 300, 37);
        for (int cabinetIndex = 0; cabinetIndex < cabinetCount; cabinetIndex++) {
            cabinets.add(new Cabinet(
                    cabinetIndex * getWidth() / cabinetCount, 0, getColor(), 0,
                    getWidth() / cabinetCount, getDepth()));
        }
    }

    @Override
    protected Shape createShape() {
        GeneralPath wallUnit = new GeneralPath();
        for (Cabinet cabinet : cabinets) {
            wallUnit.append(cabinet.getShape(), false);
        }
        return wallUnit;
    }
}
