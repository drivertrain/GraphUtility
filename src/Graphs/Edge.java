
package Graphs;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JComponent;

/**
 *
 * @author owner
 */
public class Edge extends JComponent
{
    private Dimension origin;
    private Dimension destination;
    private int weight, length, width, height, quadrant;
    
    public Edge(Dimension origin, Dimension destination, int weight)
    {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;    
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Point midpoint = new Point(Math.abs(destination.width + origin.width)/2, Math.abs(destination.height + origin.height)/2);
        g2.drawLine(origin.width, origin.height, destination.width, destination.height);
        //drawArrowHead(g2);
        g2.drawString(Integer.toString(weight), midpoint.x, midpoint.y);
        //g2.draw(createArrowShape(new Point(origin.width, origin.height), new Point(destination.width, destination.height)));
    }
    
    
}