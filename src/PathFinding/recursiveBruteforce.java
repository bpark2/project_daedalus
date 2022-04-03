package PathFinding;

import Components.cell;
import Components.grid;

import java.util.ArrayList;

public class recursiveBruteforce extends pathFindingAlgorithm{
    int destX;
    int destY;
    boolean isPartOfSolution(cell path, int dir){
        if(path == null)
            return false;
        if(path.getX()==destX&&path.getY()==destY){
//            solution.add(path);
            return true;
        }
        if(dir!=2&&isPartOfSolution(path.getCellNorth(),0)) {//moving north
            solution.add(path.getCellNorth());
            path.getCellNorth().setPath(true);
            return true;
        }
        else if(dir!=3&&isPartOfSolution(path.getCellEast(),1)) {//moving east
            solution.add(path.getCellEast());
            path.getCellEast().setPath(true);
            return true;
        }
        else if(dir!=0&&isPartOfSolution(path.getCellSouth(),2)) {//moving south
            solution.add(path.getCellSouth());
            path.getCellSouth().setPath(true);
            return true;
        }
        else if(dir!=1&&isPartOfSolution(path.getCellWest(),3)) {//moving west
            solution.add(path.getCellWest());
            path.getCellWest().setPath(true);
            return true;
        }
        return false;
    }
    public void findPath(grid laby, int x, int y, int destX, int destY){
        solution = new ArrayList<>();
        cell start = laby.get(x,y);

        this.destX = destX;
        this.destY = destY;
        start.setPath(true);
        start.setVisited(true);
        if(isPartOfSolution(start.getCellNorth(),0)) {
            solution.add(start.getCellNorth());
            start.getCellNorth().setPath(true);
        }
        else if(isPartOfSolution(start.getCellEast(),1)) {
            solution.add(start.getCellEast());
            start.getCellEast().setPath(true);
        }
        else if(isPartOfSolution(start.getCellSouth(),2)) {
            solution.add(start.getCellSouth());
            start.getCellSouth().setPath(true);
        }
        else if(isPartOfSolution(start.getCellWest(),3)) {
            solution.add(start.getCellWest());
            start.getCellWest().setPath(true);
        }

        solution.add(start);
    }

}
