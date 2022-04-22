package GUI;

import javax.swing.*;
import java.awt.*;
import Components.grid;

public class myFrame extends JFrame {
    int dim;
    labDisp labyrinth;//displays the labyrinth
    boolean mazemaking;//indicates if we are making a maze or not
    private volatile boolean exit = false;//for later when force end

    /**
     * this is the constructor for the GUI we are using.
     * @param title this is the title of the gui
     */
    public myFrame(String title){
        mazemaking = false;
        setPreferredSize(new Dimension(500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        labyrinth = new labDisp(this);
        Container contain = getContentPane();
        contain.add(labyrinth,BorderLayout.CENTER);

        pack();
        setVisible(true);
    }
    public void setMazemaking(boolean flag){
        mazemaking = flag;
    }

    /**
     * this functions sets the grid we are using with the display
     * @param g the grid we are using for the display
     */
    public void setLabyrinth(grid g){
        labyrinth.setGriddy(g);
    }

}
class labDisp extends JPanel{
    myFrame F;
    grid griddy;

    public labDisp(myFrame F){
        griddy = null;
        this.F = F;
    }
    public void setGriddy(grid g){
        griddy = g;
    }

    /**
     * this function repaints the grid each time a change is made, and redraws the maze and the paths and the solution that is found
     * @param g
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);


        int diagSize = 5;//half the size of a cell
        int x = diagSize + 5;
        int initiY = x;
        int y = diagSize + 5;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(griddy!=null) {
            for (int i = 0; i < griddy.getLaby().length; i++) {
                y = y + (diagSize * 2);

                x = initiY;
                for (int j = 0; j < griddy.getLaby()[i].length; j++) {
//                int initX = x*i;
//                int initY = y*j;

                    x = x + (diagSize * 2);
                    Components.cell curCell = griddy.get(i, j);
                    if (curCell.getCellNorth() == null) {//there is north wall
                        g.drawLine(x - diagSize, y - diagSize, x + diagSize, y - diagSize);
                    }
                    if (curCell.getCellEast() == null) {
                        g.drawLine(x + diagSize, y - diagSize, x + diagSize, y + diagSize);
                    }
                    if (curCell.getCellSouth() == null) {
                        g.drawLine(x + diagSize, y + diagSize, x - diagSize, y + diagSize);
                    }
                    if (curCell.getCellWest() == null) {
                        g.drawLine(x - diagSize, y + diagSize, x - diagSize, y - diagSize);
                    }
                    if (curCell.isVisited() && !F.mazemaking) {
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(x - diagSize + 2, y - diagSize+2, diagSize, diagSize);
                        g.setColor(Color.BLACK);
                    }
                    if(curCell.isPath()){
                        g.setColor(Color.GREEN);
                        g.fillRect(x-diagSize +2,y-diagSize +2 ,diagSize,diagSize);
                        g.setColor(Color.BLACK);
                    }
                }
            }
        }
    }

}
