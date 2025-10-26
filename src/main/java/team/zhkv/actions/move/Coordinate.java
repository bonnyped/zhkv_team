package team.zhkv.actions.move;

import java.util.Objects;

/**
 * Represents a coordinate on the game board.
 *
 * @author bonnyped
 */
public class Coordinate {
    private int dx;
    private int dy;

    /**
     * Constructs a coordinate at (0, 0).
     */
    public Coordinate() {
    }

    /**
     * Constructs a coordinate by copying another coordinate.
     *
     * @param other the coordinate to copy
     */
    public Coordinate(Coordinate other) {
        dx = other.dx;
        dy = other.dy;
    }

    /**
     * Constructs a coordinate at the specified position.
     *
     * @param dx the x position
     * @param dy the y position
     */
    public Coordinate(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    private Coordinate setCoordinate(Coordinate other) {
        this.dx += other.dx;
        this.dy += other.dy;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != Coordinate.class) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        return dx == other.dx && dy == other.dy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dx, dy);
    }

    /**
     * Returns the x position.
     *
     * @return the x position
     */
    public int getDx() {
        return dx;
    }

    /**
     * Returns the y position.
     *
     * @return the y position
     */
    public int getDy() {
        return dy;
    }

    /**
     * Sets the coordinate to the specified position.
     *
     * @param dx the x position
     * @param dy the y position
     * @return this coordinate
     */
    public Coordinate setCoordinate(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        return this;
    }

    /**
     * Returns a new coordinate that is a neighbor in the direction of the given
     * coordinate.
     *
     * @param other the direction shift
     * @return the neighbor coordinate
     */
    public Coordinate getNeighbor(Coordinate other) {
        return new Coordinate(this).setCoordinate(other);
    }

    @Override
    public String toString() {
        return "x = " + dx + " y = " + dy;
    }
}
