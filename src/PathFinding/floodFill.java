package PathFinding;

import Components.grid;
import Components.cell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class floodFill extends pathFindingAlgorithm{
    void pathReconstruct(Stack<cell> S, cell currCell){
        solution = new ArrayList<>();
        while (!S.isEmpty()){

            cell temp = S.pop();
            if(currCell.getCellNorth()!=null && temp.getCellSouth()!=null && (currCell.getX()-1 == temp.getX() && currCell.getY()==temp.getY())){//if temp is north of currCell
                solution.add(0,currCell);
                currCell = temp;
                continue;
            }
            if(currCell.getCellEast()!=null && temp.getCellWest()!=null && (currCell.getX()==temp.getX() && currCell.getY()+1 == temp.getY())){//if temp is east of currCell
                solution.add(0,currCell);
                currCell = temp;
                continue;
            }
            if(currCell.getCellSouth()!=null && temp.getCellNorth()!=null && (currCell.getX()+1 == temp.getX() && currCell.getY() == temp.getY())){//if temp is south of currCell
                solution.add(0,currCell);
                currCell = temp;
                continue;
            }
            if(currCell.getCellWest()!=null && temp.getCellEast()!=null && (currCell.getX() == temp.getX() && currCell.getY()-1 == temp.getY())){//if temp is west of currCell
                solution.add(0,currCell);
                currCell = temp;
                continue;
            }
        }
    }
    LinkedList<cell> list ;//this contains the list of cells to be visited.
    @Override
    public void findPath(grid laby, int x, int y, int destX, int destY) {

        initializeMemory();

        list = new LinkedList<>();//contains the list to be visted.
        Stack<cell> takencell = new Stack<>();//this represents the cells that were taken already.
        list.add(laby.get(x,y));//adds the base case of origin cell
        cell temp = list.peek();

        addMemory();

        while(!list.isEmpty()){//till the list of needs to visit cell is not empty
            temp = list.remove();
            takencell.push(temp);
            temp.setVisited(true);
            temp.setPath(true);
            if(temp.getX()==destX && temp.getY()==destY) {
                break;
            }
            if(temp.getCellNorth()!=null && !temp.getCellNorth().isVisited())
                list.add(temp.getCellNorth());
            if(temp.getCellEast()!=null && !temp.getCellEast().isVisited())
                list.add(temp.getCellEast());
            if(temp.getCellSouth()!=null && !temp.getCellSouth().isVisited())
                list.add(temp.getCellSouth());
            if(temp.getCellWest()!=null && !temp.getCellWest().isVisited())
                list.add(temp.getCellWest());

            addMemory();
        }
        pathReconstruct(takencell, temp);
    }
}
