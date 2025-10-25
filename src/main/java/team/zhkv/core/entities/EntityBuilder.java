package team.zhkv.core.entities;

public class EntityBuilder {
    private static final int MAX_HEALTH = 100;

    public Entity buildEntity(Class<? extends Entity> clazz) {
        if (clazz == Predator.class) {
            return createPredator();
        } else if (clazz == Herbivore.class) {
            return createHerbivore();
        } else if (clazz == Grass.class) {
            return createGrass();
        } else if (clazz == Rock.class) {
            return createRock();
        } else if (clazz == Tree.class) {
            return createTree();
        } else {
            return null;
        }
    }

    private Predator createPredator() {
        return new Predator()
                .withDamage(50)
                .withHP(MAX_HEALTH)
                .withEdible(Herbivore.class)
                .withSpeed(1);
    }

    private Herbivore createHerbivore() {
        return new Herbivore()
                .withHP(MAX_HEALTH)
                .withEdible(Grass.class)
                .withSpeed(2);
    }

    private Grass createGrass() {
        return new Grass();
    }

    private Rock createRock() {
        return new Rock();
    }

    private Tree createTree() {
        return new Tree();
    }

}
