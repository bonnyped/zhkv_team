package team.zhkv.entities;

public interface Eater {
    void eat(Eatable eatable);

    Class<? extends Entity> getFood();
}
