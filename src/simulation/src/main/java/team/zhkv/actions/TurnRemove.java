package team.zhkv.actions;

import java.util.Map;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.map.GameManager;

public class TurnRemove extends Turn {
    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameManager.class) {
            GameManager gm = (GameManager) obj;
            Map<Coordinate, IEdible> edibles = gm.getEdiblesMap();

            for (var entry : edibles.entrySet()) {
                if (entry.getValue().isEaten()) {
                    gm.removeEatedEntity(entry.getKey());
                }
            }
        }
    }

}
