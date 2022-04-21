import Components.cell;
import Components.grid;
import GUI.myFrame;
import MazeMaking.SideWinder;
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
        grid main = new grid(8000, 8000);
        myFrame f = new myFrame("Algorithm", main);
        mazeMakingAlgorithm m = new SideWinder();
        int deadEnds = 0;
        m.setDisplay(f);
        m.setR(420);
        m.makePath(main);
        for (int i = 0; i < main.getLaby().length; i++) {
            for (int j = 0; j < main.getLaby()[i].length; j++) {
                main.get(i, j).setVisited(false);
            }
        }
        long startTime = System.nanoTime();
        pathFindingAlgorithm finder = new Astar();//the algorithm we are using
        finder.setHeuristic(true);
//        pathFindingAlgorithm finder = new dijkstra();

        finder.setDisplay(f);
        int goalX = (int) (m.getR().nextDouble() * main.getLaby().length);
        int goalY = (int) (m.getR().nextDouble() * main.getLaby()[0].length);
        System.out.println("Goal Coordinates (X,Y): " + goalX + "," + goalY);
        f.repaint();
        finder.findPath(main, 0, 0, goalX, goalY);//x = 0 to width, y = 0 to height
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
//        m.setR(255);
//        m.makePath(main);
//        pathFindingAlgorithm finder = new recursiveBruteforce();
//        int startX = 0;
//        int startY = 0;
//        int destX = 4;
//        int destY= 3;
//        finder.findPath(main,startX,startY,destX,destY);
//        ArrayList<cell> t = finder.getSolution();
//        System.out.println();
        }
        int visitCount = 0;
        int validNeighCount = 0;
        for (int i = 0; i < main.getLaby().length; i++) {
            for (int j = 0; j < main.getLaby()[i].length; j++) {
                validNeighCount = 0;
                if(main.get(i,j).isVisited()){
                    visitCount++;
                    if(i!=goalX && j!=goalY) {
                        if (!(main.get(i, j).getCellNorth() == null)) {
                            validNeighCount++;
                        }
                        if (!(main.get(i, j).getCellEast() == null)) {
                            validNeighCount++;
                        }
                        if (!(main.get(i, j).getCellSouth() == null)) {
                            validNeighCount++;
                        }
                        if (!(main.get(i, j).getCellWest() == null)) {
                            validNeighCount++;
                        }
                        if (validNeighCount == 1) {//only one possible way to go
                            deadEnds++;
                        }
                    }
                }
            }
        }
        System.out.println("Deadends = "+deadEnds);
        System.out.println("Visited Nodes = "+visitCount);
    }
}
