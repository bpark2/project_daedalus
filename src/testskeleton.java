import Components.cell;
import Components.grid;
import GUI.myFrame;
import MazeMaking.mazeMakingAlgorithm;
import MazeMaking.pseudoBacktracking;
import MazeMaking.recursiveBacktracking;
import PathFinding.*;
import MazeMaking.SideWinder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class testskeleton {
    public static void main(String[] args) {
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
        pathFindingAlgorithm finder = new iterativeRandomWalk();
        finder.setDisplay(f);
        int goalX = (int) (m.getR().nextDouble() * main.getLaby().length);
        int goalY = (int) (m.getR().nextDouble() * main.getLaby()[0].length);
        System.out.println("Goal Coordinates (X,Y): " + goalX + "," + goalY);
        f.repaint();
        finder.findPath(main, 0, 0, goalX, goalY);//x = 0 to width, y = 0 to height
        ArrayList<cell> t = finder.getSolution();
        f.repaint();
        System.out.println("done");
        try {
            TimeUnit.SECONDS.sleep(5);
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
    }
}
