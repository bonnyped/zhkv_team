package team.zhkv.entities;

public class Herbivore extends Creature implements Edible, Damageble {
    public Herbivore() {
        food = Grass.class;
        speed = 2;
    }

    @Override
    public void eat(Edible grass) {
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
