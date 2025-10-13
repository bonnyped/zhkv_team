package team.zhkv.move;

public enum Direction {
    UP(new Coordinate(0, -1)),
    DOWN(new Coordinate(0, 1)),
    LEFT(new Coordinate(-1, 0)),
    RIGHT(new Coordinate(1, 0)),
    UP_LEFT(new Coordinate(-1, -1)),
    UP_RIGHT(new Coordinate(1, -1)),
    DOWN_LEFT(new Coordinate(-1, 1)),
    DOWN_RIGHT(new Coordinate(1, 1));

    private final Coordinate delta;

    Direction(Coordinate delta) {
        this.delta = delta;
    }

    public Coordinate getDelta() {
        return delta;
    }
}