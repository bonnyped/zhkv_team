package team.zhkv.actions.move;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import team.zhkv.map.GameMap;

public class CoordinateFactory {
    private final Set<Coordinate> occupiedCoordinates;
    private final Random random = new Random(31);

    public CoordinateFactory(Set<Coordinate> occupiedCoordinates) {
        this.occupiedCoordinates = occupiedCoordinates;
    }

    public Coordinate buildCoordinate() {
        Set<Coordinate> buildedLocations = new HashSet<>();
        int boardX = GameMap.DX;
        int boardY = GameMap.DY;
        Coordinate newCoordinate = new Coordinate(random.nextInt(boardX),
                random.nextInt(boardY));

        while (occupiedCoordinates.contains(newCoordinate)
                || buildedLocations.contains(newCoordinate)) {
            buildedLocations.add(newCoordinate);
            newCoordinate.setCoordinate(random.nextInt(boardX),
                    random.nextInt(boardY));
        }

        return newCoordinate;
    }
}
