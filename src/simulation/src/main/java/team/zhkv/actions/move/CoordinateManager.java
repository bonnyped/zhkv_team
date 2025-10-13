package team.zhkv.actions.move;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CoordinateManager {
    private final Map<Coordinate, Coordinate> entitiesToMove = new HashMap<>();
    private final CoordinateFactory cf;

    public CoordinateManager(Set<Coordinate> occupiedCoordinates) {
        cf = new CoordinateFactory(occupiedCoordinates);
    }

    public Map<Coordinate, Coordinate> getEntitiesToMove() {
        return entitiesToMove;
    }

    public Coordinate getFreeLocation() {
        return cf.buildLocation();
    }

}
