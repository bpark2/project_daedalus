import Components.cell;
import Components.grid;
import GUI.myFrame;
import MazeMaking.mazeMakingAlgorithm;
import MazeMaking.recursiveBacktracking;
import PathFinding.*;
import MazeMaking.SideWinder;

import java.util.ArrayList;

public class testskeleton {
    public static void main(String[] args){
        grid main = new grid(5,10);
        myFrame f = new myFrame("Algorithm",main);
        mazeMakingAlgorithm m = new recursiveBacktracking();
        m.setDisplay(f);
        m.setR(420);
        m.makePath(main);
        pathFindingAlgorithm finder = new randomWalk();
        finder.setDisplay(f);
        finder.findPath(main,0,0,5,5);//x = 0 to width, y = 0 to height
        ArrayList<cell> t = finder.getSolution();
        System.out.println("done");
        f.repaint();
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
