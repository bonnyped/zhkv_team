package team.zhkv.entities;

import java.util.List;

import team.zhkv.move.Location;
import team.zhkv.render.GameMap;

public abstract class Creature extends Entity implements Moveble, Eater {
    protected Class<? extends Entity> food;
    protected boolean isEated;

    protected int hp = 100;

    protected int speed = 1;

    @Override
    public List<Location> makeMove(GameMap gm, Location oldLocation) {
        return new BFS().build(gm, oldLocation)
                .getNextStep();
    }

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
