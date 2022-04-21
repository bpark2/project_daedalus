package GUI;

import javax.swing.*;
import java.awt.*;
import Components.grid;

public class myFrame extends JFrame {
    int dim;
    labDisp labyrinth;//displays the labyrinth
    private volatile boolean exit = false;//for later when force end

    /**
     * this is the constructor for the GUI we are using.
     * @param title this is the title of the gui
     */
    public myFrame(String title){
        setPreferredSize(new Dimension(500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        labyrinth = new labDisp(this);
        Container contain = getContentPane();
        contain.add(labyrinth,BorderLayout.CENTER);

        pack();
        setVisible(true);
    }
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
                    if (curCell.isPath()) {
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(x - diagSize, y - diagSize, diagSize, diagSize);
                        g.setColor(Color.BLACK);
                    }
                }
            }
        }
    }

}
