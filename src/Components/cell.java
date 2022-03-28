package Components;/*
each Componenets.cell represents a space in the labyrinth
 */

public class cell {
    cell cellNorth;
    cell cellEast;
    cell cellSouth;
    cell cellWest;
    int x;
    int y;
    boolean visited;//for labyrinth traversal

    public cell(int xCoord, int yCoord){
        x = xCoord;
        y = yCoord;
        visited = false;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public boolean isVisited(){
        return visited;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public cell getCellNorth() {
        return cellNorth;
    }

    public cell getCellEast() {
        return cellEast;
    }

    public cell getCellSouth() {
        return cellSouth;
    }

    public cell getCellWest() {
        return cellWest;
    }

    /*
                    this method allows for a given Componenets.cell to connect to another. An int value is provided
                    to specify which type of connection is being made
                    0 - north
                    1 - east
                    2 - south
                    3 - west
                     */
    public void setConnection(int dir, cell newConnection){
        switch (dir) {
            case 0: cellNorth = newConnection; break;
            case 1: cellEast = newConnection; break;
            case 2: cellSouth = newConnection; break;
            case 3: cellWest = newConnection; break;
            default: System.out.println("Something is wrong");
        }
    }
}
