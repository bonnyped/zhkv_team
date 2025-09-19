package team.zhkv.utils;

import team.zhkv.entities.Location;

public class Neighbor {
    private Location nearestLocation;
    private Location cellToSearch;

    public Neighbor(Location nearestLocation) {
        this.nearestLocation = nearestLocation;
    }

    public Location getNearestLocation() {
        return this.nearestLocation;
    }

    public Location getCellToSearch() {
        return this.cellToSearch;
    }

    public Neighbor setCellToSearch(Location cellToSearch) {
        this.cellToSearch = cellToSearch;
        return this;
    }

}
