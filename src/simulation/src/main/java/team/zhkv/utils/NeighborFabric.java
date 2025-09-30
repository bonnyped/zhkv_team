package team.zhkv.utils;

import team.zhkv.move.Location;

public class NeighborFabric {
    private Neighbor neighbor;

    public NeighborFabric(Location first, Location second) {
        neighbor = new Neighbor(first, second);
    }

    public Neighbor getNeighbor() {
        return neighbor;
    }
}
