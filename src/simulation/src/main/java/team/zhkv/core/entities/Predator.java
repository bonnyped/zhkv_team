package team.zhkv.entities;

import team.zhkv.service.impl.IDamageble;
import team.zhkv.service.impl.IDamager;
import team.zhkv.service.impl.IEdible;

public class Predator extends Creature implements IDamager {
    private int damage = 50;

    public Predator() {
        food = Herbivore.class;
        speed = 1;
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
