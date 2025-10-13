package team.zhkv.core.entities;

import java.lang.reflect.InvocationTargetException;

public class EntityFactory {
    public Entity buildEntity(Class<? extends Entity> entity) {
        try {
            // конструктор без параметров
            return entity.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            // Класс абстрактный или интерфейс, либо объект нельзя создать
            e.printStackTrace();
            throw new RuntimeException("Класс не может быть создан: " + entity.getName(), e);
        } catch (IllegalAccessException e) {
            // Нет доступа к конструктору (например, он private)
            e.printStackTrace();
            throw new RuntimeException("Нет доступа к конструктору: " + entity.getName(), e);
        } catch (InvocationTargetException e) {
            // Исключение во время выполнения конструктора
            e.printStackTrace();
            throw new RuntimeException("Ошибка в конструкторе: " + entity.getName(), e);
        } catch (NoSuchMethodException e) {
            // Нет конструктора без параметров
            e.printStackTrace();
            throw new RuntimeException("Нет конструктора без параметров: " + entity.getName(), e);
        }
    }
}
