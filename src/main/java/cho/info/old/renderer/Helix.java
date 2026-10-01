package cho.info.old.renderer;

import cho.info.old.components.RenderCommand;
import cho.info.config.Config;
import cho.info.config.ConfigLoader;
import cho.info.old.game.Player;
import cho.info.old.gameObjects.BehaviourBackend;
import com.raylib.Colors;
import com.raylib.Raylib;
import io.github.elebras1.flecs.Flecs;
import io.github.elebras1.flecs.World;

import static com.raylib.Raylib.InitWindow;
import static com.raylib.Raylib.SetTargetFPS;

public class Helix {

    public static Helix INSTANCE;
    private World world;
    public static boolean isRunning;

    private Raylib.Color bakcroundColor;

    public void init() {
        INSTANCE = this;
        isRunning = true;
        String appHomeDir = System.getenv("APP_HOME");


        Config.AppConfig appC = ConfigLoader.load().app();

        bakcroundColor = switch (appC.backgroundColor()) {
            case "WHITE" -> Colors.WHITE;
            case "RAYWHITE" -> Colors.RAYWHITE;
            case "BLACK" -> Colors.BLACK;
            case "BLUE" -> Colors.BLUE;
            case "RED" -> Colors.RED;
            default -> throw new IllegalArgumentException(
                    appC.backgroundColor() + " Not supported"
            );
        };

        this.world = new World();

        InitWindow(appC.width(), appC.height(), appC.name());
        SetTargetFPS(appC.fpsTarget());

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
                    Raylib.ClearBackground(bakcroundColor);

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
