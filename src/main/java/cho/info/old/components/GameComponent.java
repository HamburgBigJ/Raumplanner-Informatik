package cho.info.old.components;

import cho.info.old.gameObjects.GameObject;
import cho.info.old.script.MonoBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameComponent {

    private final List<MonoBehaviour> behaviours = new ArrayList<>();
    protected GameObject gameObject;

    private boolean started;
    private boolean enabled = true;


    public void attach(GameObject gameObject) {
        if (gameObject == null) {
            throw new NullPointerException();
        }
        this.gameObject = gameObject;
    }

    public <T extends MonoBehaviour> T addMonoBehaviour(T behaviour) {
        Objects.requireNonNull(behaviour).attach(gameObject);
        behaviours.add(behaviour);
        return behaviour;
    }

    public <T extends MonoBehaviour> T getMonoBehaviour(Class<T> type) {
        for (MonoBehaviour behaviour : behaviours) {
            if (type.isInstance(behaviour)) {
                return type.cast(behaviour);
            }
        }

        return null;
    }

    public final void fixedTick() {
        if (enabled) {
            fixedUpdate();
        }
    }

    public final void tick(float deltaTime) {
        if (!enabled) return;

        if (!started) {
            started = true;
            init();
            start();
        }

        if (enabled) {
            update(deltaTime);
        }
    }

    public final GameObject getGameObject() {
        return gameObject;
    }

    public final boolean isEnabled() {
        return enabled;
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void init() {}

    public void start() {}

    public void update(float deltaTime) {}

    public void fixedUpdate() {}

}
