package team.zhkv.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.zhkv.render.MapRenderer;

public class TurnRender extends Turn {
    private final Logger logger = LoggerFactory.getLogger(
            TurnRender.class);

    @Override
    public void action(Object obj) {
        if (obj.getClass() == MapRenderer.class) {
            MapRenderer renderer = (MapRenderer) obj;
            renderer.render();
        } else {
            logger.error("""
                    Непарвильный тип класса подается в качестве аргумениты в
                    класс TurnRender.
                    """);
        }
    }

}
