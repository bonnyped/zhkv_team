package team.zhkv.render;

import java.util.Map;

import team.zhkv.App;
import team.zhkv.entities.Entity;
import team.zhkv.move.Location;

public class FinalRenderer implements Renderable {

    @Override
    public FinalRenderer render(GameMap gm, int iterateCount) {
        Map<Location, Entity> locations = gm.getWholeMapEntities();
        Location currentLocation = new Location();
        for (int i = 0; i < GameMap.FIELD_SIZE_MID.getDx(); i++) {
            for (int j = 0; j < GameMap.FIELD_SIZE_MID.getDy(); j++) {
                currentLocation.setLocation(j, i);
                System.out.printf("%s", entityForRender(
                        locations.get(currentLocation)));
            }
            System.out.println();
        }
        System.out.println("----------------" + iterateCount + "------------------");

        return this;
    }

}
