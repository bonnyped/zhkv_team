package team.zhkv.actions.move;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import team.zhkv.core.interfaces.IEdible;
import team.zhkv.map.GameManager;

/**
 * Manages coordinates for entity movement and actions.
 *
 * @author bonnyped
 */
public class CoordinateManager {
    private Set<Coordinate> occupiedCoordinates;
    private final Map<Coordinate, Coordinate> toMoveMap;
    private final Map<Coordinate, Coordinate> toActionMap;
    private final CoordinateFactory cf;
    private final Pathfinder pathfinder;

    /**
     * Constructs a CoordinateManager.
     *
     * @param gm                  the GameManager instance
     * @param occupiedCoordinates the set of currently occupied coordinates
     */
    public CoordinateManager(GameManager gm,
            Set<Coordinate> occupiedCoordinates) {
        this.toMoveMap = new HashMap<>();
        this.toActionMap = new HashMap<>();
        this.occupiedCoordinates = occupiedCoordinates;
        cf = new CoordinateFactory(occupiedCoordinates);
        pathfinder = new Pathfinder(gm);
    }

    /**
     * Returns the map of movable entities and their target coordinates.
     *
     * @return the move map
     */
    public Map<Coordinate, Coordinate> getMovebleEntitiesCoordinates() {
        return toMoveMap;
    }

    /**
     * Clears the move map.
     */
    public void clearToMoveMap() {
        toMoveMap.clear();
    }

    /**
     * Returns the map of action entities and their target coordinates.
     *
     * @return the action map
     */
    public Map<Coordinate, Coordinate> getActionEntitiesCoordinates() {
        return toActionMap;
    }

    /**
     * Returns a random free coordinate.
     *
     * @return a free coordinate
     */
    public Coordinate getFreeCoordinate() {
        return cf.buildCoordinate();
    }

    /**
     * Builds a path from the current coordinate to the nearest edible entity.
     *
     * @param current the starting coordinate
     * @param edible  the class of edible entities
     * @return the path as a list of coordinates
     */
    public List<Coordinate> buildPath(Coordinate current,
            Class<? extends IEdible> edible) {
        return pathfinder.buildPath(current, edible);
    }

    /**
     * Checks if a coordinate is occupied.
     *
     * @param coordinate the coordinate to check
     * @return true if occupied, false otherwise
     */
    public boolean isOccupiedCoordinate(Coordinate coordinate) {
        return occupiedCoordinates.contains(coordinate);
    }

    /**
     * Adds active coordinates for movement or action based on the path and speed.
     *
     * @param src   the source coordinate
     * @param path  the path to follow
     * @param speed the movement speed
     */
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

        if (speed == path.size() && !path.isEmpty()) {
            targetCoordinate = speed == 1
                    ? path.get(speed - 1)
                    : path.get(speed - 2);
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
