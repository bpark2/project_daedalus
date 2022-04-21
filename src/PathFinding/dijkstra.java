package PathFinding;

import Components.grid;
import Components.cell;

import java.util.ArrayList;

public class dijkstra extends pathFindingAlgorithm{

    void reconstructPath(grid laby, cell[][] prev, int x, int y, int destX, int destY){
        solution = new ArrayList<>();
        cell temp = laby.get(destX,destY);
        while(temp.getX()!=x || temp.getY()!=y) {
            int tempx = temp.getX();
            int tempy = temp.getY();
//            laby.get(tempx, tempy).setPath(true);
            solution.add(0,laby.get(tempx,tempy));
            temp = prev[tempx][temp.getY()];
        }
        solution.add(0,laby.get(x,y));
//        laby.get(x,y).setPath(true);
    }
    cell min(grid laby, int[][] dist){
        cell returnThis = null;
        int minval = Integer.MAX_VALUE;
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
    @Override
    public void findPath(grid laby, int x, int y, int destX, int destY) {
        initializeMemory();//intializes the total memory

        int[][] dist = new int[laby.getLaby().length][laby.getLaby()[0].length];
        addMemory();
        cell[][] prev = new cell[laby.getLaby().length][laby.getLaby()[0].length];
        addMemory();
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[x][y] = 0;
        addMemory();
        while(true){
            cell u = min(laby,dist);
            if(u==null)//if there is no more nodes to look for
                break;
            u.setVisited(true);
            if((u.getX()==destX && u.getY()==destY)){// if we found the solution
                break;
            }
            //for each neighbour
            if(u.getCellNorth()!=null && !u.getCellNorth().isVisited()){
                int temp = dist[u.getX()][u.getY()] + 1;
                if(temp < dist[u.getCellNorth().getX()][u.getCellNorth().getY()]){
                    dist[u.getCellNorth().getX()][u.getCellNorth().getY()] = temp;
                    prev[u.getCellNorth().getX()][u.getCellNorth().getY()] = u;
                }
            }
            if(u.getCellEast()!=null && !u.getCellEast().isVisited()){
                int temp = dist[u.getX()][u.getY()] + 1;
                if(temp < dist[u.getCellEast().getX()][u.getCellEast().getY()]){
                    dist[u.getCellEast().getX()][u.getCellEast().getY()] = temp;
                    prev[u.getCellEast().getX()][u.getCellEast().getY()] = u;
                }
            }
            if(u.getCellSouth()!=null && !u.getCellSouth().isVisited()){
                int temp = dist[u.getX()][u.getY()] + 1;
                if(temp < dist[u.getCellSouth().getX()][u.getCellSouth().getY()]){
                    dist[u.getCellSouth().getX()][u.getCellSouth().getY()] = temp;
                    prev[u.getCellSouth().getX()][u.getCellSouth().getY()] = u;
                }
            }
            if(u.getCellWest()!=null && !u.getCellWest().isVisited()){
                int temp = dist[u.getX()][u.getY()] + 1;
                if(temp < dist[u.getCellWest().getX()][u.getCellWest().getY()]){
                    dist[u.getCellWest().getX()][u.getCellWest().getY()] = temp;
                    prev[u.getCellWest().getX()][u.getCellWest().getY()] = u;
                }
            }
            addMemory();
        }//end of loop
        reconstructPath(laby, prev,x, y, destX,destY);
    }
}
