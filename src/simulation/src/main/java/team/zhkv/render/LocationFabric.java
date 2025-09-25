package team.zhkv.render;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import team.zhkv.App;

public class LocationFabric {
    private Set<Location> locations = new HashSet<>();
    private int boardX = App.FIELD_SIZE_MIN.getDx();
    private int boardY = App.FIELD_SIZE_MIN.getDy();

    private Location getFreeRandomLocation() {
        Random random = new Random(31);
        Location newLocation = new Location(random.nextInt(boardX),
                random.nextInt(boardY));

        while (locations.contains(newLocation)) {
            newLocation.setLocation(random.nextInt(boardX),
                    random.nextInt(boardY));
        }

        return newLocation;
    }

    public Location buildLocation() {
        return getFreeRandomLocation();
    }
}
