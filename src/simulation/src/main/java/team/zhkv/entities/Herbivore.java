package team.zhkv.entities;

public class Herbivore extends Creature implements Eatable, Eater, Damageble {
    @Override
    public void eat(Eatable entity) {
        if (hp < 100) {
            hp += hp;
        }
    }

    @Override
    public void takeDamage(int damage) {
        hp -= damage;
    }
}
