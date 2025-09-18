package team.zhkv.render;

import java.util.Map;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Location;
import team.zhkv.entities.Predator;
import team.zhkv.entities.Rock;
import team.zhkv.entities.Tree;
import team.zhkv.entities.Grass;

public class MapRenderer {
    private Location fieldSize;

    public MapRenderer(Location fieldSize) {
        this.fieldSize = fieldSize;
    }

    private void renderEntitiy(Entity entity) {
        if (entity.getClass() == Predator.class) {
            System.out.printf("%s", "🐱 ");
        } else if (entity.getClass() == Herbivore.class) {
            System.out.printf("%s", "🐰 ");
        } else if (entity.getClass() == Grass.class) {
            System.out.printf("%s", "🌱 ");
        } else if (entity.getClass() == Rock.class) {
            System.out.printf("%s", "🏔  ");
        } else if (entity.getClass() == Tree.class) {
            System.out.printf("%s", "🌴 ");
        }
    }

    public void render(Map<Location, Entity> entities) {
        Location currentLocation = new Location();
        for (int i = 0; i < fieldSize.getDx(); i++) {
            for (int j = 0; j < fieldSize.getDy(); j++) {
                currentLocation.setLocation(i, j);
                if (!entities.containsKey(currentLocation)) {
                    System.out.printf("%s", "⬛️ ");
                } else {
                    renderEntitiy(entities.get(currentLocation));
                }
            }
            System.out.println();
        }
    }

}
