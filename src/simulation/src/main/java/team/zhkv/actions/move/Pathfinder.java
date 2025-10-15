package team.zhkv.actions.move;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import team.zhkv.map.GameMap;
import team.zhkv.core.entities.Creature;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.interfaces.IEater;
import team.zhkv.core.interfaces.IEdible;

public class Pathfinder {
    private GameMap gm;
    private Coordinate currentCoordinate;
    private Coordinate targetCoordinate;
    private Creature creature;
    private Map<Coordinate, Coordinate> allPaths;
    private List<Coordinate> path;
    private Deque<Coordinate> forCheck;
    private HashSet<Coordinate> checked;

    public Pathfinder() {
        allPaths = new HashMap<>();
        path = new ArrayList<>();
        forCheck = new ArrayDeque<>();
        checked = new HashSet<>();
    }

    public Pathfinder build(Creature creature, GameMap gm,
            Coordinate currentCoordinate) {
        this.gm = gm;
        this.creature = creature;
        this.currentCoordinate = currentCoordinate;
        checked.add(currentCoordinate);
        forCheck.addAll(addNearestNeighbors(currentCoordinate));
        allPaths.put(null, currentCoordinate);
        targetCoordinate = searchPath();

        return this;
    }

    public List<Coordinate> getPath() {
        if (targetCoordinate != null) {
            buildPath();
        }
        return path;
    }

    public void bySpeedOrTargetCell(int speed) {
        if (!path.isEmpty() && path.size() > 1) {
            targetCoordinate = speed < path.size()
                    ? path.get(speed - 1)
                    : path.get(path.size() - 2);
            gm.updateCreatureCoordinate(currentCoordinate, targetCoordinate);
        }
    }

    private List<Coordinate> addNearestNeighbors(Coordinate current) {
        List<Coordinate> neighbors = new ArrayList<>();
        for (var direction : Direction.values()) {
            Coordinate neighbor = current.getNeighbor(direction.getDelta());
            if (isNotInChecked(neighbor)
                    && neighbor.isInBounds(gm.getBounds())
                    && (neighborIsFreeCell(neighbor)
                            || isNeighborEdibleForCreature(creature,
                                    neighbor))) {
                allPaths.put(neighbor, current);
                neighbors.add(neighbor);
                checked.add(neighbor);
            }
        }
        return neighbors;
    }

    private boolean isNotInChecked(Coordinate neighbor) {
        return !checked.contains(neighbor);
    }

    private boolean neighborIsFreeCell(Coordinate neighbor) {
        return gm.getEntity(neighbor) == null;
    }

    private boolean isNeighborEdibleForCreature(IEater iEater,
            Coordinate neighbor) {
        if (gm.getEntity(neighbor) instanceof IEdible iEdible) {
            return iEater.getFood() == iEdible.getClass();
        } else {
            return false;
        }
    }

    private Coordinate searchPath() {
        while (!forCheck.isEmpty()) {
            Coordinate neighbor = forCheck.pollFirst();
            if (isNeighborEqualsTarget(neighbor)) {
                forCheck.clear();
                return neighbor;
            } else {
                forCheck.addAll(addNearestNeighbors(neighbor));
            }
        }
        return null;
    }

    private boolean isNeighborEqualsTarget(Coordinate neighbor) {
        return gm.getEntity(neighbor) instanceof Entity
                && gm.getEntity(neighbor).getClass() == creature.getFood();
    }

    private void buildPath() {
        path.add(targetCoordinate);
        Coordinate prev = allPaths.get(targetCoordinate);

        while (prev != allPaths.get(null)) {
            path.add(prev);
            prev = allPaths.get(prev);
        }
        reversePathAndRemoveStartLocation();
    }

    private void reversePathAndRemoveStartLocation() {
        Collections.reverse(path);
        path.remove(0);
    }

}
