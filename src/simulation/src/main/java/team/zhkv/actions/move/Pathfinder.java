package team.zhkv.actions.move;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import team.zhkv.map.GameManager;
import team.zhkv.core.interfaces.IEdible;

public class Pathfinder {
    private GameManager gm;
    private IEdible edible;
    private Coordinate targetCoordinate;

    private Deque<Coordinate> forCheck;
    private HashSet<Coordinate> checked;
    private Map<Coordinate, Coordinate> breadthFirstSearch;
    private List<Coordinate> path;

    public Pathfinder(GameManager gm) {
        this.gm = gm;
        breadthFirstSearch = new HashMap<>();
        path = new ArrayList<>();
        forCheck = new ArrayDeque<>();
        checked = new HashSet<>();
    }

    public List<Coordinate> buildPath(Coordinate current, IEdible edible) {
        build(current, edible);
        targetCoordinate = searchPath();
        retracePath();
        return path;
    }

    // public void moveBySpeedOrTargetCell(int speed) {
    // speed = speed > path.size() ? path.size() : speed;
    // if (speed > 0) {
    // targetCoordinate = speed > 1
    // ? determNextStep(speed)
    // : path.get(speed - 1);
    // }
    // if (targetCoordinate != null) {
    // GameState.updateCoordinate(currentCoordinate, targetCoordinate);
    // }
    // }

    // public Coordinate getGoalCoordinate() {
    // return path.size() == 1 ? path.get(0) : null;
    // }

    // private Coordinate determNextStep(int speed) {
    // return em.isOccupiedCoordinate(path.get(speed - 1))
    // ? path.get(speed - 2)
    // : path.get(speed - 1);
    // }

    private List<Coordinate> addNearestNeighbors(Coordinate current) {
        List<Coordinate> neighbors = new ArrayList<>();
        for (var direction : Direction.values()) {
            Coordinate neighbor = current.getNeighbor(direction.getShift());
            if (isAllowdNeighbor(neighbor)) {
                breadthFirstSearch.put(neighbor, current);
                neighbors.add(neighbor);
                checked.add(neighbor);
            }
        }
        return neighbors;
    }

    private boolean isAllowdNeighbor(Coordinate neighbor) {
        return isNotChecked(neighbor)
                && gm.isInBounds(neighbor)
                && cellFreeOrEdible(neighbor);
    }

    private boolean cellFreeOrEdible(Coordinate neighbor) {
        return isCellFree(neighbor)
                || isEdible(neighbor);
    }

    private boolean isNotChecked(Coordinate neighbor) {
        return !checked.contains(neighbor);
    }

    private boolean isCellFree(Coordinate neighbor) {
        return !gm.isOccupiedCoordinate(neighbor);
    }

    private boolean isEdible(Coordinate neighbor) {
        return edible.getClass().isAssignableFrom(
                gm.getEntity(neighbor).getClass());
    }

    private Coordinate searchPath() {
        while (!forCheck.isEmpty()) {
            Coordinate neighbor = forCheck.pollFirst();
            if (isNeighborEqualsTarget(neighbor)) {
                return neighbor;
            } else {
                forCheck.addAll(addNearestNeighbors(neighbor));
            }
        }

        return null;
    }

    private boolean isNeighborEqualsTarget(Coordinate neighbor) {
        return gm.isOccupiedCoordinate(neighbor)
                && isFoodFound(neighbor);
    }

    private boolean isFoodFound(Coordinate neighbor) {
        return gm.getEntity(neighbor).getClass() == edible.getClass();
    }

    private void build(Coordinate current, IEdible edible) {
        clearPathfinder();
        this.edible = edible;
        forCheck.addAll(addNearestNeighbors(current));
        breadthFirstSearch.put(null, current);
    }

    private void clearPathfinder() {
        path.clear();
        breadthFirstSearch.clear();
        checked.clear();
        forCheck.clear();
    }

    private void retracePath() {
        if (targetCoordinate != null) {
            path.add(targetCoordinate);
            Coordinate prev = breadthFirstSearch.get(targetCoordinate);

            while (prev != breadthFirstSearch.get(null)) {
                path.add(prev);
                prev = breadthFirstSearch.get(prev);
            }

            Collections.reverse(path);
        }
    }

}
