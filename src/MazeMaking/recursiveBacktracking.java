package MazeMaking;

import Components.grid;
import Components.cell;

import java.util.ArrayList;
import java.util.Stack;

/*
recursively builds labyrinth
 */
public class recursiveBacktracking extends mazeMakingAlgorithm{
    @Override
    public void makePath(grid laby) {
        Stack<cell> s = new Stack<cell>();//stack for backtracking alg
        int randomX = (int)(r.nextDouble() * laby.getLaby().length);
        int randomY = (int)(r.nextDouble() * laby.getLaby()[0].length);
        cell start = laby.get(randomX,randomY);
        s.push(start);
        s.peek().setVisited(true);
        backTrack(laby,s);
    }

    public void backTrack(grid laby,Stack<cell> s) throws StackOverflowError{
        int x = s.peek().getX();
        int y = s.peek().getY();
        /*
        Need to figure out when to push and pop relative to recursive calls
         */
        ArrayList<cell> possibleNeighbours = new ArrayList<>();
        //display.repaint();
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
        if(possibleNeighbours.size()==0){//no possible neighbours
            //go back to next level and repeat process
            s.pop();
            if(!s.empty()) {
                backTrack(laby, s);
            }
        } else { // valid neighbours exist
            int decision = (int) (r.nextDouble() * possibleNeighbours.size());
            cell chosenOne = possibleNeighbours.get(decision);
            laby.setConnection(s.peek(),chosenOne);
            s.push(chosenOne);
            chosenOne.setVisited(true);
            backTrack(laby,s);
        }
    }
}
