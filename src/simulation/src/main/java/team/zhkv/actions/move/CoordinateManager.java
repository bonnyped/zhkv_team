package team.zhkv.actions.move;

import java.util.Set;

public class CoordinateManager {
    private final CoordinateFactory cf;

    public CoordinateManager(Set<Coordinate> occupiedCoordinates) {
        cf = new CoordinateFactory(occupiedCoordinates);
    }

    public Coordinate getFreeCoordinate() {
        return cf.buildCoordinate();
    }

}
