package PathFinding;

import Components.grid;
import Components.cell;
import java.util.LinkedList;

public class floodFill extends pathFindingAlgorithm{
    LinkedList<cell> list ;
    @Override
    public void findPath(grid laby, int x, int y, int destX, int destY) {
        list = new LinkedList<>();
        list.add(laby.get(x,y));
        while(!list.isEmpty()){
            cell temp = list.remove();
            temp.setVisited(true);
            temp.setPath(true);
            if(temp.getX()==destX && temp.getY()==destY) {
                System.out.println("found path");
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


        }
    }
}
