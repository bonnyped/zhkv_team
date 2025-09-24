package team.zhkv.entities;

public class Grass extends Entity implements Eatable {
    private int minCount;

    public Grass(int quantity, int minCount) {
        setEntitiesQuantityOnMap(quantity);
        this.minCount = minCount;
    }

    public Grass(int quantity) {
        setEntitiesQuantityOnMap(quantity);
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public int getMinCount() {
        return minCount;
    }
}
