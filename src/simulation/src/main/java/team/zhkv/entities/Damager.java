package team.zhkv.entities;

public interface Damager {
    void damage(Herbivore victim);

    int getDamagePower();
}
