package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.IRespawnable;

public class Herbivore extends Creature implements IEdible, IDamageble,
        IRespawnable {
    private boolean isEated;

    public Herbivore() {
        super(100, Grass.class, 2);
    }

    @Override
    public void eat(Entity entity) {
        if (entity instanceof IEdible edible) {
            edible.setEated();
        }
    }

    @Override
    public int takeDamage(int damage) {
        setHp(getHp() - damage);
        return getHp();
    }

    @Override
    public void setEated() {
        isEated = true;
    }

    @Override
    public boolean isEaten() {
        return isEated;
    }
}
