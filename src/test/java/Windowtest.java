import cho.info.backrounds.BackgroundType;
import cho.info.furniture.Piano;
import cho.info.rendering.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class Windowtest {

    private Window window;

    @BeforeEach
    public void setup() {
        window = new Window("Main Window", 600, 500, Color.WHITE, BackgroundType.RASTER);
        window.setVisible(true);
    }

    @Test
    public void testWindowCreation() {
        window.draw();
        assertNotNull(window);
    }

    @Test
    public void testGrapicsObjectDraw() {
         window.append("piano1", Piano.class);
         assertTrue(window.getGraphicObject().get("piano1") instanceof Piano);
     }

}
