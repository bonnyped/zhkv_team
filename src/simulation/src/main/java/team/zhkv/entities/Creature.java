package team.zhkv.entities;

import java.util.HashMap;
import java.util.Map;

import team.zhkv.render.Location;

public abstract class Creature extends Entity implements Moveble {
    protected StepFabric stepFabric = new StepFabric();
    protected Class<? extends Entity> food;
    protected int hp = 100;

    protected int speed = 1;

    @Override
    public Map<Location, Entity> makeMove(Map<Location, Entity> oldCreaturesLocations,
            Location oldLocation) {
        Map<Location, Entity> newCreaturesLocations = new HashMap<>();
        Location newLocation = stepFabric.build(oldCreaturesLocations,
                newCreaturesLocations, oldLocation).gethNextStep();
        if (newLocation != oldLocation) {
            newCreaturesLocations.put(newLocation, oldCreaturesLocations.remove(oldLocation));
        }

        return newCreaturesLocations;
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
