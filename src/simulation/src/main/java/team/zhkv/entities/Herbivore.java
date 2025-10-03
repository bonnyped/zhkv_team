package team.zhkv.entities;

public class Herbivore extends Creature implements Eatable, Damageble {
    public Herbivore() {
        food = Grass.class;
    }

    @Override
    public void eat(Eatable grass) {
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
