package cho.info.backrounds;

import cho.info.GraphicObject;
import cho.info.backrounds.types.KreuzeBack;
import cho.info.backrounds.types.RasterBack;

public class BackTypeToObj {
    public static GraphicObject getObject(BackgroundType bType) {
        // TODO: Support mutlible instances of this shit
        switch (bType) {
            case KREUZE -> {
                return KreuzeBack.getInstance();
            }
            case RASTER -> {
                return RasterBack.getInstance();
            }
            default -> {
                return KreuzeBack.getInstance();
            }
        }
    }
}
