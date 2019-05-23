package Graphs;


import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author CollegeTier2.0
 */
public class Graph
{
    private ArrayList<String> uniqueNodes;
    private GraphData[] grData;
    private int size;
    private int[][] cost;
    private boolean undirected;
    
    //Constructor for a directed graph
    public Graph(GraphData[] data)
    {
        String current;
        this.undirected = false;
        uniqueNodes = new ArrayList<>();
        grData = data;
        for (int i = 0; i  < data.length; i++)
        {
            current = data[i].getOrigin();
            if (!uniqueNodes.contains(current)) uniqueNodes.add(current);
            current = data[i].getDestination();
            if (!uniqueNodes.contains(current)) uniqueNodes.add(current);
        }
        Collections.sort(uniqueNodes);
        size = uniqueNodes.size();
        cost = new int [size][size];
        for (int i = 0; i < grData.length; i++)
        {
            int[] edge = getEdge(grData[i]);
            cost [edge[0]] [edge[1]] = grData[i].getCost();
        }
        
        
   
    }
    
    //Adding a boolean value will make the graph undirected
     public Graph(GraphData[] data, boolean undirected)
    {
        String current;
        this.undirected = undirected;
        uniqueNodes = new ArrayList<>();
        grData = data;
        for (int i = 0; i  < data.length; i++)
        {
            current = data[i].getOrigin();
            if (!uniqueNodes.contains(current)) uniqueNodes.add(current);
            current = data[i].getDestination();
            if (!uniqueNodes.contains(current)) uniqueNodes.add(current);
        }
        Collections.sort(uniqueNodes);
        size = uniqueNodes.size();
        cost = new int [size][size];
        for (int i = 0; i < grData.length; i++)
        {
            int[] edge = getEdge(grData[i]);
            cost [edge[0]] [edge[1]] = grData[i].getCost();
            if (undirected)
            {
                cost [edge[1]] [edge[0]] = grData[i].getCost();
            }
        }
        
   
    }
    
    @Override
    public String toString()
    {
        
        String toString = "To String";
        return toString;
        
    }
    
    private int[] getEdge(GraphData req)
    {
        int[] edge = new int[2];
        edge[0] = getIndex(req.getOrigin());
        edge[1] = getIndex(req.getDestination());
        if (edge[0] == -1 || edge[1] == -1)
        {
            int[] fail = new int[2];
            fail[0] = -1;
            fail[1] = -1;
            return fail;
        }
        return edge;
    }
    
    private double[] getEdge(Plan req)
    {
        double[] edge = new double[2];
        edge[0] = (double) getIndex(req.getOrigin());
        edge[1] = (double) getIndex(req.getDestination());
        if (edge[0] == -1 || edge[1] == -1)
        {
            double[] fail = new double[2];
            fail[0] = -1.0;
            fail[1] = -1.0;
            return fail;
        }
        return edge;
    }
    
    private int[] getEdge(Plan req, boolean option)
    {
        int[] edge = new int[2];
        edge[0] =  getIndex(req.getOrigin());
        edge[1] =  getIndex(req.getDestination());
        if (edge[0] == -1 || edge[1] == -1)
        {
            int[] fail = new int[2];
            fail[0] = -1;
            fail[1] = -1;
            return fail;
        }
        return edge;
    }
    
    public int getIndex(String in)
    {
        for (int i = 0; i < uniqueNodes.size(); i++)
        {
            if (uniqueNodes.get(i).compareTo(in) == 0) return i;
        }
        return -1;
    }
    
    //Find shortest path
    public ArrayList<Path> FloydWarshall(Plan req)
    {
        double[][] distance = new double[size][size];
        double[][] sequence = new double[size][size];
        int[] edge = getEdge(req, true);
        ArrayList<Path> flights = new ArrayList<>();
        //Initialize matrices
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (cost[i][j] == 0)
                    distance[i][j] = Double.POSITIVE_INFINITY;
                else
                    distance[i][j] = cost[i][j];
                if (i == j)
                    sequence [i][j] = Double.POSITIVE_INFINITY;
                else
                    sequence[i][j] = j;
            }
        }
        for (int k = 1; k <= size; k++)
        {
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                {
                    if (i != j)
                    {
                        if (distance [i][j] > distance [i][k-1] + distance[k-1][j])
                        {
                            distance[i][j] = distance [i][k-1] + distance[k-1][j];
                            sequence[i][j] = k - 1;
                        }
                        if (distance[edge[0]][edge[1]] < Double.POSITIVE_INFINITY)
                        {
                            Path potential = getPath(distance, sequence, req);
                            if (!contains(flights,potential))
                                flights.add(potential);
                        }
                    }
                        
                }
            }
        }
        return flights;
    }
    
    //Find longest path without repeats
    public ArrayList<Path> InverseFloydWarshall(Plan req, ArrayList<Path> paths)
    {
        double[][] distance = new double[size][size];
        double[][] sequence = new double[size][size];
        int[] edge = getEdge(req, true);
        ArrayList<Path> flights = new ArrayList<>();
        //Initialize matrices
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (cost[i][j] == 0)
                    distance[i][j] = Double.NEGATIVE_INFINITY;
                else
                    distance[i][j] = cost[i][j];
                if (i == j)
                    sequence [i][j] = Double.POSITIVE_INFINITY;
                else
                    sequence[i][j] = j;
            }
        }
        for (int k = 1; k <= size; k++)
        {
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                {
                    if (i != j)
                    {
                        if (distance [i][j] < distance [i][k-1] + distance[k-1][j])
                        {
                            distance[i][j] = distance [i][k-1] + distance[k-1][j];
                            sequence[i][j] = k - 1;
                        }
                        if (distance[edge[0]][edge[1]] > Double.NEGATIVE_INFINITY)
                        {
                            Path potential = getPath(distance, sequence, req);
                            if (potential != null && !contains(flights,potential) && !contains(paths,potential))
                                flights.add(potential);
                        }
                    }
                        
                }
            }
        }
        return flights;
    }
    
    private boolean contains(ArrayList<Path> list, Path path)
    {
       boolean contains = false;
       for (int i = 0; i < list.size(); i++)
       {
           if (list.get(i).equals(path))
               contains = true;
       }
       return contains;
    }
    public Path getPath(double[][] distance, double[][] sequence,Plan req)
    {
        double[] edge = getEdge(req);
        ArrayList<Double> path = new ArrayList<>();
        while(sequence[(int)edge[0]][(int)edge[1]] != edge[1])
        {
            if (path.contains(getEdge(req)[0]))
                return null;
            if (path.contains(sequence[(int)edge[0]][(int)edge[1]]))
                    break;
            
            path.add(sequence[(int)edge[0]][(int)edge[1]]);
            edge[0] = sequence[(int)edge[0]][(int)edge[1]];
            
        }
        path.add(0, getEdge(req)[0]);
        path.add(path.size(), getEdge(req)[1]);
        String pathName = "";
        for (int i = 0; i < path.size(); i++)
        {
            if (i != 0)
                pathName += " ---> ";
            int nextIndex, index = (int) Math.round(path.get(i));
            if (i < path.size() - 1)
            {
                nextIndex = (int) Math.round(path.get(i+1));
            }
            pathName += uniqueNodes.get(index);
        }
        
        return new Path((int)distance[(int)getEdge(req)[0]][(int)getEdge(req)[1]],req,pathName);
    }
    
    public void displayGraph()
    {
        JFrame disp = new JFrame();
        disp.setSize(700, 700);
        disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        disp.setVisible(true);
        ArrayList<Dimension> nodes = new ArrayList<>();
        Random r = new Random();
        int rectx = 25, recty = 25;
        for (int i = 0; i < size; i++)
        {
            int x = Math.abs(r.nextInt() % (disp.getWidth() - rectx/4));
            int y = Math.abs(r.nextInt() % (disp.getHeight() - rectx/4));
            Node current = new Node(new Point(x,y), uniqueNodes.get(i));
            disp.add(current);
            disp.paintAll(disp.getGraphics());
            nodes.add(new Dimension(x + (rectx/2), y + (recty/2)));
        }//End for
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (cost[i][j] != 0)
                {
                    disp.add(new Edge(nodes.get(i), nodes.get(j), cost[i][j]));
                    disp.paintAll(disp.getGraphics());
                }//End if
            }//End for 
        }//End for
    }//End displayGraph
    
    public void scaledDisp()
    {
        HashMap<Node, Point> coordinates = new HashMap<>();
        JFrame disp = new JFrame();
        disp.setSize(700, 700);
        disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        disp.setVisible(true);
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (coordinates.isEmpty())
                {
                    Point currentPt = new Point(disp.getWidth(), disp.getHeight());
                    Node current = new Node(currentPt, uniqueNodes.get(i));
                    coordinates.put(current, currentPt);
                }
            }//End for
        }//End for
    }//End scaled disp
    
    public ArrayList<String> getNodes(){return uniqueNodes;}
    
}
