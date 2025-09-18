package team.zhkv.entities;

public abstract class Creature extends Entity implements CreaturesGoalGetter {
    Entity goal;

    public Entity getGoal() {
        return goal;
    }
}
