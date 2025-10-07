package team.zhkv.actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.entities.Creature;
import team.zhkv.entities.Damageble;
import team.zhkv.entities.Damager;
import team.zhkv.entities.Edible;
import team.zhkv.entities.Eater;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.entities.Predator;
import team.zhkv.move.Location;
import team.zhkv.render.GameMap;

public class TurnMove extends Turn {
    private final Logger logger = LoggerFactory.getLogger(
            TurnMove.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            List<Map<Location, Entity>> creaturesMaps = gm.getCreaturesMaps();
            Map<Location, Location> paths = new HashMap<>();
            for (int i = 0; i < creaturesMaps.size(); ++i) {
                Iterator<Entry<Location, Entity>> it = creaturesMaps.get(i)
                        .entrySet()
                        .iterator();
                while (it.hasNext()) {
                    var entry = it.next();
                    Creature creature = (Creature) entry.getValue();
                    Location step = creature.makeMove(gm, entry.getKey());
                    if (step != null) {
                        paths.put(entry.getKey(), step);
                    }
                }
                applyMoves(gm, paths);
                paths.clear();
            }
        } else {
            logger.error("""
                    Непарвильный тип класса подается в качестве аргумента в
                    класс TurnRender.
                    """);
        }
    }

    private void applyMoves(GameMap gm, Map<Location, Location> paths) {
        for (var path : paths.entrySet()) {
            Location start = path.getKey();
            Location target = path.getValue();
            Entity active = gm.getEntity(start);
            Entity passive = gm.getEntity(target);

            moveIfPossible(gm, start, target);
            damageIfPossible(gm, active, passive, target);
            eatIfPossible(gm, active, passive, target);
        }
    }

    private void eatIfPossible(GameMap gm, Entity active, Entity passive,
            Location target) {
        if (active instanceof Eater eater
                && passive instanceof Edible Edible
                && eater.getFood() == Edible.getClass()) {
            eater.eat(Edible);
            if (eater.getFood() == Grass.class || Edible.isEated()) {
                gm.removeEntity(target);
            }
        }
    }

    private void damageIfPossible(GameMap gm, Entity active, Entity passive,
            Location target) {
        if (active instanceof Damager damager
                && passive instanceof Damageble damageble) {
            damager.damage(damageble);
        }
    }

    private void moveIfPossible(GameMap gm, Location start, Location target) {
        if (gm.getEntity(target) == null) {
            Entity entity = gm.getEntity(start);
            var map = gm.getMapByEntity(entity.getClass());
            map.remove(start);
            map.put(target, entity);
        }
    }

}
