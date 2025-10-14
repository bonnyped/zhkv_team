package team.zhkv.actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.map.GameMap;
import team.zhkv.core.entities.Creature;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.entities.Grass;
import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.core.interfaces.IEater;
import team.zhkv.core.interfaces.IEdible;

public class TurnMove extends Turn {
    private static final Logger logger = LoggerFactory.getLogger(
            TurnMove.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Coordinate, Entity> creaturesMaps = gm.getCreaturesMap();
            Map<Coordinate, Coordinate> paths = new HashMap<>();

            for (int i = 0; i < creaturesMaps.size(); ++i) {
                Iterator<Entry<Coordinate, Entity>> it = creaturesMaps.get(i)
                        .entrySet()
                        .iterator();
                while (it.hasNext()) {
                    var entry = it.next();
                    Creature creature = (Creature) entry.getValue();
                    Coordinate step = creature.makeMove(gm, entry.getKey());
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

    private void applyMoves(GameMap gm, Map<Coordinate, Coordinate> paths) {
        for (var path : paths.entrySet()) {
            Coordinate start = path.getKey();
            Coordinate target = path.getValue();
            Entity active = gm.getEntity(start);
            Entity passive = gm.getEntity(target);

            moveIfPossible(gm, start, target);
            damageIfPossible(gm, active, passive, target);
            eatIfPossible(gm, active, passive, target);
        }
    }

    private void eatIfPossible(GameMap gm, Entity active, Entity passive,
            Coordinate target) {
        if (active instanceof IEater eater
                && passive instanceof IEdible edible
                && eater.getFood() == edible.getClass()) {
            eater.eat(edible);
            if (eater.getFood() == Grass.class || edible.isEated()) {
                gm.removeEntity(target);
            }
        }
    }

    private void damageIfPossible(GameMap gm, Entity active, Entity passive,
            Coordinate target) {
        if (active instanceof IDamager iDamager
                && passive instanceof IDamageble iDamageble) {
            iDamager.damage(iDamageble);
        }
    }

    private void moveIfPossible(GameMap gm, Coordinate start, Coordinate target) {
        if (gm.getEntity(target) == null) {
            Entity entity = gm.getEntity(start);
            var map = gm.getMapByEntity(entity.getClass());
            map.remove(start);
            map.put(target, entity);
        }
    }

}
