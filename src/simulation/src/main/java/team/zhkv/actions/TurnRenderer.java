package team.zhkv.actions;

import team.zhkv.render.GameMap;

public class TurnRenderer extends Turn {
    public TurnRenderer(GameMap gm) {
        super.gm = gm;
    }

    @Override
    public void turn() {
        gm.render();
    }

}
