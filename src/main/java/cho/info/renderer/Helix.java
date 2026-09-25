package cho.info.renderer;

import cho.info.components.RenderCommand;
import com.raylib.Raylib;
import io.github.elebras1.flecs.World;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.raylib.Raylib.InitWindow;
import static com.raylib.Raylib.SetTargetFPS;

public class Helix {

    public static Helix INSTANCE;

    public static Queue<RenderCommand> renderQueue = new ConcurrentLinkedQueue<>();

    private World world;

    public void init() {
        INSTANCE = this;
        String appHomeDir = System.getenv("APP_HOME");

        this.world = new World();

        InitWindow(800, 450, "Raumplaner");
        SetTargetFPS(60);
        /*
        world.system("GameSystem")
                .kind(Flecs.OnUpdate)
                .with(DummyTag.class)
                .run(it -> System.out.println("system GameSystem"));
                */


    }


    public void run() {

        try {
            while(world.progress()) {

                Raylib.BeginDrawing();

                    while (!renderQueue.isEmpty()) {
                        renderQueue.poll().draw();
                    }

                Raylib.EndDrawing();
            }
        } finally {
            world.destroy();
            Raylib.CloseWindow();
        }



    }

}
