package team.zhkv.actions;

import team.zhkv.render.GameMap;

public abstract class Turn implements Action {
    GameMap gm;

    public abstract void turn();
}
