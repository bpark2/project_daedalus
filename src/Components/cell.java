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
    boolean visited;//for labyrinth traversal, if the cell is visited
    boolean isPath;//for to ensure if it is the path

    /**
     * this function is the constructor for the cell object, where it takes in the x and y coordinates of the cell in the grid
     * @see grid
     * @param xCoord the x location
     * @param yCoord the y location
     */
    public cell(int xCoord, int yCoord){
        x = xCoord;
        y = yCoord;
        visited = false;
        isPath = false;
    }

    public void setPath(boolean isPath){
        this.isPath = isPath;
    }
    public boolean isPath(){
        return isPath;
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

    /**
        this method allows for a given Componenets.cell to connect to another. An int value is provided
        to specify which direction is made.
        0 - north
        1 - east
        2 - south
        3 - west
     @param dir this is the direcation we are connecting
     @param newConnection the cell to which we are connecting the current ceel
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
