package team.zhkv.render;

import java.util.HashMap;
import java.util.Map;

import team.zhkv.entities.Entity;

public class MapChanger {
    protected Map<Location, Entity> grass = new HashMap<>();
    protected Map<Location, Entity> herbivores = new HashMap<>();
    protected Map<Location, Entity> predators = new HashMap<>();
    protected Map<Location, Entity> entitiesToRemove = new HashMap<>();
}
