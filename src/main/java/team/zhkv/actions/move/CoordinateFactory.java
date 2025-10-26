package team.zhkv.actions.move;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import team.zhkv.map.GameManager;

/**
 * Factory for generating random, unoccupied coordinates on the game board.
 *
 * @author bonnyped
 */
public class CoordinateFactory {
    private static final Random RANDOM = new Random(31);
    private final Set<Coordinate> occupiedCoordinates;

    /**
     * Constructs a CoordinateFactory with the given set of occupied coordinates.
     *
     * @param occupiedCoordinates the set of occupied coordinates
     */
    public CoordinateFactory(Set<Coordinate> occupiedCoordinates) {
        this.occupiedCoordinates = occupiedCoordinates;
    }

    /**
     * Builds a new random, unoccupied coordinate.
     *
     * @return the new coordinate
     */
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
