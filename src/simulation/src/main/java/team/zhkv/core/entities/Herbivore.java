package team.zhkv.core.entities;

import team.zhkv.core.interfaces.IDamageble;
import team.zhkv.core.interfaces.IEdible;
import team.zhkv.core.interfaces.Respawnable;

public class Herbivore extends Creature implements IEdible, IDamageble,
        Respawnable {
    public Herbivore() {
        super(100, Grass.class, 2);
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

    @Override
    public void respawn() {

    }
}
