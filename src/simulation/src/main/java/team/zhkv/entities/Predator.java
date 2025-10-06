package team.zhkv.entities;

public class Predator extends Creature implements Damager {
    private int damage = 50;

    public Predator() {
        food = Herbivore.class;
        speed = 1;
    }

    @Override
    public void damage(Damageble victim) {
        victim.takeDamage(damage);
    }

    @Override
    public int getDamagePower() {
        return damage;
    }

    @Override
    public void eat(Edible herbivore) {
        if (hp < 50) {
            hp += hp;
        } else if (hp < 100) {
            hp += damage;
        }
    }
}
