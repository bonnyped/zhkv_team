package team.zhkv.render;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Predator;
import team.zhkv.entities.Rock;
import team.zhkv.entities.Tree;

public interface Renderable {
    default String entityForRender(Entity entity) {
        if (entity == null) {
            return "⬛️";
        } else if (entity.getClass() == Predator.class) {
            return "🐺";
        } else if (entity.getClass() == Herbivore.class) {
            return "🐰";
        } else if (entity.getClass() == Grass.class) {
            return "🥕";
        } else if (entity.getClass() == Rock.class) {
            return "🪨";
        } else if (entity.getClass() == Tree.class) {
            return "🌲";
        } else {
            throw new RuntimeException(
                    "Class is not apllicable with renderer!");
        }
    }

    Renderable render(GameMap gm);
}
