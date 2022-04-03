package PathFinding;

import Components.cell;
import Components.grid;

import java.util.ArrayList;

public class randomWalk extends pathFindingAlgorithm{
    int destX;
    int destY;

    @Override
    public void findPath(grid laby, int x, int y, int destX, int destY) {
        solution = new ArrayList<>();
        cell start = laby.get(x,y);
        this.destX = destX;
        this.destY = destY;
        start.setPath(true);
        start.setVisited(true);

        //while cell's coord's are not goal cords, make movement
        //need to update x and y coordinates
        //currently never exiting this while loop
        while(start.getX()!=destY || start.getY()!=destX){
            ArrayList<cell> choices = new ArrayList<>();
            int myX = start.getX();
            int myY = start.getY();
            if((myY+1)<laby.getLaby()[0].length && !laby.get(myX,myY+1).isVisited()){//if north is not visited
                choices.add(laby.get(myX,myY+1));
            }
            if((myX+1)<laby.getLaby().length && !laby.get(myX+1,myY).isVisited()){//east not visited
                choices.add(laby.get(myX+1,myY));
            }
            if((myY-1)>=0 && !laby.get(myX,myY-1).isVisited()){//south not visited
                choices.add(laby.get(myX,myY-1));
            }
            if((myX-1)>=0 && !laby.get(myX-1,myY).isVisited()){//west not visited
                choices.add(laby.get(myX-1,myY));
            }
            if(choices.size()==0){//we're at a deadend
                //might have issue here
                if(solution.size()>0) {
                    solution.remove(solution.size() - 1);
                    start = solution.get(solution.size()-1);
                }
            } else {
                int choice = (int) (Math.random()*choices.size());
                //this is a problem i believe
                solution.add(choices.get(choice));
                choices.get(choice).setPath(true);
                start = choices.get(choice);
                start.setVisited(true);
            }
            System.out.println("AHHHHH");
        }
        System.out.println(solution.size());
    }
}
