package PathFinding;

import Components.cell;
import Components.grid;

import java.util.ArrayList;
import java.util.Stack;

public class iterativeBruteForce extends pathFindingAlgorithm{
    void reconstructPath(grid laby, Stack<Integer> S,int x, int y){
        solution = new ArrayList<>();
//        Stack<Integer> temp = new Stack<>();
        while (!S.isEmpty()){
            switch (S.pop()){
                case 0://needs to go south
                    solution.add(0,laby.get(x,y));
                    x = x +1;
                    break;
                case 1:// needs to go west
                    solution.add(0,laby.get(x,y));
                    y = y-1;
                    break;
                case 2://needs to go north
                    solution.add(0,laby.get(x,y));
                    x = x -1;
                    break;
                case 3://needs to go east
                    solution.add(0,laby.get(x,y));
                    y = y +1;
                    break;
            }
        }

    }
    @Override
    public void findPath(grid laby, int x, int y, int destX, int destY) {
        cell currCell = laby.get(x,y);//this is teh currCell we are looking at
        Stack<Integer> S = new Stack<>();//this holds which position we took at each step of the solution.

//        int pathTaken = -1;//0 for north taken, 1 for east taken, 2 for south taken, and 3 for west taken
        while(true) {
            currCell.setVisited(true);
            if (currCell.getX() == destX && currCell.getY() == destY)//if we reach destination break
                break;
            if (currCell.getCellWest() != null && !currCell.getCellWest().isVisited()) {//there exist west
//                pathTaken = 3;
                S.push(3);
                currCell = currCell.getCellWest();
                continue;
            }
            if(currCell.getCellNorth()!=null && !currCell.getCellNorth().isVisited()){//there exist north
//                pathTaken = 0;
                S.push(0);
                currCell = currCell.getCellNorth();
                continue;
            }
            if(currCell.getCellEast() != null && !currCell.getCellEast().isVisited()){
//                pathTaken = 1;
                S.push(1);
                currCell = currCell.getCellEast();
                continue;
            }
            if(currCell.getCellSouth() !=null && !currCell.getCellSouth().isVisited()){
//                pathTaken = 2;
                S.push(2);
                currCell = currCell.getCellSouth();
                continue;
            }
//            int temp = S.pop();//temp holds last direction we took.
            switch (S.pop()){//if we get to this, then there is no path that can be taken that is not already visited.
                case 0://we went north in this step
                    currCell = currCell.getCellSouth();
                    break;
                case 1://we went east
                    currCell = currCell.getCellWest();
                    break;
                case 2://we went north in this case
                    currCell = currCell.getCellNorth();
                    break;
                case 3://we went west in this case
                    currCell = currCell.getCellEast();
                    break;
            }
        }//end of while
        reconstructPath(laby,S, currCell.getX(),currCell.getY());
    }
}
