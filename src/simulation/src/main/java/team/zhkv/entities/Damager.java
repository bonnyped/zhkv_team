package team.zhkv.entities;

public interface Damager {
    void damage(Damageble victim);

    int getDamagePower();
}
