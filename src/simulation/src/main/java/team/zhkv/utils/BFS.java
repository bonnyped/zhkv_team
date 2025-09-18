package team.zhkv.utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import team.zhkv.entities.Creature;
import team.zhkv.entities.CreaturesGoalGetter;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Location;
import team.zhkv.render.Direction;

public class BFS {
    private Map<Location, Entity> locations;
    private ArrayDeque<Location> forCheck = new ArrayDeque<>();
    private HashSet<Location> checked = new HashSet<>();
    private Location goalLocation;

    public BFS(Map<Location, Entity> locations) {
        this.locations = locations;
    }

    private ArrayList<Location> getNeigbors(Location creature, Location fieldSize) {
        ArrayList<Location> neighbors = new ArrayList<>();
        CreaturesGoalGetter creaturesGoal = (Creature) locations.get(creature);
        creaturesGoal.getCreaturesGoal(((Creature) locations.get(creature)).getGoal());

        for (var direction : Direction.values()) {
            neighbors.add(creature.getNeighbor(direction.getDelta()));
        }

        removeInvalidLocations(neighbors, fieldSize, creaturesGoal);
        checked.addAll(neighbors);

        return neighbors;
    }

    private void checkNeighborAsGoal(Entity neighbor, Entity creaturesGoal) {

    }

    private void removeInvalidLocations(ArrayList<Location> neighbors,
            Location fieldSize, Entity creaturesGoal) {
        for (int i = 0; i < neighbors.size(); i++) {
            Location neighbor = neighbors.get(i);
            if (!neighbor.isInBounds(fieldSize)) {
                neighbors.remove(i);
            } else if (locations.containsKey(neighbor)) {
                checkNeighborAsGoal(locations.get(neighbor), creaturesGoal);
            }
        }
    }

    private void findPath(Location creature) {

    }

    public void build(Location creature, Location fieldSize) {
        checked.add(creature);
        forCheck.addAll(getNeigbors(creature, fieldSize));
        findPath(creature);
    }

    public Location getGoalLocation() {
        return goalLocation;
    }

}
