package cho.info.old.gameObjects;

import cho.info.old.components.GameComponent;
import cho.info.old.components.RenderCommand;
import cho.info.old.script.MonoBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class GameObject {

    protected final String _ObId;
    protected RenderCommand _GbRender;

    private final long entityId;
    private final List<MonoBehaviour> behaviours = new ArrayList<>();
    private final List<GameComponent> components = new ArrayList<>();

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

    public <T extends GameComponent> T addComponent(T component) {
        Objects.requireNonNull(component).attach(this);
        components.add(component);
        return component;
    }

    public <T extends GameComponent> T getComponent(Class<T> type) {
        for  (GameComponent component : components) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
        }

        return null;

    }

    final void updateBehaviours(float deltaTime) {
        for (MonoBehaviour behaviour : List.copyOf(behaviours)) {
            behaviour.tick(deltaTime);
        }
    }

    final void updateComponents(float deltaTime) {
        for (GameComponent component : List.copyOf(components)) {
            component.tick(deltaTime);
        }
    }

    final RenderCommand renderCommand() {
        return _GbRender;
    }

}
