package MazeMaking;

import Components.grid;
import Components.cell;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
//import java.util.LinkedList;

public class SideWinder extends mazeMakingAlgorithm{
    /**
     * this algorithms implements the Side Winder algorithm and more about how it works can be read here -> https://weblog.jamisbuck.org/2011/2/3/maze-generation-sidewinder-algorithm
     * @param laby this is the grid we are trying to solve
     */
    public void makePath(grid laby){
        ArrayList<cell> run = new ArrayList<>();
        for (int i = 0; i < laby.getLaby().length; i++) {
            run.clear();
//            display.repaint();//repaints it each time
            for (int j = 0; j < laby.getLaby()[i].length; j++) {
                run.add(laby.get(i,j));
                if(r.nextDouble()>0.5 && j+1<laby.getLaby()[i].length){//carve east
                    laby.setConnection(laby.get(i,j),laby.get(i,j+1));
                }
                else if(i+1<laby.getLaby().length){//carve south
                    int loc = (int)(run.size()*r.nextDouble());
                    cell random = run.get(loc);
                    laby.setConnection(random,laby.get(random.getX()+1, random.getY()));
                    run.clear();
                }
                else if(j+1<laby.getLaby()[i].length){//keep moving east
                    laby.setConnection(laby.get(i,j),laby.get(i,j+1));
                }
                try {
                    display.repaint();
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("no oh");
                }
            }
        }//end of for loop
    }
}
