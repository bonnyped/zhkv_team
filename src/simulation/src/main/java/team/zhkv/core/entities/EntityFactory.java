package team.zhkv.core.entities;

import java.lang.reflect.InvocationTargetException;

public class EntityFactory {
    public Entity buildEntity(Class<? extends Entity> entity) {
        try {
            return entity.getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
            throw new EntityInstantiationException(
                    "Error when creating an entity: " + entity.getName(), e);
        }
    }
}
