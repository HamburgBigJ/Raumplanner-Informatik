package cho.info.furniture;

import cho.info.rendering.RoomCanvas;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class WallCabinet extends Furniture {
    private final List<Cabinet> cabinets = new ArrayList<>();
    private int cabinetCount;

    public WallCabinet(int cabinetCount) {
        super(40, 80, "blue", 40, 37);
        this.cabinetCount = cabinetCount;
        /*for (int cabinetIndex = 0; cabinetIndex < cabinetCount; cabinetIndex++) {
            cabinets.add(new Cabinet(
                    cabinetIndex * getWidth() / cabinetCount, 0, getColor(), 0,
                    getWidth() / cabinetCount, getDepth()));
        }*/
        shapeToArray();
    }

    private void shapeToArray() {
        for (int i = 0; i < cabinetCount; i++) {
            cabinets.add(new Cabinet(getXPosition() + ((getWidth())*i), getYPosition(), getColor(), 0, getWidth(), getDepth()));
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

    public void setCabinets(int a) {
        cabinetCount = a;
        for (Cabinet cabinet : cabinets) {
            RoomCanvas.getInstance().remove(cabinet);
        }
        cabinets.clear();
        shapeToArray();
        reDraw();
    }

    public void resizeWidth(int widht) {
        setWidth(widht);
        setCabinets(cabinetCount);
    }

    public void resizeDepth(int depth) {
        setDepth(depth);
        setCabinets(cabinetCount);
    }
}
