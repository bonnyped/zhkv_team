package team.zhkv.actions.move;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import team.zhkv.map.GameManager;

public class CoordinateFactory {
    private static final Random RANDOM = new Random(31);
    private final Set<Coordinate> occupiedCoordinates;

    public CoordinateFactory(Set<Coordinate> occupiedCoordinates) {
        this.occupiedCoordinates = occupiedCoordinates;
    }

    public Coordinate buildCoordinate() {
        Set<Coordinate> buildedCoordinates = new HashSet<>();
        int boardX = GameManager.DX;
        int boardY = GameManager.DY;
        Coordinate newCoordinate = new Coordinate(RANDOM.nextInt(boardX),
                RANDOM.nextInt(boardY));

        while (occupiedCoordinates.contains(newCoordinate)
                || buildedCoordinates.contains(newCoordinate)) {
            buildedCoordinates.add(newCoordinate);
            newCoordinate.setCoordinate(RANDOM.nextInt(boardX),
                    RANDOM.nextInt(boardY));
        }

        return newCoordinate;
    }
}
