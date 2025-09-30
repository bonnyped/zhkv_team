package team.zhkv.entities;

public class Predator extends Creature implements Damager, Eater {
    private int damage = 50;

    @Override
    public void damage(Herbivore victim) {
        victim.hp -= damage;
    }

    @Override
    public int getDamagePower() {
        return damage;
    }

    @Override
    public void eat(Eatable herbivore) {
        if (hp < 50) {
            hp += hp;
        } else if (hp < 100) {
            hp = 100;
        }
    }
}
