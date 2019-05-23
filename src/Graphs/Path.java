package Graphs;
import java.util.Objects;


public class Path implements Comparable
{
    private int cost;
    private Plan request;
    private String path;
    
    public Path(int c, Plan f, String p)
    {
        this.cost = c;
        this.request = f;
        this.path = p;
    }
    
    public Path()
    {
        this(999,null,"");
    }
    
    @Override
    public boolean equals(Object o)
    {
        Path comp;
        if (o instanceof Path)
            comp = (Path) o;
        else
            comp = new Path();
        return ((comp.cost == this.cost) && (comp.path.compareTo(this.path) == 0));
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + this.cost;
        hash = 17 * hash + Objects.hashCode(this.request);
        hash = 17 * hash + Objects.hashCode(this.path);
        return hash;
    }
    
   
    
    @Override
    public String toString()
    {
        return path + " Cost: " + this.cost;
                
    }

    @Override
    public int compareTo(Object o)
    {
        Path in = (Path) o;
        return (this.cost - in.cost);     
    }
}
