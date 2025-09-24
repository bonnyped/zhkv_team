package team.zhkv.actions;

import team.zhkv.entities.Grass;
import team.zhkv.render.GameMap;

public class InitGrass extends Init {
    @Override
    public void init(GameMap gm) {
        for (int i = 0; i < gm.differenceGrassCountAndMin(); i++) {
            createIntities(gm, new Grass(1));
            gm.incrementGrassCount();
        }
    }
}
