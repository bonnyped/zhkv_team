package team.zhkv.entities;

public abstract class Creature extends Entity implements Moveble {
    protected Class<? extends Entity> food;
    protected int hp = 100;

    protected int speed = 1;

    public Class<? extends Entity> getFood() {
        return food;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }
}
