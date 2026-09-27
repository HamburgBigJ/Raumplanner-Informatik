package cho.info.gameObjects;

import cho.info.components.RenderCommand;
import io.github.elebras1.flecs.Flecs;
import io.github.elebras1.flecs.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public final class BehaviourBackend {

    private static World world;
    private static long gameObjectTag;

    private static final Map<Long, GameObject> objects = new HashMap<>();

    private BehaviourBackend() {}

    public static void init(
            World ecsWorld,
            Queue<RenderCommand> renderQueue
    ) {
        if (world != null) {
            throw new IllegalStateException("alredy init");
        }

        Objects.requireNonNull(ecsWorld);
        Objects.requireNonNull(renderQueue);

        long tag = ecsWorld.entity("JavaGameObject");

        ecsWorld.system("JavaBehaviourUpdate")
                .kind(Flecs.OnUpdate)
                .with(tag)
                .run(it -> {
                    while (it.next()) {
                        for (int i = 0; i < it.count(); i++) {
                            GameObject object = objects.get(it.entity(i));
                            if (object == null) continue;

                            object.updateBehaviours(it.deltaTime());

                            RenderCommand command = object.renderCommand();
                            if (command != null) {
                                renderQueue.add(command);
                            }
                        }
                    }
                });

        gameObjectTag = tag;
        world = ecsWorld;
    }

    static long register(GameObject object) {
        if (world == null) {
            throw new IllegalStateException(
                    "BehavoirBackend.init"
            );
        }

        long entityId = world.entity();

        objects.put(entityId, object);
        world.obtainEntity(entityId).add(gameObjectTag);

        return entityId;
    }

}