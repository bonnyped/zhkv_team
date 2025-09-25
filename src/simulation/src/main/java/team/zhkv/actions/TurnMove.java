package team.zhkv.actions;

import java.util.HashSet;
import java.util.Set;

import team.zhkv.entities.Creature;
import team.zhkv.render.GameMap;
import team.zhkv.render.Location;

public class TurnMove extends Turn {
    public TurnMove(GameMap gm) {
        super.gm = gm;
    }

    @Override
    public void turn() {
        Set<Location> newCreaturesLocations = new HashSet<>();

        for (Location creature : gm.getCreatures()) {
            Creature extarctedCreature = (Creature) gm.getLocations()
                    .get(creature);
            extarctedCreature.makeMove(gm,
                    creature, newCreaturesLocations);
        }

        gm.setCreatures(newCreaturesLocations);
    }
}
