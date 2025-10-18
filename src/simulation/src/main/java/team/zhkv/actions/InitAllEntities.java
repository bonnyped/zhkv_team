package team.zhkv.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.map.GameManager;

public class InitAllEntities extends Init {
    private static final Logger logger = LoggerFactory.getLogger(InitAllEntities.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameManager.class) {
            GameManager gm = (GameManager) obj;
            gm.spawnAllEntities();
        } else {
            logger.error("""
                    Непарвильный тип класса подается в качестве аргумениты в
                    класс InitAllEntities.
                    """);
        }
    }
}
