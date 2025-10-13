package team.zhkv.rendering;

import team.zhkv.map.GameMap;

public interface IRenderable {
    void render(GameMap gm, int iterateCount);
}
