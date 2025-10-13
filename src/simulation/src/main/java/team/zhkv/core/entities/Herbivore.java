package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IEdible;

public class Herbivore extends Creature implements IEdible, IDamageble {
    public Herbivore() {
        food = Grass.class;
        speed = 2;
    }

    @Override
    public void eat(IEdible grass) {
        if (hp < 100) {
            hp += hp;
        }
    }

    @Override
    public int takeDamage(int damage) {
        hp -= damage;
        return hp;
    }

    @Override
    public boolean isEated() {
        return hp <= 0;
    }
}
