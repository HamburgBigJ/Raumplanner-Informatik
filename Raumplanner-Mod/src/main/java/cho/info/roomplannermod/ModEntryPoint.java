package cho.info.roomplannermod;

import cho.info.rendering.RoomCanvas;
import cho.info.roomplannermod.mixin.RoomCanvasMixin;

public final class ModEntryPoint {
    private ModEntryPoint() {
    }

    public static void onInitialize() {
        System.out.println("[Raumplanner-Mod] Mod initialized.");

        //RoomCanvas.getInstance().grapics();

    }
}
