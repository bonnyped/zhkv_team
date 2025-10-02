package team.zhkv.render;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import team.zhkv.entities.Entity;
import team.zhkv.move.Location;

public class ChangeStorage {
    private Map<Location, Location> toMove = new HashMap<>();

    private Map<Location, Entity> toCreate = new HashMap<>();

    private Set<Location> toRemove = new HashSet<>();

    public Map<Location, Location> getToMove() {
        return toMove;
    }

    public Map<Location, Entity> getToCreate() {
        return toCreate;
    }

    public Set<Entry<Location, Entity>> getToCreateEntrySet() {
        return this.toCreate.entrySet();
    }

    public Set<Location> getToRemove() {
        return this.toRemove;
    }
}
