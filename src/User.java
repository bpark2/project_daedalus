import Components.cell;
import Components.grid;
import GUI.myFrame;
import MazeMaking.SideWinder;
import MazeMaking.mazeMakingAlgorithm;
import MazeMaking.pseudoBacktracking;
import PathFinding.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class User {
    User(){
        myFrame f = new myFrame("Algorithms");
        Scanner s = new Scanner(System.in);
        System.out.println("welcome user to our project!");
        while(true) {
            System.out.println("Enter your width and height , or input 0 to end!");
            int width = s.nextInt();
            int height = s.nextInt();
            if(width<=0||height<=0)
                break;
            grid main = new grid(height, width);//this is the main grid we are going to be using
            f.setLabyrinth(main);
            System.out.println("Enter which algorithm you want to use for maze making \n0. Sidewinder \n1. Iterative bactracker");
            boolean flag = false;//indicating if user input bad input
            mazeMakingAlgorithm m = null;
            switch (s.nextInt()){
                case 0: m = new SideWinder();
                    System.out.println("Input your random seed");
                    m.setDisplay(f);
                    m.setR(s.nextInt());
                    m.makePath(main);
                    break;
                case 1: m = new pseudoBacktracking();
                    m.setDisplay(f);
                    System.out.println("Input your random seed");
                    m.setR(s.nextInt());
                    m.makePath(main);
                    break;
                default : flag = true;
            }
            if(flag)
                continue;
            f.repaint();
            main.reset();
            System.out.println("Enter your intial X and Y positions");
            int X = s.nextInt();
            int Y = s.nextInt();
            System.out.println("Enter your destination X and Y positions");
            int destX = s.nextInt();
            int destY = s.nextInt();
            System.out.println("Enter Your choice of Algorithm \n0.Random walk\n1.Brute force\n2.Dijkstra's\n3.A-Star Manhattan\n4.A-Star Euclidean\n5.Flood Fill");
            flag = false;
            pathFindingAlgorithm finder = null;
            switch (s.nextInt()){
                case 0:
                    finder = new iterativeRandomWalk();
                    break;
                case 1:
                    finder = new iterativeBruteForce();
                    break;
                case 2:
                    finder = new dijkstra();
                    break;
                case 3:
                    finder = new Astar();
                    finder.setHeuristic(true);
                    break;
                case 4:
                    finder = new Astar();
                    finder.setHeuristic(false);
                    break;
                case 5:
                    finder = new floodFill();
                    break;
                default : flag = true;
            }
            if(flag)
                continue;
            finder.setDisplay(f);
            Runtime.getRuntime().gc();
            long starttime = System.currentTimeMillis();
            finder.findPath(main,X,Y,destX,destY);
            long time = System.currentTimeMillis() - starttime;
            ArrayList<cell> t = finder.getSolution();
            f.repaint();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("oh no");
            }
            main.reset();
            for (int i = 0; i < t.size(); i++) {//this is the loop
                t.get(i).setPath(true);
                f.repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("no oh");
                }
            }//end of path
            System.out.println("Time : " + (time));
            System.out.println("Memory : " + finder.getTotalMemory());
            System.out.println("Visted : "+ main.countVisted(X,Y,destX,destY));
            System.out.println("deadends : "+ main.countDeadEnds(X,Y,destX,destY));
            System.out.println();
        }
    }
    public static void main(String[] args){
        new User();
    }
}
