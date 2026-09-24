package cho.info.renderer;

import com.raylib.Raylib;

import static com.raylib.Raylib.InitWindow;
import static com.raylib.Raylib.SetTargetFPS;

public class Helix {

    public static void init() {
        String appHomeDir = System.getenv("APP_HOME");

        InitWindow(800, 450, "Raumplaner");
        SetTargetFPS(60);
    }


    public void run() {
        while (!Raylib.WindowShouldClose()) {

        }
    }

}
