package team.zhkv.render;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.diogonunes.jcdp.color.api.Ansi.BColor;

import team.zhkv.entities.Entity;
import team.zhkv.move.Location;

public class ChangeStorage {
    private Map<Location, Entity> toCreate = new HashMap<>();
    private Map<Location, Location> toEat = new HashMap<>();
    private Map<Location, Location> toDamage = new HashMap<>();
    private Map<Location, Location> toMove = new HashMap<>();
    private Set<Location> toRemove = new HashSet<>();

    public Map<Location, Entity> getToCreate() {
        return this.toCreate;
    }

    public Map<Location, Location> getToMove() {
        return this.toMove;
    }

    public Set<Location> getToRemove() {
        return this.toRemove;
    }

    public Map<Location, Location> getToEat() {
        return this.toEat;
    }

    public Map<Location, Location> getToDamage() {
        return this.toDamage;
    }

    public Location getPassiveEntity(Location activeEntity) {
        if (toCreate.containsKey(activeEntity)) {
            return activeEntity;
        } else if (toEat.containsKey(activeEntity)) {
            return toEat.get(activeEntity);
        } else if (toDamage.containsKey(activeEntity)) {
            return toDamage.get(activeEntity);
        } else if (toMove.containsKey(activeEntity)) {
            return toMove.get(activeEntity);
        } else if (toRemove.contains(activeEntity)) {
            return activeEntity;
        }

        return null;
    }

    public BColor getColorOfAction(Location activeEntity) {
        if (toDamage.containsKey(activeEntity)
                || toRemove.contains(activeEntity)) {
            return BColor.RED;
        } else if (toEat.containsKey(activeEntity)) {
            return BColor.CYAN;
        } else if (toCreate.containsKey(activeEntity)) {
            return BColor.GREEN;
        } else if (toMove.containsKey(activeEntity)) {
            return BColor.YELLOW;
        } else {
            return BColor.NONE;
        }
    }

}
