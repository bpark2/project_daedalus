package MazeMaking;

import Components.grid;

import javax.swing.*;
import java.util.Random;

public abstract class mazeMakingAlgorithm {
    Random r;
    JFrame display;
    public void setDisplay(JFrame display){
        this.display = display;
    }
    public void setR(int seed) {
        r = new Random(seed);
    }
    public Random getR(){
        return r;
    }
    public abstract void makePath(grid laby);
}
