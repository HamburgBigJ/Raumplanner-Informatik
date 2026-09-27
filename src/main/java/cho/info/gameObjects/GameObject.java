package cho.info.gameObjects;

import cho.info.components.RenderCommand;
import cho.info.script.MonoBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class GameObject {

    protected final String _ObId;
    protected RenderCommand _GbRender;

    private final long entityId;
    private final List<MonoBehaviour> behaviours = new ArrayList<>();

    protected GameObject(String id) {
        this._ObId = Objects.requireNonNull(id);
        this.entityId = BehaviourBackend.register(this);
    }

    public String getId() {
        return _ObId;
    }

    public long entityId() {
        return entityId;
    }

    public <T extends MonoBehaviour> T addMonoBehaviour(T behaviour) {
        Objects.requireNonNull(behaviour).attach(this);
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

    final void updateBehaviours(float deltaTime) {
        for (MonoBehaviour behaviour : List.copyOf(behaviours)) {
            behaviour.tick(deltaTime);
        }
    }

    final RenderCommand renderCommand() {
        return _GbRender;
    }

}
