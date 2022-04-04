import Components.cell;
import Components.grid;
import GUI.myFrame;
import MazeMaking.mazeMakingAlgorithm;
import MazeMaking.pseudoBacktracking;
import MazeMaking.recursiveBacktracking;
import PathFinding.*;
import MazeMaking.SideWinder;

import java.util.ArrayList;

public class testskeleton {
    public static void main(String[] args){
        grid main = new grid(100,200);
        myFrame f = new myFrame("Algorithm",main);
        mazeMakingAlgorithm m = new pseudoBacktracking();
        m.setDisplay(f);
        m.setR(420);
        m.makePath(main);
        for (int i = 0; i < main.getLaby().length; i++) {
            for (int j = 0; j < main.getLaby()[i].length; j++) {
                main.get(i,j).setVisited(false);
            }
        }
        pathFindingAlgorithm finder = new randomWalk();
        finder.setDisplay(f);
        finder.findPath(main,0,0,15,15);//x = 0 to width, y = 0 to height
        ArrayList<cell> t = finder.getSolution();
        //System.out.println("done");
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
