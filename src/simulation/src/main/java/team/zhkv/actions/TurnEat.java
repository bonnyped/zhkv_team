package team.zhkv.actions;

import team.zhkv.actions.move.Coordinate;
import team.zhkv.core.entities.Entity;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.map.GameManager;

public class TurnEat extends Turn {
    @Override
    public void action(GameManager gm) {
        gm.eatAllEdibleEntities();
    }

    /*
     * остановился тут, продолжаю рефакторить, нужно понять можно ли выести эти два
     * метода ниже в EM
     * тот же самый вопрос и для TurnDamage
     */
    private IEdible getGoalEntity(GameManager gm, Coordinate coordinate) {
        Entity entity = gm.getEntity(coordinate);
        if (IEdible.class.isAssignableFrom(entity.getClass())) {
            return (IEdible) entity;
        } else {
            return null;
        }
    }

    private boolean isFoodFound(Class<? extends Entity> food, IEdible goal) {
        return goal.getClass().equals(food);
    }
}
