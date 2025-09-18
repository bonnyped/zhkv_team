package team.zhkv.entities;

public abstract class Entity {
    int fieldQuantity;
    Location location;

    public Location getLocation() {
        return location;
    }

    public int getFieldQuantity() {
        return fieldQuantity;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setFieldQuantity(int quantity) {
        this.fieldQuantity = quantity;
    }

}
