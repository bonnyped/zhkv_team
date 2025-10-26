package team.zhkv.actions.move;

/**
 * Enum representing possible movement directions on the board.
 *
 * @author bonnyped
 */
public enum Direction {
    /** Move up (north), shift (0, -1). */
    UP(new Coordinate(0, -1)),
    /** Move down (south), shift (0, 1). */
    DOWN(new Coordinate(0, 1)),
    /** Move left (west), shift (-1, 0). */
    LEFT(new Coordinate(-1, 0)),
    /** Move right (east), shift (1, 0). */
    RIGHT(new Coordinate(1, 0)),
    /** Move up-left (northwest), shift (-1, -1). */
    UP_LEFT(new Coordinate(-1, -1)),
    /** Move up-right (northeast), shift (1, -1). */
    UP_RIGHT(new Coordinate(1, -1)),
    /** Move down-left (southwest), shift (-1, 1). */
    DOWN_LEFT(new Coordinate(-1, 1)),
    /** Move down-right (southeast), shift (1, 1). */
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