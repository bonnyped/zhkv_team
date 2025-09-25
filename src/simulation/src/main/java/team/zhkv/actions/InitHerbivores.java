package team.zhkv.actions;

import team.zhkv.entities.Herbivore;
import team.zhkv.render.GameMap;

public class InitHerbivores extends Init {
    public InitHerbivores(GameMap gm) {
        super.gm = gm;
    }

    @Override
    public void init() {
        for (int i = 0; i < gm.differenceHerbivoreCountAndMin(); i++) {
            createIntities(gm, new Herbivore(1));
            gm.incrementHerbivoreCount();
        }
    }
}
