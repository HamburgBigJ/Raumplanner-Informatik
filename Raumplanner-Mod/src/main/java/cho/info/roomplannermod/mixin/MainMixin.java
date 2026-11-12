package cho.info.roomplannermod.mixin;

import cho.info.roomplannermod.ModEntryPoint;
import info.cho.universalMixin.api.At;
import info.cho.universalMixin.api.Inject;
import info.cho.universalMixin.api.Mixin;

@Mixin(target = "cho.info.Main")
public class MainMixin {
    @Inject(method = "main", at = At.HEAD)
    public static void beforeMain() {
        ModEntryPoint.onInitialize();
    }
}
