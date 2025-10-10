package team.zhkv.service.impl;

import team.zhkv.GameMap;
import team.zhkv.entities.*;

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
            return "🪨 ";
        } else if (entity.getClass() == Tree.class) {
            return "🌲";
        } else {
            throw new RuntimeException(
                    "Class is not apllicable with renderer!");
        }
    }

    void render(GameMap gm, int iterateCount);
}
