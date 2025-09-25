package team.zhkv.entities;

public abstract class Entity {
    int quantity;

    protected Entity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

}
