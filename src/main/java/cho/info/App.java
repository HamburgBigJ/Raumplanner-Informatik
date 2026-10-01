package cho.info;

import cho.info.component.Draw;
import cho.info.config.Config;
import cho.info.config.ConfigLoader;
import com.raylib.Colors;
import com.raylib.Raylib;
import io.github.elebras1.flecs.Entity;
import io.github.elebras1.flecs.World;

public class App {

    public static World mainWorld;

    public void init() {
        mainWorld = new World();

        Config.AppConfig appConfig = ConfigLoader.load().app();

        Raylib.InitWindow(appConfig.width(), appConfig.height(), appConfig.name());
        Raylib.SetTargetFPS(appConfig.fpsTarget());

        Entity player = mainWorld.obtainEntity(mainWorld.entity("player"))
                .set(new Draw(() -> {
                    Raylib.DrawCircle(10, 10, 10f, Colors.BLUE);
                }));


    }

    public void run() {
        while (Raylib.WindowShouldClose()) {
            if (!mainWorld.progress(Raylib.GetFrameTime())) break;
            Raylib.BeginDrawing();
            mainWorld.getAlive() all comoenteltn and call draw
            Raylib.EndDrawing();
        }
    }

}
