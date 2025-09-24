package team.zhkv.entities;

public class Rock extends Entity {
    public Rock(int quantity) {
        setEntitiesQuantityOnMap(quantity / 8);
    }
}
