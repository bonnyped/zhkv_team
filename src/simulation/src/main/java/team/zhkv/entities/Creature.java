package team.zhkv.entities;

import java.util.List;
import java.util.Map;

import team.zhkv.move.Location;
import team.zhkv.render.GameMap;

public abstract class Creature extends Entity implements Moveble, Eater {
    protected Class<? extends Entity> food;
    protected boolean isEated;

    protected int hp = 100;

    protected int speed;

    @Override
    public Location makeMove(GameMap gm, Location oldLocation) {
        List<Location> path = new BFS().build(gm, oldLocation)
                .getPath();

        return path.isEmpty() ? null : path.get(speed - 1);
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
