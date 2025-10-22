package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IDamager;
import team.zhkv.core.interfaces.IEdible;

public class Predator extends Creature implements IDamager {
    private int damage = 50;

    public Predator() {
        super(100, Herbivore.class, 1);
    }

    @Override
    public void giveDamage(Entity victim) {
        if (victim instanceof IDamageble damageble) {
            damageble.takeDamage(damage);
        }
    }

    @Override
    public void eat(Entity entity) {
        if (entity instanceof IEdible edible
                && entity instanceof Creature) {
            edible.setEated();
        }
    }
}
