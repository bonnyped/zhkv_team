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
    public void giveDamage(IDamageble victim) {
        if (victim != null) {
            victim.takeDamage(damage);
        }
    }

    @Override
    public void eat(IEdible edible) {
        if (edible.getHp() <= 0) {
            edible.setEated();
        }
    }
}
