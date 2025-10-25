package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.core.interfaces.IEdible;

public class Predator extends Creature<Predator> implements IDamager {
    private int damagePower;

    public Predator() {
        super(100, Herbivore.class, 1);
    }

    @Override
    public void giveDamage(Entity victim) {
        if (victim instanceof IDamageble damageble) {
            damageble.takeDamage(damagePower);
        }
    }

    @Override
    public void eat(Entity entity) {
        if (entity instanceof IEdible edible
                && entity instanceof Creature) {
            edible.setEated();
        }
    }

    public Predator withDamage(int damagePower) {
        this.damagePower = damagePower;
        return this;
    }
}
