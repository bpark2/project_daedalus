package PathFinding;

import Componenets.cell;
import Componenets.grid;

import java.util.ArrayList;

public abstract class pathFindingAlgorithm {
    ArrayList<cell> solution;

    /**
     * this implements how the algorithm solves the path finding problem
     * @param laby the maze it is trying to solve
     * @param x the x position
     * @param y the y position
     * @param destX the x position of the destination
     * @param destY the y position of the destination
     */
    public abstract void findPath(grid laby, int x, int y, int destX, int destY);

    public ArrayList<cell> getSolution() {
        return solution;
    }
}
