package cho.info.module;

import io.github.elebras1.flecs.FlecsModule;
import io.github.elebras1.flecs.World;

public class MovementModule implements FlecsModule {
    @Override
    public void initModule(World world) {
        world.module(this);

    }
}
