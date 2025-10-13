package team.zhkv.actions.move;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import team.zhkv.map.GameState;

public class CoordinateFactory {
    private final Set<Coordinate> occupiedCoordinates;
    private final Random random = new Random(31);

    public CoordinateFactory(Set<Coordinate> occupiedCoordinates) {
        this.occupiedCoordinates = occupiedCoordinates;
    }

    public Coordinate buildLocation() {
        Set<Coordinate> buildedLocations = new HashSet<>();
        int boardX = GameState.FIELD_SIZE.getDx();
        int boardY = GameState.FIELD_SIZE.getDy();
        Coordinate newLocation = new Coordinate(random.nextInt(boardX),
                random.nextInt(boardY));

        while (occupiedCoordinates.contains(newLocation)
                || buildedLocations.contains(newLocation)) {
            buildedLocations.add(newLocation);
            newLocation.setLocation(random.nextInt(boardX),
                    random.nextInt(boardY));
        }

        return newLocation;
    }
}
