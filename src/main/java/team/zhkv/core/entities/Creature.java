package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IEater;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.IMoveble;

public abstract class Creature extends Entity implements IMoveble, IEater {
    private int hp;
    private Class<? extends IEdible> food;
    private int speed;

    protected Creature(int hp, Class<? extends IEdible> food, int speed) {
        this.hp = hp;
        this.food = food;
        this.speed = speed;
    }

    public Class<? extends IEdible> getFood() {
        return food;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp > 100 ? 100 : hp;
    }

    public int getSpeed() {
        return speed;
    }
}
