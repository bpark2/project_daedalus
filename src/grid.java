
/*
The grid object will act as our board and contain the labyrinth
 */

public class grid {
    cell[][] laby;//our labyrinth
    public grid(int height, int width){
        laby = new cell[height][width];

    }

    /*
    initializes a labyrinth filled with cells that are unconnected
     */
    public void init(){
        for (int i = 0; i < laby.length; i++) {
            for (int j = 0; j < laby[i].length; j++) {
                laby[i][j] = new cell(i,j);
            }
        }
    }
}
