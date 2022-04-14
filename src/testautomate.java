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
    public static void runTest(pathFindingAlgorithm finder,boolean heuristic, int x,int y, int destx, int desy, grid main){
        long startTime = System.nanoTime();
        finder.setHeuristic(heuristic);
        finder.findPath(main,x,y,destx,desy);
        System.out.println("Time : " + (System.nanoTime() - startTime));
        System.out.println("Memory : " + finder.getTotalMemory());
        System.out.println("Visted : "+ main.countVisted(x,y,destx,desy));
        System.out.println("deadends : "+ main.countDeadEnds(x,y,destx,desy));
        System.out.println();
    }
    public static void main(String[] args) throws FileNotFoundException {
        int[] seeds = {420,352,772,17542};//different seeds we ar eusing
//        String[] files = //have to be ordered as the switch statement
        int numberOfgoalspermaze = 2;
        int recursiveMaxSizeforMaze = 50;
        int[][] lengths = {{30,30},{100,100}};//different sizes of mazes we are using
        int[][] starts = {{0,0},{5,29},{2,5},{5,99}};
        int[][] goals = {{20,20},{29,5},{50,50},{99,0}};//there are 2 goals per box, and the format is [x,y]
        for(int i = 0; i<seeds.length;i++){//for each seed
            PrintStream o;
            o = new PrintStream(new File("seed"+i+".txt"));
            System.setOut(o);

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
                    long startTime = System.nanoTime();
                    m.makePath(main);//make the path
                    System.out.println("Maze Time : " + (System.nanoTime() - startTime));


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
