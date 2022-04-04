package MazeMaking;

import Components.cell;
import Components.grid;

import java.util.ArrayList;
import java.util.Stack;

public class pseudoBacktracking extends mazeMakingAlgorithm {

    @Override
    public void makePath(grid laby) {
        Stack<cell> s = new Stack<cell>();
        int randomX = (int)(r.nextDouble() * laby.getLaby().length);
        int randomY = (int)(r.nextDouble() * laby.getLaby()[0].length);
        cell start = laby.get(randomX,randomY);
        s.push(start);
        s.peek().setVisited(true);
        while(!s.empty()){
            int x = s.peek().getX();
            int y = s.peek().getY();
            ArrayList<cell> possibleNeighbours = new ArrayList<>();
            if((y+1)<laby.getLaby()[0].length && !laby.get(x,y+1).isVisited()){//if north is not visited
                possibleNeighbours.add(laby.get(x,y+1));
            }
            if((x+1)<laby.getLaby().length && !laby.get(x+1,y).isVisited()){//east not visited
                possibleNeighbours.add(laby.get(x+1,y));
            }
            if((y-1)>=0 && !laby.get(x,y-1).isVisited()){//south not visited
                possibleNeighbours.add(laby.get(x,y-1));
            }
            if((x-1)>=0 && !laby.get(x-1,y).isVisited()){//west not visited
                possibleNeighbours.add(laby.get(x-1,y));
            }
            if(possibleNeighbours.size()==0) {//no possible neighbours
                //go back to next level and repeat process
                s.pop();
            } else {
                int decision = (int) (r.nextDouble() * possibleNeighbours.size());
                cell chosenOne = possibleNeighbours.get(decision);
                laby.setConnection(s.peek(),chosenOne);
                s.push(chosenOne);
                chosenOne.setVisited(true);
            }
        }
    }
}
