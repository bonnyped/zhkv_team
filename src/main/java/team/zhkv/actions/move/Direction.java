package team.zhkv.actions.move;

/**
 * Enum representing possible movement directions on the board.
 *
 * @author bonnyped
 */
public enum Direction {
    UP(new Coordinate(0, -1)),
    DOWN(new Coordinate(0, 1)),
    LEFT(new Coordinate(-1, 0)),
    RIGHT(new Coordinate(1, 0)),
    UP_LEFT(new Coordinate(-1, -1)),
    UP_RIGHT(new Coordinate(1, -1)),
    DOWN_LEFT(new Coordinate(-1, 1)),
    DOWN_RIGHT(new Coordinate(1, 1));

    private final Coordinate shift;

    Direction(Coordinate shift) {
        this.shift = shift;
    }

    /**
     * Returns the coordinate shift for this direction.
     *
     * @return the coordinate shift
     */
    public Coordinate getShift() {
        return shift;
    }
}