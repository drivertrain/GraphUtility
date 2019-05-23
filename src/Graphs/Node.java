package Graphs;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;


public class Node extends JComponent
{
    private Point location;
    private String name;
    
    public Node(Point d, String n)
    {
        this.location = d; this.name = n;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString(name, location.x, location.y);
    }
    
    @Override
    public String getName(){return this.name;}
}
