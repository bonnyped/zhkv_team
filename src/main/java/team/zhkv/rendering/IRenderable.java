package team.zhkv.rendering;

import team.zhkv.map.GameManager;

/**
 * Interface for rendering the game world.
 *
 * @author bonnyped
 */
public interface IRenderable {
    /**
     * Renders the current state of the game.
     *
     * @param gm the GameManager containing game state information
     */
    void render(GameManager gm);
}
