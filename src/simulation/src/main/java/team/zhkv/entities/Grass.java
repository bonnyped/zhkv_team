package team.zhkv.entities;

public class Grass extends Entity {
    public Grass() {
        setFieldQuantity(0);
    }

    public Grass(int quantity) {
        setFieldQuantity(quantity / 5);
    }
}
