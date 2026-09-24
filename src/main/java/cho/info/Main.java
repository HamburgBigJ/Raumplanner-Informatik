package cho.info;

import cho.info.Furniture.objects.Klavier;
import cho.info.Furniture.objects.Stuhl;
import cho.info.Furniture.objects.Tisch;
import cho.info.register.ObjectRegister;
import org.bytedeco.javacpp.BytePointer;

import static com.raylib.Colors.LIGHTGRAY;
import static com.raylib.Colors.RAYWHITE;
import static com.raylib.Raylib.*;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        String appHomeDir = System.getenv("APP_HOME");

        InitWindow(800, 450, "Raumplaner");
        SetTargetFPS(60);


        while (!WindowShouldClose()) {
            BeginDrawing();

                ClearBackground(RAYWHITE);

                DrawText("Hello", 190, 200, 20, LIGHTGRAY);
                DrawFPS(100, 100);

            EndDrawing();
        }

        CloseWindow();

    }

    /*
            Leinwand.gibLeinwand();

        new Stuhl().registerObject();
        new Tisch().registerObject();
        new Klavier().registerObject();

        ObjectRegister.drawAllObjects();
     */

}
