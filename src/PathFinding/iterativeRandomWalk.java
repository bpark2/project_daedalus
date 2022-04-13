package PathFinding;

import Components.cell;
import Components.grid;

import java.util.ArrayList;

public class iterativeRandomWalk extends pathFindingAlgorithm{
    int destX;//the goal x coordinate
    int destY;//the goal y coordinate

    @Override
    public void findPath(grid laby, int x, int y, int destX, int destY) {
        initializeMemory();

        solution = new ArrayList<>();//the solution path
        cell curCell = laby.get(x,y);//the starting position
        //updating local vars
        this.destX = destX;
        this.destY = destY;
        //updating path with current position
        curCell.setPath(true);
        curCell.setVisited(true);
        solution.add(curCell);
        addMemory();
        while(!(curCell.getX()==destX && curCell.getY()==destY)){
            ArrayList<cell> neighbours = new ArrayList<>();
            neighbours = getNeighbours(curCell,neighbours);
            if(neighbours.size()==0){
                while(true){
                    solution.get(solution.size() - 1).setPath(false);
                    solution.remove(solution.size() - 1);
                    if(solution.size()>0) {
                        curCell = solution.get(solution.size() - 1);
                        neighbours = getNeighbours(curCell, neighbours);
                        if (neighbours.size() != 0) {
                            break;
                        }
                    }
                    addMemory();
                }
            } else {
                int decision = (int)(Math.random()*neighbours.size());
                cell chosenOne = neighbours.get(decision);
                chosenOne.setVisited(true);
                chosenOne.setPath(true);
                solution.add(chosenOne);
                curCell = chosenOne;
            }
            addMemory();
        }
        System.out.println("we got to "+curCell.getX()+","+curCell.getY());
    }

    public ArrayList<cell> getNeighbours(cell curCell, ArrayList<cell> neighbours){
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
        return neighbours;
    }
}
