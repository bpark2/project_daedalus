import Components.grid;
import MazeMaking.SideWinder;
import MazeMaking.mazeMakingAlgorithm;
import MazeMaking.pseudoBacktracking;
import MazeMaking.recursiveBacktracking;
import PathFinding.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class testautomate {
    /**
     * this function runs the tests for a given set of parameters
     * @param finder the path finding algorithm we are testing
     * @param heuristic this is only relavent we are using Astar, true indicates Manhattan, and false Euclidean
     * @param x the origin x
     * @param y the origin y
     * @param destx the destination x
     * @param desy the destination y
     * @param main the grid we are trying to solve.
     */
    public static void runTest(pathFindingAlgorithm finder,boolean heuristic, int x,int y, int destx, int desy, grid main){
        long startTime = System.currentTimeMillis();
        finder.setHeuristic(heuristic);
        finder.findPath(main,x,y,destx,desy);
        System.out.println("Time : " + (System.currentTimeMillis() - startTime));
        System.out.println("Memory : " + finder.getTotalMemory());
        System.out.println("Visted : "+ main.countVisted(x,y,destx,desy));
        System.out.println("deadends : "+ main.countDeadEnds(x,y,destx,desy));
        System.out.println();
    }
    public static void main(String[] args) throws FileNotFoundException {
        int[] seeds = {420,772};//different seeds we ar eusing
        int[][] lengths = {
                {1,500},
                {30,30},
                {50,50},
                {70,100},
                {100,100}
        };//different sizes of mazes we are using
        int[][] starts = {
                {0,0},
                {0,0},
                {0,0},
                {0,0},
                {0,0}
        };//starting postions for mazes, 2 per box
        int[][] goals = {
                {0,499},
                {0,29},
                {0,49},
                {0,99},
                {0,99}
        };//there are 2 goals per box, and the format is [x,y]
        for(int i = 0; i<seeds.length;i++){//for each seed
            PrintStream o;
            o = new PrintStream(new File("seed"+i+".txt"));
            System.setOut(o);//setting it so we are going save the data
            for (int j = 0; j < 2; j++) {//for each maze making algorithm
                for (int k = 0; k < lengths.length; k++) {//number of sizes
                    mazeMakingAlgorithm m = null;
                    grid main = new grid(lengths[k][0],lengths[k][1]);
                    switch (j) {
                        case 0:
                            m = new SideWinder();
                            break;
                        case 1:
                            m = new pseudoBacktracking();
                            break;
                    }

                    m.setR(seeds[i]);
                    m.makePath(main);//make the path

                    for (int l = 0; l < 6; l++) {//number of path finding algorithm
                        for (int n = k; n <= k; n++) {//for number of goals and starts to consider, here k * 2 is 2 goals per mazesize
                            main.reset();//resets variables
                            Runtime.getRuntime().gc();
//
                            switch (l) {
                                case 0://Astar with manhattan
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("Astar Manhattan , s : "+i+" , malgo : "+j+" , size : "+k);
                                    runTest(new Astar(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;
                                case 1://astar with euclidean
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("Astar Euclidean , s : "+i+" , malgo : "+j+" , size : "+k);
                                    runTest(new Astar(), false, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;
                                case 2://dijkstra
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("Dijkstra , s : "+i+" , malgo : "+j+" , size : "+k);
                                    runTest(new dijkstra(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;
                                case 3://floodfill
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("Floodfill , s : "+i+" , malgo : "+j+" , size : "+k);
                                    runTest(new floodFill(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;
                                case 4://iterative brute force
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("iterative Brute Force , s : "+i+" , malgo : "+j+" , size : "+k);
                                    runTest(new iterativeBruteForce(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;
                                case 5://iterative randomwalk
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("Iterative random walk , s : "+i+" , malgo : "+j+" , size : "+k);
                                    runTest(new iterativeRandomWalk(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;

                            }
                        }//end of n
                    }//end of l
                }//end of k
            }//end of j
        }//end of i
    }
}
