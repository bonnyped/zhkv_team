package team.zhkv.utils;

import java.util.ArrayDeque;
import java.util.List;

import team.zhkv.entities.Location;

public class NeighborsDeque extends ArrayDeque<Neighbor> {
    public boolean addNearestNeighbors(List<Location> locations) {
        for (int i = 0; i < locations.size(); i++) {
            NeighborFabric fabric = new NeighborFabric(locations.get(i));
            this.add(fabric.getNeighbor(locations.get(i)));
        }

        return true;
    }

    public boolean addAllNeighbors(Location startLocation, List<Location> locations) {
        NeighborFabric fabric = new NeighborFabric(startLocation);

        for (int i = 0; i < locations.size(); i++) {
            this.add(fabric.getNeighbor(locations.get(i)));
        }

        return true;
    }
}
