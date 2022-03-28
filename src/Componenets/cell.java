package Componenets;/*
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
            case 0 -> cellNorth = newConnection;
            case 1 -> cellEast = newConnection;
            case 2 -> cellSouth = newConnection;
            case 3 -> cellWest = newConnection;
            default -> System.out.println("Something is wrong");
        }
    }
}
