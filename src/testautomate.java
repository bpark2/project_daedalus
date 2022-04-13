import Components.grid;
import MazeMaking.SideWinder;
import MazeMaking.mazeMakingAlgorithm;
import MazeMaking.pseudoBacktracking;
import MazeMaking.recursiveBacktracking;
import PathFinding.*;

public class testautomate {
    public static void runTest(pathFindingAlgorithm finder,boolean heuristic, int x,int y, int destx, int desy, grid main){
        long startTime = System.nanoTime();
        finder.setHeuristic(heuristic);
        finder.findPath(main,x,y,destx,desy);
        System.out.println("Time : " + (System.nanoTime() - startTime));
        System.out.println("Memory : " + finder.getTotalMemory());
    }
    public static void main(String[] args){
        int[] seeds = {420,352,772,17542};//different seeds we ar eusing
        int numberOfgoalspermaze = 2;
        int recursiveMaxSizeforMaze = 50;
        int[][] lengths = {{30,30},{100,100}};//different sizes of mazes we are using
        int[][] starts = {{0,0},{5,29},{2,5},{5,99}};
        int[][] goals = {{20,20},{29,5},{50,50},{99,0}};//there are 2 goals per box, and the format is [x,y]
        for(int i = 0; i<seeds.length;i++){//for each seeds
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
                            switch (l) {
                                case 0://Astar with manhattan
                                    System.out.println("Astar Manhattan");
                                    runTest(new Astar(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
                                    break;
                                case 1://astar with euclidean
                                    System.out.println("Astar Euclidean");
                                    runTest(new Astar(), false, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
                                    break;
                                case 2://dijkstra
                                    System.out.println("Dijkstra");
                                    runTest(new dijkstra(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
                                    break;
                                case 3://floodfill
                                    System.out.println("Floodfill");
                                    runTest(new floodFill(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
                                    break;
                                case 4://iterative brute force
                                    System.out.println("iterative Brute Force ");
                                    runTest(new iterativeBruteForce(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
                                    break;
                                case 5://iterative randomwalk
                                    System.out.println("Iterative random walk");
                                    runTest(new iterativeRandomWalk(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
                                    break;
                                case 6://random walk
                                    if(main.getLaby().length<recursiveMaxSizeforMaze && main.getLaby()[0].length<recursiveMaxSizeforMaze) {
                                        System.out.println("Recursive random walk");
                                        runTest(new randomWalk(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
                                    }
                                    else {
                                        continue;
                                    }
                                    break;
                                case 7://recursive bruteforce
                                    if(main.getLaby().length<recursiveMaxSizeforMaze && main.getLaby()[0].length<recursiveMaxSizeforMaze) {
                                        System.out.println("Recursive brute force");
                                        runTest(new recursiveBruteforce(), true, starts[n][0], starts[n][1], goals[n][0], goals[n][1], main);
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
