package team.zhkv.render;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import team.zhkv.App;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Predator;
import team.zhkv.entities.Rock;
import team.zhkv.entities.Tree;

public class LocationFabric {
    private int boardX = App.FIELD_SIZE_MIN.getDx();
    private int boardY = App.FIELD_SIZE_MIN.getDy();
    private List<Map<Location, Entity>> maps = new ArrayList<>();
    private Set<Location> rokcs;

    private Set<Location> trees;

    private Set<Location> grass;

    private Set<Location> herbivores;

    private Set<Location> predators;

    public LocationFabric(List<Map<Location, Entity>> maps) {
        this.maps = maps;
    }

    private boolean locationExists(Set<Location> s, Location l) {
        return s != null && s.contains(l);
    }

    private boolean locationsContainsNewLocation(Location l) {
        return locationExists(grass, l)
                || locationExists(rokcs, l)
                || locationExists(trees, l)
                || locationExists(predators, l)
                || locationExists(herbivores, l);
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

    public void bindSetsWithMaps(Map<Location, Entity> map,
            Class<? extends Entity> clazz) {
        if (clazz == Rock.class) {
            rokcs = map.keySet();
        } else if (clazz == Tree.class) {
            trees = map.keySet();
        } else if (clazz == Grass.class) {
            grass = map.keySet();
        } else if (clazz == Herbivore.class) {
            herbivores = map.keySet();
        } else if (clazz == Predator.class) {
            predators = map.keySet();
        }
    }

    public Location buildLocation() {
        return getFreeRandomLocation();
    }

    public Set<Location> getGrass() {
        return grass;
    }

    public Set<Location> getHerbivores() {
        return herbivores;
    }

    public Set<Location> getPredators() {
        return predators;
    }
}
