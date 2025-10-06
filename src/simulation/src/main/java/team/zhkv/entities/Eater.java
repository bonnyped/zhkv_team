package team.zhkv.entities;

public interface Eater {
    void eat(Edible Edible);

    Class<? extends Entity> getFood();
}
