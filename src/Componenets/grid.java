package Componenets;
/*
The Componenets.grid object will act as our board and contain the labyrinth
 */

public class grid {
    cell[][] laby;//our labyrinth
    public grid(int height, int width){
        laby = new cell[height][width];
        init();
    }

    public cell[][] getLaby() {
        return laby;
    }

    /*
        initializes a labyrinth filled with cells that are unconnected
         */
    public cell get(int i,int j){
        return laby[i][j];
    }
    public void init(){
        for (int i = 0; i < laby.length; i++) {
            for (int j = 0; j < laby[i].length; j++) {
                laby[i][j] = new cell(i,j);
            }
        }
    }

    /**
     * connects two given cell
     * @param A the first cell to connect
     * @param B the second cell to connect
     */
    public void setConnection(cell A, cell B){
        if(A.x == B.x){//vertical change
            if(A.y<B.y){//right
                A.setConnection(1,B);
            }
            else{//left
                A.setConnection(3,B);
            }
        }
        else{//horizontal
            if(A.x<B.x){//down
                A.setConnection(2,B);
            }
            else{//up
                A.setConnection(0,B);
            }
        }
    }

    /**
     * will print the given grid neatly
     */
    public void prettyprint(){

    }
}
