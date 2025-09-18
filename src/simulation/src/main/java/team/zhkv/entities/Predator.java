package team.zhkv.entities;

public class Predator extends Creature {
    public Predator(int quantity) {
        setFieldQuantity(quantity / 5);
        goal = new Herbivore();
    }
}
