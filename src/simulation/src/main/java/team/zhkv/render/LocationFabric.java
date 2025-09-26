package team.zhkv.render;

import java.util.Set;
import java.util.Random;

import team.zhkv.App;

public class LocationFabric {
    private int boardX = App.FIELD_SIZE_MIN.getDx();
    private int boardY = App.FIELD_SIZE_MIN.getDy();
    private Set<Location> rokcs;

    private Set<Location> trees;

    private Set<Location> grass;

    private Set<Location> herbivores;

    private Set<Location> predators;

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

    public Location buildLocation() {
        return getFreeRandomLocation();
    }

    public void setRokcs(Set<Location> rokcs) {
        this.rokcs = rokcs;
    }

    public void setTrees(Set<Location> trees) {
        this.trees = trees;
    }

    public void setGrass(Set<Location> grass) {
        this.grass = grass;
    }

    public void setHerbivores(Set<Location> herbivores) {
        this.herbivores = herbivores;
    }

    public void setPredators(Set<Location> predators) {
        this.predators = predators;
    }
}
