package team.zhkv.render;

import java.util.Map;

import team.zhkv.entities.Entity;
import team.zhkv.entities.Location;

public class MapRenderer {
    private Map<Location, Entity> entities;
    private String emojiPredator = "🐅";

    public MapRenderer(Map<Location, Entity> entities) {
        this.entities = entities;
    }

    public void render() {
        int sizex = 10;
        int sizey = 10;
        System.out.println(emojiPredator);
    }
}
