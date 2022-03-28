import Componenets.cell;
import Componenets.grid;
import MazeMaking.mazeMakingAlgorithm;
import PathFinding.*;
import MazeMaking.SideWinder;

import java.util.ArrayList;

public class testskeleton {
    public static void main(String[] args){
        grid main = new grid(5,5);
        mazeMakingAlgorithm m = new SideWinder();
        m.setR(255);
        m.makePath(main);
        pathFindingAlgorithm finder = new recursiveBruteforce();
        int startX = 0;
        int startY = 0;
        int destX = 4;
        int destY= 3;
        finder.findPath(main,startX,startY,destX,destY);
        ArrayList<cell> t = finder.getSolution();
        System.out.println();
    }
}
