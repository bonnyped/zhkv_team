package team.zhkv.entities;

public class Herbivore extends Creature implements Eatable {
    private int minCount;

    public Herbivore(int quantity, int minCount) {
        this.food = Grass.class;
        setEntitiesQuantityOnMap(quantity);
        this.minCount = minCount;
    }

    public Herbivore(int quantity) {
        this.food = Grass.class;
        setEntitiesQuantityOnMap(quantity);
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public int getMinCount() {
        return minCount;
    }

}
