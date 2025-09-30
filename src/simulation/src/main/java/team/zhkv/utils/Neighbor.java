package team.zhkv.utils;

import team.zhkv.move.Location;

public class Neighbor {
    private Location pathLocation;
    private Location cellToSearch;

    public Neighbor(Location pathLocation, Location cellToSearch) {
        this.pathLocation = pathLocation;
        this.cellToSearch = cellToSearch;
    }

    public Location getPathLocation() {
        return this.pathLocation;
    }

    public Location getCellToSearch() {
        return this.cellToSearch;
    }
}
