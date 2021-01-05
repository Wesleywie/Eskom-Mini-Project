package Model;

import com.jwetherell.algorithms.data_structures.Graph;

/**
 * 
 * @Reference https://algorithms.tutorialhorizon.com/graph-implementation-adjacency-matrix-set-3/
 * 
 * @Editor Wesley Wienand
 */

public class GraphAjdacencyMatrix extends Graph
{	
	   int vertex; //Number of vertexs
	   public int matrix[][];

	   /**
	    * Constructor
	    * @param vertex
	    */
	    public GraphAjdacencyMatrix(int vertex) 
	    {
	    	super(); //call to super class ... extends Graph
	        this.vertex = vertex;
	        matrix = new int[vertex][vertex];
	    }

	    /**
	     * Add Edge
	     * @param source
	     * @param destination
	     */
	    public void addEdge(int source, int destination) 
	    {
	        //add edge
	        matrix[source][destination] = 1;
	    }

	    /**
	     * Print Adj Matrix
	     */
	    public void printGraph() 
	    {
	        System.out.println("Graph: (Adjacency Matrix)");
	        for (int i = 0; i < vertex; i++) 
	        {
	            for (int j = 0; j <vertex ; j++) 
	            {
	                System.out.print(matrix[i][j]+ " ");
	            }
	            System.out.println();
	        }
	        
	        for (int i = 0; i < vertex; i++) 
	        {
	            System.out.print("Vertex " + i + " is connected to:");
	            
	            for (int j = 0; j <vertex ; j++) 
	            {
	                if(matrix[i][j]==1)
	                {
	                    System.out.print(j + " ");
	                }
	            }
	            System.out.println();
	        }

}
	    
}
