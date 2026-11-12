package cho.info.roomplannermod.mixin;

import cho.info.rendering.RoomCanvas;
import info.cho.universalMixin.api.Mixin;
import info.cho.universalMixin.api.Replace;
import info.cho.universalMixin.api.Shadow;
import info.cho.universalMixin.api.WidenAccess;

import java.awt.*;

@Mixin(RoomCanvas.class)
@WidenAccess(fields = {"graphics"})
public class RoomCanvasMixin {

    @Shadow
    public Graphics2D graphics;

    @Replace(method = "setDrawingColor")
    public void setDrawingColor(String colorName) {
        Color color = switch (colorName) {
            case "red", "rot" -> Color.RED;
            case "blue", "blau" -> Color.BLUE;
            case "yellow", "gelb" -> Color.YELLOW;
            case "green", "gruen" -> Color.GREEN;
            case "purple", "lila" -> Color.MAGENTA;
            case "white", "weiss" -> Color.WHITE;
            case "cyan" -> Color.CYAN;
            default -> Color.YELLOW;
        };
        graphics.setColor(color);
    }

}
