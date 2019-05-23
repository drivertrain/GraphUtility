package Graphs;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Chase Joshua Uphaus
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Graph graph = new Graph(readData(args[0]));
        ArrayList<String> cities = graph.getNodes();
        Plan[] plans = getPlans(args[1]);
        ArrayList<Path> paths = new ArrayList<>();
        ArrayList<ArrayList<Path>> allPaths = new ArrayList<>();
        ArrayList<Path> uniques = new ArrayList<>();
        for (int i = 0; i < plans.length; i++)
        {    
            
            paths = (graph.FloydWarshall(plans[i]));               
            paths.addAll(graph.InverseFloydWarshall(plans[i], paths));


            allPaths.add(paths);                        
        }
        printData(allPaths,plans,args[2]);
        graph.displayGraph();
    }
    
    public static GraphData[] readData(String fileName)
    {
        try
        {
            File data = new File(fileName);
            Scanner input = new Scanner(data);
            int size = Integer.parseInt(input.nextLine());
            String[] container;
            GraphData[] arr = new GraphData[size];
            for (int i = 0; i < size; i++)
            {
                String line = input.nextLine();
                container = line.split("\\|");
                arr[i] = new GraphData(container[0],container[1], Integer.parseInt(container[2]));                
            }
            return arr;
        }
        catch(Exception e){e.printStackTrace(); return null;}
    }
    
    public static Plan[] getPlans(String filename)
    {
         try
        {
            File data = new File(filename);
            Scanner input = new Scanner(data);
            int size = Integer.parseInt(input.nextLine());
            String[] container;
            Plan[] arr = new Plan[size];
            for (int i = 0; i < size; i++)
            {
                String line = input.nextLine();
                container = line.split("\\|");
                arr[i] = new Plan(container[0],container[1]);                
            }
            return arr;
        }
        catch(Exception e){e.printStackTrace(); return null;}
    }
    
    public static void printData(ArrayList<ArrayList<Path>> paths, Plan[] plans, String outputFile)
    {
        try
        {
            PrintWriter output = new PrintWriter(new File(outputFile));
        
            for (int i = 0; i < plans.length; i++)
            {
                output.println("Request: " + (i + 1) + "   " + plans[i].toString());
                if (paths.get(i).size() == 0)
                    output.println("   Error, no such route is available...");
                for (int j = 0; j < paths.get(i).size() &&  j < 3; j++)
                {
                    output.println("   Path: " + (j + 1) + "  " + paths.get(i).get(j).toString());
                }
            }
            output.flush();
            output.close();
        }
        catch (FileNotFoundException ex){ex.printStackTrace();}
    }
    
    
    
    public static boolean contains(ArrayList<Path> uniques, Path current)
    {
        boolean contains = false;
        for (int i = 0; i < uniques.size(); i++)
        {
            if (uniques.get(i).equals(current))
            {
                contains = true;
                break;
            }
        }
        return contains;
        
    }
    
}
