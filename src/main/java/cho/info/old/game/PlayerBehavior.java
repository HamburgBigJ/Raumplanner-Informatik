package cho.info.old.game;

import cho.info.old.script.MonoBehaviour;
import static com.raylib.Raylib.*;

public class PlayerBehavior extends MonoBehaviour {

    private Player player;

    public PlayerBehavior(Player player) {
        this.player = player;
    }

    @Override
    public void start() {

    }

    @Override
    public void update(float detaTime) {
        float distance = 250f * detaTime;

        if (IsKeyDown(KEY_A)) player.x -= distance;
        if (IsKeyDown(KEY_D)) player.x += distance;
        if (IsKeyDown(KEY_W)) player.y -= distance;
        if (IsKeyDown(KEY_S)) player.y += distance;
    }

}
