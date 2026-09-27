package cho.info.game;

import cho.info.gameObjects.BehaviourBackend;
import cho.info.gameObjects.GameObject;
import com.raylib.Raylib;

import static com.raylib.Colors.BLUE;


public class Player extends GameObject {

    public int x = 300;
    public int y = 300;

    public Player() {
        super("HE_Player");

        _GbRender = () -> {
            Raylib.DrawCircle(x, y, 20, BLUE);
        };

        addMonoBehaviour(new PlayerBehavior(this));

    }

}
