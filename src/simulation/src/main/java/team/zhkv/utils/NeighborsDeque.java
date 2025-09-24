package team.zhkv.utils;

import java.util.ArrayDeque;
import java.util.List;
import team.zhkv.render.Location;

public class NeighborsDeque extends ArrayDeque<Neighbor> {
    public boolean addFirstNeighbors(List<Location> locations) {
        Location firstNeighbor = null;
        Location nextSearchStep = null;
        for (int i = 0; i < locations.size(); i++) {
            firstNeighbor = locations.get(i);
            nextSearchStep = firstNeighbor;
            NeighborFabric fabric = new NeighborFabric(firstNeighbor, nextSearchStep);
            this.add(fabric.getNeighbor());
        }

        return true;
    }

    public boolean addAllNeighbors(Location startLocation, List<Location> locations) {
        for (int i = 0; i < locations.size(); i++) {
            NeighborFabric fabric = new NeighborFabric(startLocation, locations.get(i));
            this.add(fabric.getNeighbor());
        }

        return true;
    }
}
