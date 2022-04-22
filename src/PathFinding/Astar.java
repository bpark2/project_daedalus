package PathFinding;

import Components.cell;
import Components.grid;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Astar extends pathFindingAlgorithm{

    /**
     * given a labrinth with cells, and dist of each cell, returns the smallest value
     * @param laby the labyrinth we are considering
     * @param dist the distance value of each node
     * @return the cell that is not visited and has lowest value
     */
    cell min(grid laby, double[][] dist){
        cell returnThis = null;
        double minval = Double.POSITIVE_INFINITY;
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                if(minval>dist[i][j] && !laby.get(i,j).isVisited()){
                    returnThis = laby.get(i,j);
                    minval = dist[i][j];
                }
            }
        }
        return returnThis;//this means that we are returing a cell we already visited, which then ends the loop
    }


    boolean isManhattan(){
        return heuristic;
    }
    /**
     * given a list of visited cells, returns the path from the current cell
     * @param prev the list of cells visited in order
     * @param current the current cell we are at
     */
    void reconstructPath(cell[][] prev, cell current){
        solution = new ArrayList<>();
        solution.add(0,current);
        cell c = current;
        while (prev[c.getX()][c.getY()]!=null){
            c = prev[c.getX()][c.getY()];
            solution.add(0,c);
        }
    }
    /**
     * given two cells, this function calculates the manhattan distance of the two cells
     * @param A this current cell
     * @param B the neighbouring cell we are testing against
     * @return an integer value which is the manhattan distance between current cell and the neighbour.
     */
    int ManhattanDistance(cell A, cell B){
        return Math.abs(A.getX() - B.getX()) + Math.abs(A.getY() - B.getY());
    }

    /**
     * given two cells, this functions calculates the euclidean distance of the two cells
     * @param A this current cell.
     * @param B the neighbouring cell we are testing against.
     * @return an integer value which is the euclidean distance between current cell and the neighbour.
     */
    double EuclideanDistance(cell A, cell B){
        return Math.sqrt(Math.pow(A.getX()-B.getX(),2) + Math.pow(A.getY()-B.getY(),2));
    }
    @Override
    public void findPath(grid laby, int x, int y, int destX, int destY) {
        initializeMemory();//used to keep track of the memory used.
        int[][] dist = new int[laby.getLaby().length][laby.getLaby()[0].length];//gets the distance values for each node
        double[][] fscore = new double[laby.getLaby().length][laby.getLaby()[0].length];//tracks the manhattan distance
        cell[][] prev = new cell[laby.getLaby().length][laby.getLaby()[0].length];
        for (int i = 0; i < dist.length; i++) {//fills the distances with max value
            for (int j = 0; j < dist[i].length; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                fscore[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        dist[x][y] = 0;//the starting position is always 0 distance from it self.
        fscore[x][y] = isManhattan()?ManhattanDistance(laby.get(x,y),laby.get(destX,destY)):EuclideanDistance(laby.get(x,y),laby.get(destX,destY));//calculates the manhattan distance of origin to destination

        addMemory();//adds the amount of memory currently used

        while(true){//keep looping till we reach destination
            cell currCell = min(laby,fscore);

            if(currCell==null)//if no more cells to visit
                break;
            if((currCell.getX()==destX && currCell.getY()==destY)){
                reconstructPath(prev,currCell);
                break;
            }
            currCell.setVisited(true);//we visit it
            try {
                display.repaint();
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("no oh");
            }
            ArrayList<cell> neighbours = new ArrayList<>();//holds the number of neighbours for current cell
            if(currCell.getCellNorth()!=null && !currCell.getCellNorth().isVisited()) neighbours.add(currCell.getCellNorth());//checks if we have a north neighbour
            if(currCell.getCellEast()!=null && !currCell.getCellEast().isVisited()) neighbours.add(currCell.getCellEast());//checks if we have a east neighbour
            if(currCell.getCellSouth()!=null && !currCell.getCellSouth().isVisited()) neighbours.add(currCell.getCellSouth());//checks if we have a south neighbour
            if(currCell.getCellWest()!=null && !currCell.getCellWest().isVisited()) neighbours.add(currCell.getCellWest());//checks if we have a west neighbour
            for (cell neighbour :
                    neighbours) {
                int tempScore = dist[currCell.getX()][currCell.getY()] + 1;//the distance between two cells is one
                if(tempScore<dist[neighbour.getX()][neighbour.getY()]){
                    prev[neighbour.getX()][neighbour.getY()] = currCell;
                    dist[neighbour.getX()][neighbour.getY()] = tempScore;
                    fscore[neighbour.getX()][neighbour.getY()] = tempScore + (isManhattan()?ManhattanDistance(neighbour,laby.get(destX,destY)):EuclideanDistance(neighbour,laby.get(destX,destY)));
                }//end of the if statement
            }
            addMemory();
        }//end of while loop
    }
}
