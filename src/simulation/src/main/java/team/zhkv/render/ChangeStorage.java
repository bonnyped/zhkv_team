package team.zhkv.render;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.diogonunes.jcdp.color.api.Ansi.BColor;

import team.zhkv.entities.Entity;
import team.zhkv.move.Location;

public class ChangeStorage {
    private Map<Location, Entity> toCreate = new HashMap<>();

    private Set<Location> toRemove = new HashSet<>();
    private Map<Location, Location> toMove = new HashMap<>();

    public Map<Location, Entity> getToCreate() {
        return toCreate;
    }

    public Set<Entry<Location, Entity>> getToCreateEntrySet() {
        return this.toCreate.entrySet();
    }

    public Map<Location, Location> getToMove() {
        return this.toMove;
    }

    public Set<Location> getToRemove() {
        return this.toRemove;
    }

    public Location getPassiveEntity(Location activeEntity) {
        if (toCreate.containsKey(activeEntity)) {
            return activeEntity;
        } else if (toMove.containsKey(activeEntity)) {
            return toMove.get(activeEntity);
        } else if (toRemove.contains(activeEntity)) {
            return activeEntity;
        }

        return null;
    }

    public BColor getColorOfAction(Location activeEntity) {
        if (toCreate.containsKey(activeEntity)) {
            return BColor.GREEN;
        } else if (toMove.containsKey(activeEntity)) {
            return BColor.YELLOW;
        } else {
            return BColor.NONE;
        }
    }

}
