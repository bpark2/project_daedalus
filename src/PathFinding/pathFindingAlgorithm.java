package PathFinding;

import Components.cell;
import Components.grid;

import javax.swing.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;

public abstract class pathFindingAlgorithm {
    ArrayList<cell> solution;
    JFrame display;
    MemoryMXBean memory;
    double totalMemory;
    double startMemory;
    boolean heuristic = false;//true for manhattan and false for euclidean

    /**
     * this function is only used for Astar algorithm
     * @param flag true for manhattan distance metric and false for euclidean
     */
    public void setHeuristic(boolean flag){
        heuristic = flag;
    }
    /**
     * this function initializes total memory and runtime so that we can keep track of memory used
     * @see ManagementFactory
     */
    public void initializeMemory(){
        memory = ManagementFactory.getMemoryMXBean();

        startMemory = ( memory.getHeapMemoryUsage().getUsed())/1000000.0;
        totalMemory = startMemory;
    }

    /**
     * this uses heap memory manager to count how much memory is used and adds them in mega bytes
     */
    public void addMemory(){
//        totalMemory = totalMemory + ((runtime.totalMemory()-runtime.freeMemory())-startMemory);
        Runtime.getRuntime().gc();
        totalMemory = totalMemory + ((((memory.getHeapMemoryUsage().getUsed()))/1000000.0) - startMemory);
    }
    public double getTotalMemory(){
        return totalMemory;
    }
    /**
     * this implements how the algorithm solves the path finding problem
     * @param laby the maze it is trying to solve
     * @param x the x position
     * @param y the y position
     * @param destX the x position of the destination
     * @param destY the y position of the destination
     */
    public abstract void findPath(grid laby, int x, int y, int destX, int destY);

    public void setDisplay(JFrame display) {
        this.display = display;
    }

    public ArrayList<cell> getSolution() {
        return solution;
    }
}
