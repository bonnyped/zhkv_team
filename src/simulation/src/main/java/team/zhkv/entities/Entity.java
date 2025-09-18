package team.zhkv.entities;

public abstract class Entity {
    private int fieldQuantity;
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Entity location(Location location) {
        setLocation(location);
        return this;
    }

    public int getFieldQuantity() {
        return fieldQuantity;
    }

    public void setFieldQuantity(int quantity) {
        this.fieldQuantity = quantity;
    }

}
