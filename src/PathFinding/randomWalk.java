package PathFinding;

import Components.cell;
import Components.grid;

import java.util.ArrayList;

public class randomWalk extends pathFindingAlgorithm{
    int destX;//the goal x coordinate
    int destY;//the goal y coordinate

    @Override
    public void findPath(grid laby, int x, int y, int destX, int destY) {
        initializeMemory();
        solution = new ArrayList<>();//the solution path
        cell start = laby.get(x,y);//the starting position
        //updating local vars
        this.destX = destX;
        this.destY = destY;
        //updating path with current position
        start.setPath(true);
        start.setVisited(true);
        solution.add(start);
        randomStep(laby,start,solution,destX,destY);
    }

    public void randomStep(grid laby, cell curCell, ArrayList<cell> path, int destX, int destY){
        int curX = curCell.getX();
        int curY = curCell.getY();
        ArrayList<cell> neighbours = new ArrayList<>();
        if(curX!=destX || curY!=destY){//if our cell is not at the goal position
            /*
            if north is not out of bounds and is not visited:
                add to list of possible neighbours
            if east is not out of bounds and is not visited:
                add to list of possible neighbours
            if south is not out of bounds and is not visited:
                add to list of possible neighbours
            if west is not out of bounds and is not visited:
                add to list of possible neighbours
             */
            /*
            note: these conditional statements do not care about our walls
             */
            if(curCell.getCellNorth()!=null && !curCell.getCellNorth().isVisited()){
                neighbours.add(curCell.getCellNorth());//laby.get(curX,curY-1)
            }
            if(curCell.getCellEast()!=null && !curCell.getCellEast().isVisited()){
                neighbours.add(curCell.getCellEast());//laby.get(curX+1,curY)
            }
            if(curCell.getCellSouth()!=null && !curCell.getCellSouth().isVisited()){
                neighbours.add(curCell.getCellSouth());//laby.get(curX,curY+1)
            }
            if(curCell.getCellWest()!=null && !curCell.getCellWest().isVisited()){
                neighbours.add(curCell.getCellWest());//laby.get(curX-1,curY)
            }
            if(neighbours.size()==0){//no possible neighbours to move to
                if(path.size()>0) {
                    path.get(path.size() - 1).setPath(false);//removing from solution path
                    path.remove(path.size() - 1);//removing from solution path
                    addMemory();
                    randomStep(laby, path.get(path.size() - 1), path, destX, destY);//recursive call
                }
            } else {//possible neighbours exist
                int decision = (int)(Math.random() * neighbours.size());//picking a random neighbour
                cell chosenOne = neighbours.get(decision);//our chosen cell to move to
                chosenOne.setVisited(true);//update it as visited
                chosenOne.setPath(true);//update as part of the path
                path.add(chosenOne);//at to path list
                addMemory();
                randomStep(laby,chosenOne,path,destX,destY);//recursive call
            }
        }
    }
}
