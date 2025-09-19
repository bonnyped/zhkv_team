package team.zhkv.utils;

import team.zhkv.entities.Location;

public class NeighborFabric {
    private Neighbor neighbor;

    public NeighborFabric(Location first) {
        neighbor = new Neighbor(first);
    }

    public Neighbor getNeighbor(Location second) {
        neighbor.setCellToSearch(second);
        return neighbor;
    }
}
