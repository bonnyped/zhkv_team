
package team.zhkv.rendering;

import java.util.Map;

import team.zhkv.map.GameManager;
import team.zhkv.core.entities.*;
import team.zhkv.actions.move.Coordinate;

@SuppressWarnings({ "java:S106", "java:S6126" })
public class Renderer implements IRenderable {
    @Override
    public void render(GameManager gm) {
        clearTerminal();
        printGameName();
        printSeparator(gm.getTurnCount());
        renderField(gm.getEntities());
        printMenu();
    }

    private void renderField(Map<Coordinate, Entity> coordinates) {
        Coordinate currentCoordinate = new Coordinate();
        for (int i = 0; i < GameManager.DY; i++) {
            for (int j = 0; j < GameManager.DX; j++) {
                currentCoordinate.setCoordinate(j, i);
                System.out.print(entityForRender(
                        coordinates.get(currentCoordinate)));
            }
            System.out.println();
        }
    }

    private void printSeparator(int iterateCount) {
        StringBuilder sb = new StringBuilder();
        int width = GameManager.DX - determNumberLength(iterateCount);
        for (int i = 0; i < width; i++) {
            sb.append("-");
        }
        System.out.println(sb.toString()
                + " " + iterateCount
                + " " + sb.toString());
    }

    private int determNumberLength(int iterateCount) {
        int length = 0;

        while (iterateCount > 0) {
            ++length;
            iterateCount /= 10;
        }
        return length;
    }

    private void clearTerminal() {
        System.out.println("\033[2J\033[H");
    }

    @SuppressWarnings("java:S106")
    private void printGameName() {
        System.out.println("\n\n");
        System.out.println("     ▗▄▄▖▗▄▄▄▖▗▖  ▗▖▗▖ ▗▖▗▖    ▗▄▖ ▗▄▄▄▖▗▄▄▄▖ ▗▄▖ ▗▖  ▗▖\n" + //
                "    ▐▌     █  ▐▛▚▞▜▌▐▌ ▐▌▐▌   ▐▌ ▐▌  █    █  ▐▌ ▐▌▐▛▚▖▐▌\n" + //
                "     ▝▀▚▖  █  ▐▌  ▐▌▐▌ ▐▌▐▌   ▐▛▀▜▌  █    █  ▐▌ ▐▌▐▌ ▝▜▌\n" + //
                "    ▗▄▄▞▘▗▄█▄▖▐▌  ▐▌▝▚▄▞▘▐▙▄▄▖▐▌ ▐▌  █  ▗▄█▄▖▝▚▄▞▘▐▌  ▐▌\n" + //
                "                                                    \n" + //
                "                                                    \n" + //
                "                                                    ");
    }

    private void printMenu() {
        System.out.println("\n\n\n=========================== МЕНЮ ===========================");
        System.out.println();
        System.out.println("                1. p — pause/resume");
        System.out.println("             2. cntrl + с — quit program");
        System.out.println();
    }

    private String entityForRender(Entity entity) {
        if (entity == null) {
            return "⬛️";
        } else if (entity.getClass() == Predator.class) {
            return "🐺";
        } else if (entity.getClass() == Herbivore.class) {
            return "🐰";
        } else if (entity.getClass() == Grass.class) {
            return "🥕";
        } else if (entity.getClass() == Rock.class) {
            return "🪨 ";
        } else if (entity.getClass() == Tree.class) {
            return "🌲";
        } else {
            throw new RuntimeException(
                    "Class is not apllicable with renderer!");
        }
    }
}
