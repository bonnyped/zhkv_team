package team.zhkv.move;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import team.zhkv.entities.Entity;
import team.zhkv.render.GameMap;

public class LocationFabric {
    private int boardX = GameMap.FIELD_SIZE_MID.getDx();
    private int boardY = GameMap.FIELD_SIZE_MID.getDy();
    private List<Map<Location, Entity>> maps;
    private Set<Location> buildedLocations = new HashSet<>();
    private Random random = new Random(31);

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

    public Location buildLocation() {
        Location newLocation = new Location(random.nextInt(boardX),
                random.nextInt(boardY));

        while (locationsContainsNewLocation(newLocation) || buildedLocations.contains(newLocation)) {
            newLocation.setLocation(random.nextInt(boardX),
                    random.nextInt(boardY));
        }
        buildedLocations.add(newLocation);

        return newLocation;
    }

    public void clearBuildedLocations() {
        buildedLocations.clear();
    }
}
