package team.zhkv.render;

import java.util.Map;

import team.zhkv.App;
import team.zhkv.entities.Entity;
import team.zhkv.move.Location;

public class FinalRenderer implements Renderable {

    @Override
    public FinalRenderer render(GameMap gm) {
        Map<Location, Entity> locations = gm.getWholeMapEntities();
        Location currentLocation = new Location();
        for (int i = 0; i < App.FIELD_SIZE_MIN.getDx(); i++) {
            for (int j = 0; j < App.FIELD_SIZE_MIN.getDy(); j++) {
                currentLocation.setLocation(j, i);
                System.out.printf("%s", entityForRender(
                        locations.get(currentLocation)));
            }
            System.out.println();
        }
        System.out.println("----------------------------------");

        return this;
    }

}
