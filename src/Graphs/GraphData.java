package Graphs;


/**
 *
 * @author CollegeTier2.0
 */
public class GraphData implements Cloneable, Comparable
{
    private String origin, destination;
    private int cost;
    
    public GraphData(String o, String d, int c)
    {
        this.origin = o;
        this.destination = d;
        this.cost = c;
    }//End constructor
    
    public GraphData(String o, String d)
    {
        this.origin = o;
        this.destination = d;
        this.cost = 0;
    }
    
    public int getCost(){return cost;}
    
    
    
    @Override
    public GraphData clone() throws CloneNotSupportedException
    {
        super.clone();
        return new GraphData(origin, destination,  cost);         
    }
    
    public String getOrigin(){return this.origin;}
    public String getDestination(){return this.destination;}
    
    @Override
    public int compareTo(Object in)
    {
        GraphData obj = (GraphData) in;
        return this.origin.compareTo(obj.getOrigin()) + this.destination.compareTo(obj.getDestination());
    }
    @Override
    public String toString()
    {
        return (this.origin + "|" + this.destination + "|" + Integer.toString(this.cost));
    }
    
    
}
