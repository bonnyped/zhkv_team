package team.zhkv.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.map.GameState;

public class InitAllEntities extends Init {
    private static final Logger logger = LoggerFactory.getLogger(InitAllEntities.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == GameState.class) {
            GameState gs = (GameState) obj;
            gs.createAllEntities();
        } else {
            logger.error("""
                    Непарвильный тип класса подается в качестве аргумениты в
                    класс InitAllEntities.
                    """);
        }
    }
}
