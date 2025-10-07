package team.zhkv.entities;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import team.zhkv.move.Direction;
import team.zhkv.move.Location;
import team.zhkv.render.GameMap;

public class BFS {
    private GameMap gm;
    private Class<? extends Entity> target;
    private Location goal;
    private Creature creature;
    private Map<Location, Location> allPaths = new HashMap<>();
    private List<Location> path = new ArrayList<>();
    private Deque<Location> forCheck = new ArrayDeque<>();
    private HashSet<Location> checked = new HashSet<>();

    public BFS build(GameMap gm,
            Location current, Class<? extends Entity> target) {
        this.gm = gm;
        this.target = target;
        creature = (Creature) gm.getEntity(current);
        checked.add(current);
        forCheck.addAll(addNearestNeighbors(current));
        allPaths.put(null, current);
        goal = searchPath();

        return this;
    }

    public List<Location> getPath() {
        if (goal != null) {
            buildPath();
        }
        return path;
    }

    private List<Location> addNearestNeighbors(Location current) {
        List<Location> neighbors = new ArrayList<>();
        for (var direction : Direction.values()) {
            Location neighbor = current.getNeighbor(direction.getDelta());
            if (isNotInChecked(neighbor)
                    && neighbor.isInBounds(GameMap.FIELD_SIZE_MID)
                    && (neighborIsFreeCell(neighbor)
                            || isNeighborEdibleForCreature(creature, neighbor))) {
                allPaths.put(neighbor, current);
                neighbors.add(neighbor);
                checked.add(neighbor);
            }
        }
        return neighbors;
    }

    private boolean isNotInChecked(Location neighbor) {
        return !checked.contains(neighbor);
    }

    private boolean neighborIsFreeCell(Location neighbor) {
        return gm.getEntity(neighbor) == null;
    }

    private boolean isNeighborEdibleForCreature(Eater eater,
            Location neighbor) {
        if (gm.getEntity(neighbor) instanceof Edible edible) {
            return eater.getFood() == edible.getClass();
        } else {
            return false;
        }
    }

    private Location searchPath() {
        while (!forCheck.isEmpty()) {
            Location neighbor = forCheck.pollFirst();
            if (isNeighborEqualsTarget(neighbor)) {
                forCheck.clear();
                return neighbor;
            } else {
                forCheck.addAll(addNearestNeighbors(neighbor));
            }
        }
        return null;
    }

    private boolean isNeighborEqualsTarget(Location neighbor) {
        return gm.getEntity(neighbor) instanceof Entity
                && gm.getEntity(neighbor).getClass() == target;
    }

    private void buildPath() {
        path.add(goal);
        Location prev = allPaths.get(goal);

        while (prev != allPaths.get(null)) {
            path.add(prev);
            prev = allPaths.get(prev);
        }

        Collections.reverse(path);
    }
}
