package team.zhkv.actions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.GameMap;
import team.zhkv.entities.Creature;
import team.zhkv.entities.Entity;
import team.zhkv.entities.Grass;
import team.zhkv.move.Coordinate;
import team.zhkv.service.impl.IDamageble;
import team.zhkv.service.impl.IDamager;
import team.zhkv.service.impl.IEater;
import team.zhkv.service.impl.IEdible;

public class TurnMove extends Turn {
    private static final Logger logger = LoggerFactory.getLogger(
            TurnMove.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            List<Map<Coordinate, Entity>> creaturesMaps = gm.getCreaturesMap();
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
        if (active instanceof IEater IEater
                && passive instanceof IEdible IEdible
                && IEater.getFood() == IEdible.getClass()) {
            IEater.eat(IEdible);
            if (IEater.getFood() == Grass.class || IEdible.isEated()) {
                gm.removeEntity(target);
            }
        }
    }

    private void damageIfPossible(GameMap gm, Entity active, Entity passive,
            Coordinate target) {
        if (active instanceof IDamager IDamager
                && passive instanceof IDamageble IDamageble) {
            IDamager.damage(IDamageble);
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
