package team.zhkv.entities;

public class Tree extends Entity {
    public Tree(int quantity) {
        setEntitiesQuantityOnMap(quantity / 8);
    }
}
