package cho.info.renderer;

import cho.info.components.RenderCommand;
import cho.info.game.Player;
import cho.info.gameObjects.BehaviourBackend;
import com.raylib.Raylib;
import io.github.elebras1.flecs.Entity;
import io.github.elebras1.flecs.Flecs;
import io.github.elebras1.flecs.World;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.raylib.Colors.RAYWHITE;
import static com.raylib.Raylib.InitWindow;
import static com.raylib.Raylib.SetTargetFPS;

public class Helix {

    public static Helix INSTANCE;

    private World world;

    public static boolean isRunning;

    public void init() {
        INSTANCE = this;
        isRunning = true;
        String appHomeDir = System.getenv("APP_HOME");

        this.world = new World();

        InitWindow(800, 450, "Raumplaner");
        SetTargetFPS(60);

        BehaviourBackend.init(world, RenderQueue.renderQueue);

        long helixRenderer = world.entity("HE_Renderer");

        world.system("Startup")
                .kind(Flecs.OnStart)
                .with("HE_Renderer")
                .run((iter -> {
                    System.out.println("hello world");
                }));



        Player player = new Player();


    }


    public void run() {
        try {
            while(!Raylib.WindowShouldClose() && isRunning) {
                if (!world.progress()) break;

                Raylib.BeginDrawing();
                    Raylib.ClearBackground(RAYWHITE);

                    RenderCommand command;
                    while ((command = RenderQueue.renderQueue.poll()) != null) {
                        command.draw();
                    }
                Raylib.EndDrawing();

            }
        } finally {
            world.destroy();
            Raylib.CloseWindow();
        }



    }

}
