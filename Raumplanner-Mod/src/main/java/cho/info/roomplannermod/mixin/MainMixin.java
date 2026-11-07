package cho.info.roomplannermod.mixin;

import cho.info.roomplannermod.ModEntryPoint;
import info.cho.universalMixin.api.At;
import info.cho.universalMixin.api.Inject;
import info.cho.universalMixin.api.Mixin;

/** Connects the original application entry point to the mod initializer. */
@Mixin(target = "cho.info.Main")
public class MainMixin {
    @Inject(method = "main", at = At.HEAD)
    public static void beforeMain() {
        ModEntryPoint.onInitialize();
    }
}
