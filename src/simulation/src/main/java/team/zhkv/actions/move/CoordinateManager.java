package team.zhkv.actions.move;

import java.util.List;
import java.util.Set;

import team.zhkv.core.interfaces.IEdible;
import team.zhkv.map.GameManager;

public class CoordinateManager {
    private Set<Coordinate> occupiedCoordinates;

    private final CoordinateFactory cf;
    private final Pathfinder pathfinder;

    public CoordinateManager(GameManager gm, Set<Coordinate> occupiedCoordinates) {
        this.occupiedCoordinates = occupiedCoordinates;
        cf = new CoordinateFactory(occupiedCoordinates);
        pathfinder = new Pathfinder(gm);
    }

    public Coordinate getFreeCoordinate() {
        return cf.buildCoordinate();
    }

    public List<Coordinate> buildPath(Coordinate current, IEdible edible) {
        pathfinder.buildPath(current, edible);
    }

    public boolean isOccupiedCoordinate(Coordinate coordinate) {
        return occupiedCoordinates.contains(coordinate);
    }
}
