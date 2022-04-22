package MazeMaking;

import Components.cell;
import Components.grid;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class pseudoBacktracking extends mazeMakingAlgorithm {

    @Override
    /**
     * this function makes the grid using the backtracking algorithm, however, this is iterative in nature. https://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
     *
     */
    public void makePath(grid laby) {
        Stack<cell> s = new Stack<>();
        int randomX = (int)(r.nextDouble() * laby.getLaby().length);
        int randomY = (int)(r.nextDouble() * laby.getLaby()[0].length);
        cell start = laby.get(randomX,randomY);
        s.push(start);
        s.peek().setVisited(true);
        while(!s.empty()){
            int x = s.peek().getX();
            int y = s.peek().getY();
            ArrayList<cell> possibleNeighbours = new ArrayList<>();
            try {
                display.repaint();
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("no oh");
            }
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
