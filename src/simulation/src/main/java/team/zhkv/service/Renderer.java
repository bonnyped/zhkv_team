package team.zhkv.service;

import java.util.Map;

import team.zhkv.GameMap;
import team.zhkv.entities.Entity;
import team.zhkv.move.Location;
import team.zhkv.service.impl.Renderable;

public class Renderer implements Renderable {
    @Override
    public void render(GameMap gm, int iterateCount) {
        Map<Location, Entity> locations = gm.getWholeMapEntities();
        int height = gm.getFieldSize().getDx();
        int width = gm.getFieldSize().getDy();
        clearTerminal();
        printGameName();
        printSeparator(width, iterateCount);
        renderField(height, width, locations);
        printMenu();
    }

    private void renderField(int height, int width,
            Map<Location, Entity> locations) {
        Location currentLocation = new Location();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                currentLocation.setLocation(i, j);
                System.out.print(entityForRender(
                        locations.get(currentLocation)));
            }
            System.out.println();
        }
    }

    private void printSeparator(int width, int iterateCount) {
        StringBuilder sb = new StringBuilder();
        width -= determNumberLength(iterateCount);
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
}
