package team.zhkv.move;

import java.util.List;
import java.util.Map;
import java.util.Random;

import team.zhkv.entities.Entity;
import team.zhkv.render.GameMap;

public class LocationFabric {
    private int boardX = GameMap.FIELD_SIZE_MID.getDx();
    private int boardY = GameMap.FIELD_SIZE_MID.getDy();
    private List<Map<Location, Entity>> maps;
    private Map<Location, Entity> toCreate;
    private Random random = new Random(31);

    public LocationFabric(List<Map<Location, Entity>> maps, Map<Location, Entity> toCreate) {
        this.maps = maps;
        this.toCreate = toCreate;
    }

    private boolean locationsContainsNewLocation(Location l) {
        if (toCreate.containsKey(l)) {
            return true;
        }
        for (int i = 0; i < maps.size(); i++) {
            if (maps.get(i).containsKey(l)) {
                return true;
            }
        }
        return false;
    }

    private Location getFreeRandomLocation() {
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
