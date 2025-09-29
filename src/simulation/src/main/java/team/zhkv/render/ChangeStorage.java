package team.zhkv.render;

import java.util.HashMap;
import java.util.Map;

import team.zhkv.entities.Entity;

public class ChangeStorage {
    private Map<Location, Entity> grass = new HashMap<>();
    private Map<Location, Entity> herbivores = new HashMap<>();
    private Map<Location, Entity> predators = new HashMap<>();
    private Map<Location, Entity> entitiesToRemove = new HashMap<>();

    public Map<Location, Entity> getGrass() {
        return this.grass;
    }

    public Map<Location, Entity> getHerbivores() {
        return this.herbivores;
    }

    public Map<Location, Entity> getPredators() {
        return this.predators;
    }

    public Map<Location, Entity> getEntitiesToRemove() {
        return this.entitiesToRemove;
    }

    public boolean isEmpty() {
        return grass.isEmpty()
                && herbivores.isEmpty()
                && predators.isEmpty()
                && entitiesToRemove.isEmpty();
    }
}
