package Graphs;


public class Plan
{
    private String origin, destination;
    
    public Plan(String o, String d)
    {
        this.origin = o;
        this.destination = d;
    }
   
    @Override
    public String toString()
    {
        return "From: " + this.origin + ", To: " + this.destination;
    }
    
    public String getOrigin(){return this.origin;}
    public String getDestination(){return this.destination;}
    
}
