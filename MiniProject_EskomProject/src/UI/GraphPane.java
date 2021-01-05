package UI;

import java.io.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.swing.JOptionPane;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import com.jwetherell.algorithms.data_structures.DepthFirstTraversal;

import Model.GraphAjdacencyMatrix;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

import UI.GraphCanvas;

/**
 * Graph Pane class Structures GUI and Calls Sequence to construct and deploy graph
 * @author Wesley Wienand
 *Information gathered here
 *OOP
 */
public class GraphPane extends GridPane
{	
	//Variables
	GraphCanvas graphC = null;
	
	public Graph Gstruct = null;
	
	public GraphAjdacencyMatrix amStruct = null;
	
	public TextField txtStations = new TextField();
	
	public TextArea textArea = new TextArea();
	
	/**
	 * Default Constructor
	 */
	public GraphPane()
	{
		graphC = new GraphCanvas();
		
		//Buttons
		Label lblVetrices = new Label("Coal Stations:");
		
		Button btnDrawNode = new Button("Deploy"); //buttons
		
		Button btnAssignWeight = new Button("Weight"); //buttons
		
		Button btnDFS = new Button("Depth First Search Traversal"); //buttons
		
		Button btnInfrastructureloadbalancing = new Button("Load Balance"); //buttons
	
		textArea.setScaleX(1);
		
		btnDrawNode.setOnAction(e -> //btndrawNode
		{
			if(Integer.parseInt(txtStations.getText()) <= 8) //error proofing ... Can only deploy up to 8 Coal stations ... Logical
			{
				graphC.addNode(Integer.parseInt(txtStations.getText())); //Draws Node
			}
			
			textArea.clear();

		});		
				
		btnAssignWeight.setOnAction(e -> //Logically Constructs Graph
		{
			Gstruct = ConstructVertexs(Integer.parseInt(txtStations.getText()) +1); //Graph construction
			
			//matrix conversion					
			amStruct = ConvertGraphToAdjMatrix(Gstruct); //convert graph to adjacency matrix graph
			
			//print edges
			printEdges(amStruct);	//Plus add values and weight	
			
			graphC.updateGraphValues(Gstruct.getVertices());
			
			graphC.updateEdgeValues(Gstruct.getEdges());			
			
		});		
		
		btnDFS.setOnAction(e -> //btnDFS
		{		
			
			DFT(amStruct); 	// Visualize Traversal
			
			try 
			{
				graphC.ShowTraversal();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
			
			graphC.updateEdgeValues(Gstruct.getEdges());
			graphC.updateGraphValues(Gstruct.getVertices());
			
			Output(); //outputs graph information
			
		});		
		
		btnInfrastructureloadbalancing.setOnAction(e -> 
		{						
			resultR(); //display result for current data
		});		
		
		//GUI Assembly
		Image Background = new Image("file:src/data/Images/BackGround2.jpg");
		
		BackgroundSize bgSize = new BackgroundSize(0, 0, false, false, false, true);
		
		BackgroundImage bgImg = new BackgroundImage(Background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bgSize);
		
		Background bg = new Background(bgImg);
		
		BorderPane mPane = new BorderPane();
		mPane.setBackground(bg);
		
		VBox layout = new VBox();  //Layout	
		HBox Layout2 = new HBox();		
		
		HBox Layout3 = new HBox();	
		
		Layout3.setPadding(new Insets(10, 300, 20, 300));
		
		Layout3.getChildren().addAll(lblVetrices,txtStations,btnDrawNode,btnAssignWeight,btnDFS,btnInfrastructureloadbalancing);	

		layout.getChildren().addAll(graphC,Layout3,textArea);
		
		getChildren().addAll(mPane,layout); 	
	}
	
	/**
	 * 
	 * @param i
	 * @return Alphabet character based on number 
	 * @reference https://stackoverflow.com/questions/10813154/how-do-i-convert-a-number-to-a-letter-in-java 
	 * @owner adarshr
	 */
	private String getCharForNumber(int i)  //converts number to alphabet char
	{
	    return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
	}
	
	/**
	 * Constructs graph
	 * Takes in collection of vertices
	 * Takes in Collection of edges
	 * Then Constructs graph
	 * @param numVertexs
	 * @return graph
	 */
	public Graph ConstructVertexs(int numVertexs) //constructs graph object
	{
		
		List<Vertex> allVertices = new ArrayList<Vertex>();
		List<Edge> allEdges = new ArrayList<Edge>();	
		
		for(int i = 0; i < numVertexs; i++)
		{				
			
			if(i == numVertexs-1)
			{
				String Weight = JOptionPane.showInputDialog("Please input demand from LoadCentre " + "Z"); //inputs for weights
				Vertex vLC = new Vertex(i, Integer.parseInt(Weight)); //value + weight
				allVertices.add(vLC);
			}
			else
			{
			String Weight = JOptionPane.showInputDialog("Please input how much Coal Station " + getCharForNumber(i+1) + " can produce"); //inputs for weights			
				Vertex v1 = new Vertex(i+1, Integer.parseInt(Weight)); //value + weight	
				allVertices.add(v1);
			}
			
		}
		
		for(int i = 0; i < numVertexs-1; i++)
		{
				String cost = JOptionPane.showInputDialog("Enter in how much the coal station " + getCharForNumber(i+1) + " is producing to Z(Load Centre)" + '\n' + "Production Capacity: " + allVertices.get(i).getWeight() + "MW"); //inputs for weights
				Edge e1 = new Edge(Integer.parseInt(cost),allVertices.get(i),allVertices.get(numVertexs-1)); //cost + from, to	
				
				allEdges.add(e1);			
		}
		
		Graph g = new Graph(allVertices, allEdges); //build and construct graph		
		
		System.out.println(g + "\n");
		System.out.println(g.getEdges());
		
		return g;
		
	}
	
	/**
	 * Converts normal Graph to Adjacency Matrix
	 * @param g
	 * @return GraphAjdacencyMatrix
	 */
	public GraphAjdacencyMatrix ConvertGraphToAdjMatrix(Graph g)
	{
		List<Vertex> allVertices = new ArrayList<Vertex>();
		List<Edge> allEdges = new ArrayList<Edge>();
		
		int numStation = Integer.parseInt(txtStations.getText()) + 1;
		
		GraphAjdacencyMatrix amGraph = new GraphAjdacencyMatrix(numStation); //Initialize how many nodes there are
		
		allEdges.addAll(g.getEdges());
		allVertices.addAll(g.getVertices());
		
				
			for(int i = 0; i < numStation-1;i++)
			{
				if(i != numStation)
				{
					amGraph.addEdge(i, numStation-1);
				}
	
			}								
			
			amGraph.printGraph();

			return amGraph;	
	}
	
	/**
	 * Prints edges
	 * @param G
	 */
	public void printEdges(GraphAjdacencyMatrix G)
	{
		
		int numStations = Integer.parseInt(txtStations.getText()) + 1;
		int tracker = 0;
		int count = 0;
		
		for(int r = 0; r < numStations;r++)
		{
			for(int c = 0; c < numStations;c++)
			{
				
				tracker = G.matrix[r][c];
				
				if(tracker == 1)
				{
					String row = String.valueOf(r);
					String col = String.valueOf(c);
					graphC.addEdge(row + col, String.valueOf(numStations-1));

				}
				
				count++;			
					
			}			

		}
		
		System.out.println(count);
		
	}
	
	/**
	 * Depth First Search that takes GraphAjdacencyMatrix
	 * @param adjMatrix
	 */
	public void DFT(GraphAjdacencyMatrix adjMatrix) //Depth first Traversal
	{
		int[] Arr;
		
		int numNodes = Integer.parseInt(txtStations.getText()) + 1;
		
		Arr = DepthFirstTraversal.depthFirstTraversal(numNodes, adjMatrix.matrix, 0);
		
		String temp = "";
		
		for(int i = 0; i < Arr.length;i++)
		{
			temp += Arr[i] + " ";
		}
		
		System.out.println(temp);
		
	}
	
	/**
	 * Constructs Output once graph is fully assembled and traversed
	 */
	public void Output()
	{
		String Temp = "";
		
		Temp = "-----------------------------------" + '\n';
		Temp += "Eskom Load Balancing" + '\n';
		Temp += "------------------------------------" + '\n';
		
		Temp += "Graph: Directed from Coals Station To Load Centre" + '\n' + '\n';
		
		int numCS = Gstruct.getVertices().size() - 1;		
		Temp += "Number of Coal Stations: " + numCS + '\n';
		
		List<Vertex> allVertices = Gstruct.getVertices();
		List<Edge> allEdges = Gstruct.getEdges();
		
		Vertex lcD = allVertices.get(numCS);				
		Temp += "Load Centre Demand: " + lcD.getWeight() + "MW" + '\n' + '\n';
		
		
		int CSWeights = 0;
		
		int CostsAdded = 0;
		
		for(int i = 0; i < numCS; i++)
		{
			CSWeights += allVertices.get(i).getWeight();
			
			CostsAdded += allEdges.get(i).getCost();
		}
		
		int Avrg = CostsAdded/numCS;
		
		Temp += "Average power supply: " + Avrg + "MW" + '\n';
		
		Temp += "Total power supply: " + CostsAdded + "MW" + '\n';
		
		if(lcD.getWeight() > CostsAdded)
		{
			Temp += "Meeting Requirements: NO" + '\n' + '\n';	
		}
		else
		{
			Temp += "Meeting Requirements: YES" + '\n' + '\n';
		}
		
		
		Vertex Comparsion = allVertices.get(0);
		
		int save = 1;
		
		for(int i = 1; i < numCS; i++)
		{
			if(allVertices.get(i).getWeight() < Comparsion.getWeight())
			{
				save = i;
			}
		}
		
		String WCS = getCharForNumber(save);
		
		Temp += "Weakest Coal Station: " + WCS + '\n';
		
		
		Edge ComparsionEdge = allEdges.get(0);
		
		for(int i = 1; i < numCS; i++)
		{
			if(allEdges.get(i).getCost() < ComparsionEdge.getCost())
			{
				ComparsionEdge = allEdges.get(i);
			}
		}
		
		Comparable EdgeNum = ComparsionEdge.getFromVertex().getValue();
		
		String Combo = "";
		
		if(EdgeNum.equals(1))
		{
			Combo = "AZ";
		}
		else if(EdgeNum.equals(2))
		{
			Combo = "BZ";
		}
		else if(EdgeNum.equals(3))
		{
			Combo = "CZ";
		}
		else if(EdgeNum.equals(4))
		{
			Combo = "DZ";
		}
		else if(EdgeNum.equals(5))
		{
			Combo = "EZ";
		}
		else if(EdgeNum.equals(6))
		{
			Combo = "FZ";
		}
		else if(EdgeNum.equals(7))
		{
			Combo = "GZ";
		}
		else if(EdgeNum.equals(8))
		{
			Combo = "HZ";
		}
		
		
		Temp += "Weakest RelationShip: " + Combo +'\n';
		
		Temp += "-----------------------------------";
		
		textArea.setText(Temp);
		
	}
	
	/**
	 * resultR()
	 * returns result regarding load balancing
	 */
	public void resultR() //returns result regarding load balancing
	{
		
		String Temp = "";
		
		Temp = "-----------------------------------" + '\n';
		Temp += "Load Balancing " + '\n';
		Temp += "------------------------------------" + '\n';
		
		int numCS = Gstruct.getVertices().size() - 1;		
		Temp += "Number of Coal Stations: " + numCS + '\n';
		
		List<Vertex> allVertices = Gstruct.getVertices();
		List<Edge> allEdges = Gstruct.getEdges();
		
		Vertex lcD = allVertices.get(numCS);						
		
		int CSWeights = 0;
		
		int CostsAdded = 0;
		
		for(int i = 0; i < numCS; i++)
		{
			CSWeights += allVertices.get(i).getWeight();
			
			CostsAdded += allEdges.get(i).getCost();
		}
		
		int Avrg = CostsAdded/numCS;
		
		Temp += "Average power supply: " + Avrg + "MW" + '\n';
		
		Temp += "Total power supply: " + CostsAdded + "MW" + '\n';
		
		if(lcD.getWeight() > CostsAdded)
		{
			Temp += "Meeting Requirements: NO" + '\n' + '\n';	
		}
		else
		{
			Temp += "Meeting Requirements: YES" + '\n' + '\n';
		}
		
		if(lcD.getWeight() > CostsAdded)
		{
			Vertex Comparsion = allVertices.get(0);
			
			int save = 1;
			
			for(int i = 1; i < numCS; i++)
			{
				if(allVertices.get(i).getWeight() < Comparsion.getWeight())
				{
					save = i;
				}
			}
			
			String WCS = getCharForNumber(save);
			
			Temp += "Weakest Coal Station: " + WCS + '\n';
			
			
			Edge ComparsionEdge = allEdges.get(0);
			
			for(int i = 1; i < numCS; i++)
			{
				if(allEdges.get(i).getCost() < ComparsionEdge.getCost())
				{
					ComparsionEdge = allEdges.get(i);
				}
			}
			
			Comparable EdgeNum = ComparsionEdge.getFromVertex().getValue();
			
			String Combo = "";
			
			if(EdgeNum.equals(1))
			{
				Combo = "AZ";
			}
			else if(EdgeNum.equals(2))
			{
				Combo = "BZ";
				WCS = "B";
			}
			else if(EdgeNum.equals(3))
			{
				Combo = "CZ";
				WCS = "C";
			}
			else if(EdgeNum.equals(4))
			{
				Combo = "DZ";
				WCS = "D";
			}
			else if(EdgeNum.equals(5))
			{
				Combo = "EZ";
				WCS = "E";
			}
			else if(EdgeNum.equals(6))
			{
				Combo = "FZ";
				WCS = "F";
			}
			else if(EdgeNum.equals(7))
			{
				Combo = "GZ";
				WCS = "G";
			}
			else if(EdgeNum.equals(8))
			{
				Combo = "HZ";
				WCS = "H";
			}
			
			
			Temp += "Weakest RelationShip: " + Combo +'\n';		
		
			
			int diffAvrg = ((lcD.getWeight() - CostsAdded)/numCS);
			
			int diff = ((Avrg - allEdges.get(save).getCost()) +  diffAvrg);
			
			Temp += "Coal Station: " + WCS + " Requires : " + diff + " More MegaWatts to support Average power supply" +'\n';
			
			Temp += "All Coal stations must produce " + diffAvrg + " more MegaWatts to support Average power supply to reach Target" + '\n';
			
			Temp += "-----------------------------------";
		}
		else
		{
			Temp += "Load Balance is Stable" + '\n';
			
			Temp += "-----------------------------------";
		}
		
		
	
		
		textArea.setText(Temp);
		
	}
	

}
	



