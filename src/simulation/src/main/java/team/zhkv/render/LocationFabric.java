package team.zhkv.render;

import java.util.List;
import java.util.Map;
import java.util.Random;

import team.zhkv.App;
import team.zhkv.entities.Entity;

public class LocationFabric {
    private int boardX = App.FIELD_SIZE_MIN.getDx();
    private int boardY = App.FIELD_SIZE_MIN.getDy();
    private List<Map<Location, Entity>> maps;

    public LocationFabric(List<Map<Location, Entity>> maps) {
        this.maps = maps;
    }

    private boolean locationsContainsNewLocation(Location l) {
        for (int i = 0; i < maps.size(); i++) {
            if (maps.get(i).containsKey(l)) {
                return true;
            }
        }
        return false;
    }

    private Location getFreeRandomLocation() {
        Random random = new Random(31);
        Location newLocation = new Location(random.nextInt(boardX),
                random.nextInt(boardY));

        while (locationsContainsNewLocation(newLocation)) {
            newLocation.setLocation(random.nextInt(boardX),
                    random.nextInt(boardY));
        }

        return newLocation;
    }

    public Location buildLocation() {
        return getFreeRandomLocation();
    }
}
