package PathFinding;

import Components.cell;
import Components.grid;

import java.util.ArrayList;
import java.util.LinkedList;

public class AstarManhattan extends pathFindingAlgorithm{

    cell minManhattan(grid laby, int[][] dist){

    }
    @Override
    public void findPath(grid laby, int x, int y, int destX, int destY) {
        int[][] dist = new int[laby.getLaby().length][laby.getLaby()[0].length];//gets the distance values for each node

        for (int i = 0; i < dist.length; i++) {//fills the distances with max value
            for (int j = 0; j < dist[i].length; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[x][y] = 0;//the starting position is always 0 distance from it self.
        while(true){//keep looping till we reach destination
            cell currCell = minManhattan(laby,dist);
            if(currCell==null)//if no more cells to visit
                break;
            if((currCell.getX()==destX && currCell.getY()==destY)){
                System.out.println("found solution");
                break;
            }
            ArrayList<cell> neighbours = new ArrayList<>();//holds the number of neighbours for current cell
            if(currCell.getCellNorth()!=null) neighbours.add(currCell.getCellNorth());
            if(currCell.getCellEast()!=null) neighbours.add(currCell.getCellEast());
            if(currCell.getCellSouth()!=null) neighbours.add(currCell.getCellSouth());
            if(currCell.getCellWest()!=null) neighbours.add(currCell.getCellWest());
            for (cell neighbour :
                    neighbours) {
                int tempScore = dist[currCell.getX()][currCell.getY()] + ManhattanDistance();
            }
        }//end of while loop
    }
}
