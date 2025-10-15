package team.zhkv.actions;

import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.map.GameMap;

public class TurnRemove extends Turn {
    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameMap.class) {
            GameMap gm = (GameMap) obj;
            Map<Coordinate, IEdible> edibles = gm.getEdiblesMap();

            for (var entry : edibles.entrySet()) {
                if (entry.getValue().isEated()) {
                    gm.removeEatedEntity(entry.getKey());
                }
            }
        }
    }

}
