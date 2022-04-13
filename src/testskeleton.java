import Components.cell;
import Components.grid;
import GUI.myFrame;
import MazeMaking.mazeMakingAlgorithm;
import MazeMaking.pseudoBacktracking;
import PathFinding.*;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class testskeleton {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        grid main = new grid(100, 100);
        myFrame f = new myFrame("Algorithm", main);
        mazeMakingAlgorithm m = new pseudoBacktracking();
        m.setDisplay(f);
        m.setR(420);
        m.makePath(main);
        for (int i = 0; i < main.getLaby().length; i++) {
            for (int j = 0; j < main.getLaby()[i].length; j++) {
                main.get(i, j).setVisited(false);
            }
        }
        long startTime = System.nanoTime();
        pathFindingAlgorithm finder = new floodFill();//the algorithm we are using
        finder.setHeuristic(false);
//        pathFindingAlgorithm finder = new dijkstra();

        finder.setDisplay(f);
        int goalX = (int) (m.getR().nextDouble() * main.getLaby().length);
        int goalY = (int) (m.getR().nextDouble() * main.getLaby()[0].length);
        System.out.println("Goal Coordinates (X,Y): " + 9 + "," + 9);
        f.repaint();
        finder.findPath(main, 0, 0, 99, 0);//x = 0 to width, y = 0 to height
//        System.out.println(String.format("Memory : " + (double)test.getNonHeapMemoryUsage().getUsed()));
        System.out.println("Memory " + finder.getTotalMemory());
        ArrayList<cell> t = finder.getSolution();
        f.repaint();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("oh no");
        }
        for (int i = 0; i < main.getLaby().length; i++) {
            for (int j = 0; j < main.getLaby()[i].length; j++) {
                main.get(i, j).setPath(false);
            }
        }
        main.get(0,0).setPath(true);
        for (int i = 0; i < t.size(); i++) {
            t.get(i).setPath(true);
            f.repaint();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("no oh");
            }
        }
    }
}
