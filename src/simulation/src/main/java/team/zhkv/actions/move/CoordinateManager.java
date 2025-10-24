package team.zhkv.actions.move;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import team.zhkv.core.interfaces.IEdible;
import team.zhkv.map.GameManager;

public class CoordinateManager {
    private Set<Coordinate> occupiedCoordinates;
    private final Map<Coordinate, Coordinate> toMoveMap;

    private final Map<Coordinate, Coordinate> toActionMap;

    private final CoordinateFactory cf;
    private final Pathfinder pathfinder;

    public CoordinateManager(GameManager gm, Set<Coordinate> occupiedCoordinates) {
        this.toMoveMap = new HashMap<>();
        this.toActionMap = new HashMap<>();
        this.occupiedCoordinates = occupiedCoordinates;
        cf = new CoordinateFactory(occupiedCoordinates);
        pathfinder = new Pathfinder(gm);
    }

    public Map<Coordinate, Coordinate> getMovebleEntitiesCoordinates() {
        return toMoveMap;
    }

    public void clearToMoveMap() {
        toMoveMap.clear();
    }

    public Map<Coordinate, Coordinate> getActionEntitiesCoordinates() {
        return toActionMap;
    }

    public Coordinate getFreeCoordinate() {
        return cf.buildCoordinate();
    }

    public List<Coordinate> buildPath(Coordinate current, Class<? extends IEdible> edible) {
        return pathfinder.buildPath(current, edible);
    }

    public boolean isOccupiedCoordinate(Coordinate coordinate) {
        return occupiedCoordinates.contains(coordinate);
    }

    public void addActiveCoordinates(Coordinate src, List<Coordinate> path,
            int speed) {
        Coordinate target = getTargetCoordinate(path, speed);
        if (target != null) {
            if (occupiedCoordinates.contains(target)) {
                toActionMap.put(src, target);
            } else {
                toMoveMap.put(src, target);
            }
        }
    }

    private Coordinate getTargetCoordinate(List<Coordinate> path, int speed) {
        Coordinate targetCoordinate = null;
        speed = determFactSpeed(path.size(), speed);

        if (speed == path.size()) {
            targetCoordinate = speed == 1 ? path.get(speed - 1) : path.get(speed - 2);
        } else if (path.size() > speed) {
            targetCoordinate = path.get(speed - 1);
        }

        return targetCoordinate;
    }

    private int determFactSpeed(int size, int speed) {
        int factSpeed = 0;

        if (size != 0) {
            factSpeed = speed > size ? size : speed;
        }

        return factSpeed;
    }
}
