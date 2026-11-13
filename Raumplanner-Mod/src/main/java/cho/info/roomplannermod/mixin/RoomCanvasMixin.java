package cho.info.roomplannermod.mixin;

import cho.info.rendering.Window;
import info.cho.universalMixin.api.Mixin;
import info.cho.universalMixin.api.Replace;
import info.cho.universalMixin.api.Shadow;
import info.cho.universalMixin.api.WidenAccess;

import java.awt.*;

@Mixin(Window.class)
@WidenAccess(fields = {"graphics"})
public class RoomCanvasMixin {

    @Shadow
    public Graphics2D graphics;


}
