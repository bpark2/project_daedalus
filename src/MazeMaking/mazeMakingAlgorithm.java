package MazeMaking;

import Componenets.grid;

import java.util.Random;

public abstract class mazeMakingAlgorithm {
    Random r;

    public void setR(int seed) {
        r = new Random(seed);
    }
    public abstract void makePath(grid laby);
}
