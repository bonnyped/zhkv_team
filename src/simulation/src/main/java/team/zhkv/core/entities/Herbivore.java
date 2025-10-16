package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.Respawnable;

public class Herbivore extends Creature implements IEdible, IDamageble,
        Respawnable {
    private boolean isEated;

    public Herbivore() {
        super(100, Grass.class, 2);
    }

    @Override
    public int getSpeed() {
        return getHp() < 100 ? 2 : 1;
    }

    @Override
    public void eat(IEdible edible) {
        setHp(getHp() + getHp());
        edible.setEated();
    }

    @Override
    public int takeDamage(int damage) {
        setHp(getHp() - damage);
        return getHp();
    }

    @Override
    public boolean isEated() {
        return isEated;
    }

    @Override
    public void setEated() {
        isEated = getHp() <= 0;
    }
}
