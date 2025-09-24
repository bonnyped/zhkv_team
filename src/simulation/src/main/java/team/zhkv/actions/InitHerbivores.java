package team.zhkv.actions;

import team.zhkv.entities.Herbivore;
import team.zhkv.render.GameMap;

public class InitHerbivores extends Init {
    @Override
    public void init(GameMap gm) {
        for (int i = 0; i < gm.differenceHerbivoreCountAndMin(); i++) {
            createIntities(gm, new Herbivore(1));
            gm.incrementHerbivoreCount();
        }
    }
}
