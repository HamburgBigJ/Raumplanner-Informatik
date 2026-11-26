package cho.info.backrounds.types;

import cho.info.GraphicObject;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class KreuzeBack extends GraphicObject {
    private static KreuzeBack kreuzeBack;

    public static KreuzeBack getInstance() {
        if (kreuzeBack == null) kreuzeBack = new KreuzeBack();
        return kreuzeBack;
    }


    public KreuzeBack() {
        super(1, 1);
    }

    @Override
    protected Shape createShape() {
        GeneralPath cabinet = new GeneralPath();
        cabinet.moveTo(0, 0);
        cabinet.lineTo(1, 0);
        cabinet.lineTo(22, 22);
        cabinet.lineTo(0, 22);
        cabinet.lineTo(0, 0);
        cabinet.lineTo(22, 33);
        cabinet.moveTo(0, 33);
        cabinet.lineTo(12, 0);
        return cabinet;
    }
}
