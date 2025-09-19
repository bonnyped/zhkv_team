package team.zhkv.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import team.zhkv.entities.Creature;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Herbivore;
import team.zhkv.entities.Location;
import team.zhkv.entities.Predator;
import team.zhkv.render.Direction;

public class BFS {
    private Map<Location, Entity> locations;
    private NeighborsDeque forCheck = new NeighborsDeque();
    private HashSet<Location> checked = new HashSet<>();

    public BFS(Map<Location, Entity> locations) {
        this.locations = locations;
    }

    private void removeInvalidLocations(Queue<Location> neighbors,
            Location fieldSize) {
        for (int i = 0; i < neighbors.size(); i++) {
            Location neighbor = neighbors.peek(i);
            if (!neighbor.isInBounds(fieldSize) || checked.contains(neighbor)) {
                neighbors.remove(i);
            }
        }
    }

    private Location goalCellOrChecked(Class<? extends Entity> creatureClass,
            ArrayList<Location> neighbors) {
        Entity neighbor = null;

        for (int i = 0; i < neighbors.size(); i++) {
            neighbor = locations.get(neighbors.get(i));
        }

        return null;
    }

    private Queue<Location> getNeigborLocations(Location startCell, Location fieldSize) {
        Queue<Location> neighbors = new LinkedList<>();
        checked.add(startCell);

        for (var direction : Direction.values()) {
            // тут надо сразу проверять сгенерированного соседа
            neighbors.add(startCell.getNeighbor(direction.getDelta()));
        }

        removeInvalidLocations(neighbors, fieldSize);
        checked.addAll(neighbors);

        return neighbors;
    }

    private boolean isFoodFound(Class<? extends Entity> food,
            Class<? extends Entity> creature) {
        return food == Grass.class && creature == Herbivore.class
                || food == Herbivore.class && creature == Predator.class;
    }

    private ArrayList<Location> removeNotEmptyLocations(ArrayList<Location> neighbors) {

    }

    private Location findPathOrExpandNeighbors(
            Class<? extends Entity> cretureClass,
            Neighbor next, Location fieldSize) {
        Location goal = null;
        Queue<Location> neighbors = getNeigborLocations(
                next.getCellToSearch(), fieldSize);
        ArrayList<Location> entitiesNeighbors = removeNotEmptyLocations(neighbors);
        goal = goalCellOrChecked(cretureClass, neighbors);

        forCheck.addAllNeighbors(next.getNearestLocation(), neighbors);

        return goal;
    }

    private Location checkNeighbors(Location oldLocation, Location fieldSize) {
        Location newLocation = null;
        Entity creatureToMove = locations.get(oldLocation);

        while (!forCheck.isEmpty()) {
            Neighbor next = forCheck.pollFirst();
            Entity nextCell = locations.get(
                    next.getCellToSearch());
            if (isFoodFound(nextCell.getClass(),
                    creatureToMove.getClass())) {
                newLocation = next.getNearestLocation();
                forCheck.clear();
            } else {
                newLocation = findPathOrExpandNeighbors(
                        creatureToMove.getClass(), next, fieldSize);
            }
        }

        return newLocation;
    }

    public Location findNewLocation(Location oldLocation, Location fieldSize) {
        forCheck.addNearestNeighbors(getNeigborLocations(
                oldLocation, fieldSize));

        return checkNeighbors(oldLocation, fieldSize);
    }

}
