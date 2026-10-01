package cho.info.old.script;

import cho.info.old.gameObjects.GameObject;

public class MonoBehaviour {
    protected GameObject gameObject;

    private boolean started;
    private boolean enabled = true;

    public final void attach(GameObject owner) {
        if (gameObject != null) {
            throw new IllegalStateException(
                    "Alrady on an object"
            );
        }

        gameObject = owner;
    }

    public final void tick(float deltaTime) {
        if (!enabled) return;

        if (!started) {
            started = true;
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

    public void start() {}

    public void update(float deltaTime) {}
}
