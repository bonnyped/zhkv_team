package team.zhkv.entities;

public interface CreaturesGoalGetter {
    default Class<?> getCreaturesGoal(Entity entity) {
        return entity.getClass();
    }
}
