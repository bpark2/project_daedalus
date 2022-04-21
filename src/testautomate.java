import Components.grid;
import MazeMaking.SideWinder;
import MazeMaking.mazeMakingAlgorithm;
import MazeMaking.pseudoBacktracking;
import MazeMaking.recursiveBacktracking;
import PathFinding.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

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
        int[] seeds = {420,352,772,17542};//different seeds we ar eusing
//        String[] files = //have to be ordered as the switch statement
        int numberOfgoalspermaze = 2;//this is the number of starts and goals we have for a given maze
        int recursiveMaxSizeforMaze = 50;//the max size for all recursive algorithms
        int[][] lengths = {
                {100000,1},
                {1,100000},
                {10000,1},
                {1,10000},
                {30,30},
                {100,100},
                {500,500},
                {500,600},
                {100,700},
                {900,500},
                {1000,400}
        };//different sizes of mazes we are using
        int[][] starts = {
                {99999,0},{50000,0},
                {0,99999},{0,50000},
                {9999,0},{5000,0},
                {0,9999},{0,5000},
                {25,25},{29,1},
                {99,99},{78,99},
                {499,499},{250,150},
                {499,599},{250,200},
                {99,699},{0,699},
                {899,499},{899,250},
                {999,399},{400,200}
        };//starting postions for mazes, 2 per box
        int[][] goals = {
                {0,0}, {1,0},
                {0,0},{0,99999},
                {0,0},{0,0},
                {0,0},{0,0},
                {1,5},{0,29},
                {0,0},{50,25},
                {0,0},{20,50},
                {0,0},{499,20},
                {0,0},{99,0},
                {0,0},{20,40},
                {0,0},{1,390}
        };//there are 2 goals per box, and the format is [x,y]
        for(int i = 0; i<seeds.length;i++){//for each seed
            PrintStream o;
            o = new PrintStream(new File("seed"+i+".txt"));
            System.setOut(o);//setting it so we are going save the data

            for (int j = 0; j < 3; j++) {//for each maze making algorithm
                for (int k = 0; k < lengths.length; k++) {//number of sizes
                    mazeMakingAlgorithm m = null;
                    grid main = new grid(lengths[k][0],lengths[k][1]);
                    switch (j) {
                        case 0:
                            m = new SideWinder();
                            break;
                        case 1:
                            if(main.getLaby().length<recursiveMaxSizeforMaze && main.getLaby()[0].length<recursiveMaxSizeforMaze)
                                m = new recursiveBacktracking();
                            else
                                continue;
                            break;
                        case 2:
                            m = new pseudoBacktracking();
                            break;
                    }

                    m.setR(seeds[i]);
                    long startTime = System.currentTimeMillis();
                    m.makePath(main);//make the path
                    System.out.println("Maze Time : " + (System.currentTimeMillis() - startTime));


                    for (int l = 0; l < 8; l++) {//number of path finding algorithm
                        for (int n = (k*numberOfgoalspermaze); n < (k*numberOfgoalspermaze)+2; n++) {//for number of goals and starts to consider, here k * 2 is 2 goals per mazesize
                            main.reset();//resets variables
                            Runtime.getRuntime().gc();
//
                            switch (l) {
                                case 0://Astar with manhattan
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("Astar Manhattan s:" + i +" size:"+k+" spot:"+n);
                                    runTest(new Astar(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;
                                case 1://astar with euclidean
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("Astar Euclidean s:" + i +" size:"+k+" spot:"+n);
                                    runTest(new Astar(), false, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;
                                case 2://dijkstra
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("Dijkstra s:" + i +" size:"+k+" spot:"+n);
                                    runTest(new dijkstra(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;
                                case 3://floodfill
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("Floodfill s:" + i +" size:"+k+" spot:"+n);
                                    runTest(new floodFill(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;
                                case 4://iterative brute force
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("iterative Brute Force  s:" + i +" size:"+k+" spot:"+n);
                                    runTest(new iterativeBruteForce(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;
                                case 5://iterative randomwalk
//                                    o = new PrintStream(new File(files[l]));
//                                    System.setOut(o);
                                    System.out.println("Iterative random walk s:" + i +" size:"+k+" spot:"+n);
                                    runTest(new iterativeRandomWalk(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                    System.setOut(System.out);
                                    break;
                                case 6://random walk

                                    if(main.getLaby().length<recursiveMaxSizeforMaze && main.getLaby()[0].length<recursiveMaxSizeforMaze) {
//                                        o = new PrintStream(new File(files[l]));
//                                        System.setOut(o);
                                        System.out.println("Recursive random walk s:" + i +" size:"+k+" spot:"+n);
                                        runTest(new randomWalk(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                        System.setOut(System.out);
                                    }
                                    else {
                                        continue;
                                    }
                                    break;
                                case 7://recursive bruteforce

                                    if(main.getLaby().length<recursiveMaxSizeforMaze && main.getLaby()[0].length<recursiveMaxSizeforMaze) {
//                                        o = new PrintStream(new File(files[l]));
//                                        System.setOut(o);
                                        System.out.println("Recursive brute force s:" + i +" size:"+k+" spot:"+n);
                                        runTest(new recursiveBruteforce(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
//                                        System.setOut(System.out);
                                    }
                                    else{
                                        continue;
                                    }
                                    break;
                            }
                        }//end of n
                    }//end of l
                }//end of k
            }//end of j
        }//end of i
    }
}
