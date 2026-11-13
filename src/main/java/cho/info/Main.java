package cho.info;

import cho.info.furniture.Chair;
import cho.info.furniture.Piano;
import cho.info.furniture.WallCabinet;
import cho.info.rendering.Window;

import java.awt.*;

public class Main {

    public static Window mainWindow;

    public static void main(String[] args) {
        mainWindow = new Window("Main Window", 600, 500, Color.WHITE);
        mainWindow.setVisible(true);
        mainWindow.append(new Piano());
    }
}
