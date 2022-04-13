package Components;
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


    public cell get(int i,int j){
        return laby[i][j];
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

    /**
     * connects two given cell both directions
     * @param A the first cell to connect
     * @param B the second cell to connect
     */
    public void setConnection(cell A, cell B){
        if(A.x == B.x){//vertical change
            if(A.y<B.y){//right
                A.setConnection(1,B);
                B.setConnection(3,A);
            }
            else{//left
                A.setConnection(3,B);
                B.setConnection(1,A);
            }
        }
        else{//horizontal
            if(A.x<B.x){//down
                A.setConnection(2,B);
                B.setConnection(0,A);
            }
            else{//up
                A.setConnection(0,B);
                B.setConnection(2,A);
            }
        }
    }

    /**
     * will print the given grid neatly
     */
    public void reset(){
        for (int i = 0; i < laby.length; i++) {
            for (int j = 0; j < laby[i].length; j++) {
                laby[i][j].visited = false;
                laby[i][j].isPath = false;
            }
        }
    }
}
