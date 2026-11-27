package cho.info.backrounds.types;

import cho.info.GraphicObject;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class RasterBack extends GraphicObject {
    private static RasterBack rasterBack;
    private int distance;

    public static RasterBack getInstance() {
        if (rasterBack == null) rasterBack = new RasterBack();
        return rasterBack;
    }

    public RasterBack() {
        super(1, 1);
        distance = 50;
    }

    @Override
    protected Shape createShape() {
        setWidth(getWindow().getWindowWidth());
        setDepth(getWindow().getWindowHeight());

        GeneralPath raster = new GeneralPath();
        for(int i=0; i<=getDepth()/distance; i++) {
            raster.moveTo(0,distance*i);
            raster.lineTo(getWidth(),distance*i);
        }

        for(int i=0; i<=getWidth()/distance; i++) {
            raster.moveTo(distance*i, 0);
            raster.lineTo(distance*i, getDepth());
        }
        return raster;
    }

    public void setDistance(int dis) {
        distance = dis;
        hide();
        show();
    }
}
