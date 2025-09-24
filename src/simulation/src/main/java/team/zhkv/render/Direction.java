package team.zhkv.render;

public enum Direction {
    UP(new Location(0, -1)),
    DOWN(new Location(0, 1)),
    LEFT(new Location(-1, 0)),
    RIGHT(new Location(1, 0)),
    UP_LEFT(new Location(-1, -1)),
    UP_RIGHT(new Location(1, -1)),
    DOWN_LEFT(new Location(-1, 1)),
    DOWN_RIGHT(new Location(1, 1));

    private final Location delta;

    Direction(Location delta) {
        this.delta = delta;
    }

    public Location getDelta() {
        return delta;
    }
}