package team.zhkv.entities;

import java.util.Map;

import team.zhkv.move.Location;
import team.zhkv.render.ChangeStorage;

public abstract class Creature extends Entity implements Moveble {
    protected Class<? extends Entity> food;
    protected Location nextStep;

    protected int hp = 100;

    protected int speed = 1;

    @Override
    public void makeMove(Map<Location, Entity> oldCreaturesLocations,
            Location oldLocation, ChangeStorage cs) {
        new StepFabric().build(oldCreaturesLocations, oldLocation, cs)
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

    public Location getNextStep() {
        return nextStep;
    }
}
