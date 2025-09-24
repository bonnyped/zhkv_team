package team.zhkv.entities;

public class Predator extends Creature {
    public Predator(int quantity) {
        this.food = Herbivore.class;
        setEntitiesQuantityOnMap(quantity / 5);
    }
}
