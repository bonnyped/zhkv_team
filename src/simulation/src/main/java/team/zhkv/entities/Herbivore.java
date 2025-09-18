package team.zhkv.entities;

public class Herbivore extends Creature {
    public Herbivore() {
    }

    public Herbivore(int quantity) {
        setFieldQuantity(quantity / 5);
        goal = new Grass();
    }
}
