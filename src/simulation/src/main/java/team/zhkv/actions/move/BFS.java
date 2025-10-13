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

public class BFS {
    private GameMap gm;
    private Class<? extends Entity> target;
    private Coordinate goal;
    private Creature creature;
    private Map<Coordinate, Coordinate> allPaths = new HashMap<>();
    private List<Coordinate> path = new ArrayList<>();
    private Deque<Coordinate> forCheck = new ArrayDeque<>();
    private HashSet<Coordinate> checked = new HashSet<>();

    public BFS build(GameMap gm,
            Coordinate current, Class<? extends Entity> target) {
        this.gm = gm;
        this.target = target;
        creature = (Creature) gm.getEntity(current);
        checked.add(current);
        forCheck.addAll(addNearestNeighbors(current));
        allPaths.put(null, current);
        goal = searchPath();

        return this;
    }

    public List<Coordinate> getPath() {
        if (goal != null) {
            buildPath();
        }
        return path;
    }

    private List<Coordinate> addNearestNeighbors(Coordinate current) {
        List<Coordinate> neighbors = new ArrayList<>();
        for (var direction : Direction.values()) {
            Coordinate neighbor = current.getNeighbor(direction.getDelta());
            if (isNotInChecked(neighbor)
                    && neighbor.isInBounds(gm.getFieldSize())
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

    private boolean isNeighborEdibleForCreature(IEater IEater,
            Coordinate neighbor) {
        if (gm.getEntity(neighbor) instanceof IEdible IEdible) {
            return IEater.getFood() == IEdible.getClass();
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
                && gm.getEntity(neighbor).getClass() == target;
    }

    private void buildPath() {
        path.add(goal);
        Coordinate prev = allPaths.get(goal);

        while (prev != allPaths.get(null)) {
            path.add(prev);
            prev = allPaths.get(prev);
        }

        Collections.reverse(path);
    }
}
