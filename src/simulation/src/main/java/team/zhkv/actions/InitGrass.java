package team.zhkv.actions;

import team.zhkv.entities.Grass;
import team.zhkv.render.GameMap;

public class InitGrass extends Init {
    public InitGrass(GameMap gm) {
        super.gm = gm;
    }

    @Override
    public void init() {
        for (int i = 0; i < gm.differenceGrassCountAndMin(); i++) {
            createIntities(gm, new Grass(1));
            gm.incrementGrassCount();
        }
    }
}
