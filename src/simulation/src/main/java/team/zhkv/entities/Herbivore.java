package team.zhkv.entities;

public class Herbivore extends Creature implements Eatable {
    public Herbivore(int quantity) {
        this.food = Grass.class;
        setEntitiesQuantityOnMap(quantity / 5);
    }

}
