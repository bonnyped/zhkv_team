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
    public void damage(IDamageble victim) {
        victim.takeDamage(damage);
    }

    @Override
    public int getDamagePower() {
        return damage;
    }

    @Override
    public void eat(IEdible herbivore) {
        if (hp < 50) {
            hp += hp;
        } else if (hp < 100) {
            hp += damage;
        }
    }
}
