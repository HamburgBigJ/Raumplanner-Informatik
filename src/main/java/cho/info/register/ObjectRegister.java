package cho.info.register;

import cho.info.Furniture.Furniture;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ObjectRegister {

    public static Map<UUID, Furniture> FORegister = new HashMap<>();

    public static boolean addObject(UUID id, Class<?> object) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Furniture instance = (Furniture) object.getDeclaredConstructor().newInstance();
        FORegister.put(id, instance);

        return false;
    }

    public static void removeObject(UUID id) {
        FORegister.get(id).remove();
    }

    public static void drawAllObjects() {
        FORegister.forEach((uuid, o) -> {
            o.Init();
            //o.remove();
            //o.zeichne();
        });
    }

    public static void updateAllObject() {
        FORegister.forEach((uuid, f) -> {
            f.updateDraw();
        });
    }

}
