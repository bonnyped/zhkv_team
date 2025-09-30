package team.zhkv.render;

import java.util.HashMap;
import java.util.Map;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.BColor;

import team.zhkv.App;
import team.zhkv.entities.Entity;
import team.zhkv.move.Location;

public class IntermediateRenderer implements Renderable {
    public IntermediateRenderer render(GameMap gm) {
        ColoredPrinter cp;
        Location currentLocation = new Location();
        Map<Location, Entity> locations = gm.getWholeMapEntities();
        ChangeStorage cs = gm.getChangeStorage();
        for (int i = 0; i < App.FIELD_SIZE_MIN.getDx(); i++) {
            for (int j = 0; j < App.FIELD_SIZE_MIN.getDy(); j++) {
                currentLocation.setLocation(j, i);
                cp = new ColoredPrinter.Builder(0, false)
                        .background(cs.getColorOfAction(currentLocation))
                        .build();
                cp.print(entityForRender(locations.get(
                        cs.getPassiveEntity(currentLocation))));
            }
            System.out.println();
        }
        System.out.println("----------------------------------");
        return this;
    }

}
