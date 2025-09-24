package team.zhkv.entities;

public class Grass extends Entity implements Eatable {
    public Grass(int quantity) {
        setEntitiesQuantityOnMap(quantity / 5);
    }
}
