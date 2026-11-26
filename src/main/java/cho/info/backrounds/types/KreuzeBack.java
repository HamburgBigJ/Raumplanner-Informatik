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
        //TODO: impement
        return null;
    }
}
