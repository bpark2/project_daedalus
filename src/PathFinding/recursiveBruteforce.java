package PathFinding;

import Components.cell;
import Components.grid;

import java.util.ArrayList;

public class recursiveBruteforce extends pathFindingAlgorithm{
    int destX;
    int destY;
    boolean isPartOfSolution(cell path, int dir){
        addMemory();
        if(path == null)
            return false;
        if(path.getX()==destX&&path.getY()==destY){
//            solution.add(path);
            return true;
        }
        if(dir!=2&&isPartOfSolution(path.getCellNorth(),0)) {//moving north
            solution.add(0,path.getCellNorth());
            path.getCellNorth().setVisited(true);
            return true;
        }
        else if(dir!=3&&isPartOfSolution(path.getCellEast(),1)) {//moving east
            solution.add(0,path.getCellEast());
            path.getCellEast().setVisited(true);
            return true;
        }
        else if(dir!=0&&isPartOfSolution(path.getCellSouth(),2)) {//moving south
            solution.add(0,path.getCellSouth());
            path.getCellSouth().setVisited(true);
            return true;
        }
        else if(dir!=1&&isPartOfSolution(path.getCellWest(),3)) {//moving west
            solution.add(0,path.getCellWest());
            path.getCellWest().setVisited(true);
            return true;
        }
        return false;
    }
    public void findPath(grid laby, int x, int y, int destX, int destY){
        initializeMemory();
        solution = new ArrayList<>();
        cell start = laby.get(x,y);
// here 0 indicates, we went north, 1 east, 2 south and 3 west
        this.destX = destX;
        this.destY = destY;
        start.setPath(true);
        start.setVisited(true);
        solution.add(start);
        addMemory();
        if(isPartOfSolution(start.getCellNorth(),0)) {// if we can go north and didn't come from south go north.
            solution.add(0,start.getCellNorth());
            start.getCellNorth().setVisited(true);
        }
        else if(isPartOfSolution(start.getCellEast(),1)) {
            solution.add(0,start.getCellEast());
            start.getCellEast().setVisited(true);
        }
        else if(isPartOfSolution(start.getCellSouth(),2)) {
            solution.add(0,start.getCellSouth());
            start.getCellSouth().setVisited(true);
        }
        else if(isPartOfSolution(start.getCellWest(),3)) {
            solution.add(0,start.getCellWest());
            start.getCellWest().setVisited(true);
        }
    addMemory();
    }

}
