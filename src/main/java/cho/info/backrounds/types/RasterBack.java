package cho.info.backrounds.types;

import cho.info.GraphicObject;

import java.awt.*;

public class RasterBack extends GraphicObject {
    private static RasterBack rasterBack;

    public static RasterBack getInstance() {
        if (rasterBack == null) rasterBack = new RasterBack();
        return rasterBack;
    }

    public RasterBack() {
        super(1, 1);
    }

    @Override
    protected Shape createShape() {
        //TODO: Impelement Raster
        return null;
    }
}
