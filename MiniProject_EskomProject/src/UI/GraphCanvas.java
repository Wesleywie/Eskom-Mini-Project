package UI;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
/**
 * Graph Canvas class
 * Used to construct and deploy graph visually
 * @author Wesley Wienand
 *
 */
public class GraphCanvas extends Canvas
{
	public int Count = 0; //keeps track of number of nodes
	public String ComboEdge = ""; //Combonation of Nodes .. AZ
	
	GraphicsContext gc = getGraphicsContext2D();
	/**
	 * Default Constructor
	 */
	public GraphCanvas() 
	{
		setHeight(600); //Size setting for canvas
		setWidth(1200);		
		
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		gc.strokeRect(1, 1, 1195, 595);
		
	}
	
	/**
	 * Sets all Nodess
	 */
	public void updateGraph()
	{
		
		gc.clearRect(1, 1, 1100, 590); //resets canvas
		
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		gc.strokeRect(1, 1, 1195, 595); //Border
		
		gc.strokeText("(Z)Load centre", 450, 245); //distribution center electricity
		gc.setFill(Color.BLUE);
		gc.fillRect(525, 250, 50, 50); //1
		
		
		for(int i = 1; i < Count+1;i++)
		{

			if(i == 1)
			{
				gc.setFill(Color.RED);
				gc.fillOval(525, 50, 40, 40); //2 //Coal Station Node
				gc.strokeText("A", 525, 50); //Label
			}
			else if(i == 2)
			{
				gc.setFill(Color.RED);
				gc.fillOval(675, 75, 40, 40); //3
				gc.strokeText("B", 675, 75);
			}
			else if(i == 3)
			{
				gc.setFill(Color.RED);
				gc.fillOval(750, 250, 40, 40); //4
				gc.strokeText("C", 750, 250);
			}
			else if(i == 4)
			{
				gc.setFill(Color.RED);
				gc.fillOval(675, 400, 40, 40); //5	
				gc.strokeText("D", 675, 400);
			}
			else if(i == 5)
			{
				gc.setFill(Color.RED);
				gc.fillOval(525, 450, 40, 40); //6	
				gc.strokeText("E", 525, 450);
			}
			else if(i == 6)
			{
				gc.setFill(Color.RED);
				gc.fillOval(375, 400, 40, 40); //7
				gc.strokeText("F", 375, 400);
			}
			else if(i == 7)
			{
				gc.setFill(Color.RED);
				gc.fillOval(300, 250, 40, 40); //8
				gc.strokeText("G", 300, 250);
			}
			else if(i == 8)
			{
				gc.setFill(Color.RED);
				gc.fillOval(375, 75, 40, 40); //9
				gc.strokeText("H", 375, 75);
			}	
		}
		
		
	}
	
	/**
	 * Sets graph values on canvas
	 * @param allVertices
	 * Takes in vertices to display weight 
	 */
	public void updateGraphValues(List<Vertex> allVertices)
	{		
		int i = 0; //count
		
		int size = allVertices.size();
		
		gc.setStroke(Color.YELLOW);
		gc.strokeText(allVertices.get(size-1).getWeight() + "MW", 528, 275); //sets weight to node
		
		for(i = 0; i < Count;i++)
		{

			if(i == 0)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allVertices.get(i).getWeight()  + "MW", 525, 75);
			}
			else if(i == 1)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allVertices.get(i).getWeight()  + "MW", 675, 100);
			}
			else if(i == 2)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allVertices.get(i).getWeight()  + "MW", 750, 275);
			}
			else if(i == 3)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allVertices.get(i).getWeight()  + "MW", 675, 425);
			}
			else if(i == 4)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allVertices.get(i).getWeight()  + "MW", 525, 475);
			}
			else if(i == 5)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allVertices.get(i).getWeight()  + "MW", 375, 425);
			}
			else if(i == 6)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allVertices.get(i).getWeight()  + "MW", 300, 275);
			}
			else if(i == 7)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allVertices.get(i).getWeight()  + "MW", 375, 100);
			}	
		}		
		
	}
	
	/**
	 * Sets Edge values on canvas
	 * @param allEdges
	 * Takes in edges to display cost 
	 */
	public void updateEdgeValues(List<Edge> allEdges)
	{		
		int i = 0;

		for(i = 0; i < Count;i++)
		{

			if(i == 0)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allEdges.get(i).getCost()  + "MW", 550, 200); //sets cost to line
			}
			else if(i == 1)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allEdges.get(i).getCost()   + "MW", 640, 160);
			}
			else if(i == 2)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allEdges.get(i).getCost()   + "MW", 680, 265);
			}
			else if(i == 3)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allEdges.get(i).getCost()   + "MW", 640, 380);
			}
			else if(i == 4)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allEdges.get(i).getCost()   + "MW", 525, 420);
			}
			else if(i == 5)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allEdges.get(i).getCost()   + "MW", 365, 380);
			}
			else if(i == 6)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allEdges.get(i).getCost()   + "MW", 360, 260);
			}
			else if(i == 7)
			{
				gc.setStroke(Color.BLACK);
				gc.strokeText(allEdges.get(i).getCost()   + "MW", 425, 145);
			}	
		}		
		
	}
	
	/**
	 * Draws initial edges
	 * @param numofPoint
	 */
	public void updateGraphEdge(String numofPoint)
	{		
		
		if(ComboEdge.contentEquals("0" + numofPoint))
		{
			gc.setFill(Color.YELLOW); //represents electrical lines
			gc.setLineWidth(2);
			gc.strokeLine(545, 90, 550, 250); //x1, y1, x2, y2
		}
		else if(ComboEdge.contentEquals("1" + numofPoint))
		{
			gc.setFill(Color.YELLOW); //represents electrical lines
			gc.setLineWidth(2);
			gc.strokeLine(675, 90, 575, 250); //x1, y1, x2, y2
		}
		else if(ComboEdge.contentEquals("2" + numofPoint))
		{
			gc.setFill(Color.YELLOW); //represents electrical lines
			gc.setLineWidth(2);
			gc.strokeLine(750, 265, 575, 275); //x1, y1, x2, y2
		}
		else if(ComboEdge.contentEquals("3" + numofPoint))
		{
			gc.setFill(Color.YELLOW); //represents electrical lines
			gc.setLineWidth(2);
			gc.strokeLine(675, 420, 575, 300); //x1, y1, x2, y2 
		}
		else if(ComboEdge.contentEquals("4" + numofPoint))
		{
			gc.setFill(Color.YELLOW); //represents electrical lines
			gc.setLineWidth(2);
			gc.strokeLine(545, 450, 550, 300); //x1, y1, x2, y2
		}
		else if(ComboEdge.contentEquals("5" + numofPoint))
		{
			gc.setFill(Color.YELLOW); //represents electrical lines
			gc.setLineWidth(2);
			gc.strokeLine(390, 400, 530, 300); //x1, y1, x2, y2 
		}
		else if(ComboEdge.contentEquals("6" + numofPoint))
		{
			gc.setFill(Color.YELLOW); //represents electrical lines
			gc.setLineWidth(2);
			gc.strokeLine(340, 270, 525, 270); //x1, y1, x2, y2 
		}
		else if(ComboEdge.contentEquals("7" + numofPoint))
		{
			gc.setFill(Color.YELLOW); //represents electrical lines
			gc.setLineWidth(2);
			gc.strokeLine(400, 115, 540, 250); //x1, y1, x2, y2
		}

	}
	
	/**
	 * Visually displays traversal of directed connected graph
	 * @throws InterruptedException
	 */
	public void ShowTraversal() throws InterruptedException
	{
		
		gc.clearRect(1, 1, 1100, 590); //resets canvas
		
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		gc.strokeRect(1, 1, 1195, 595);
		
		gc.strokeText("(Z)Load centre", 450, 245); //distribution centre electricity
		gc.setFill(Color.BLUE);
		gc.fillRect(525, 250, 50, 50); //1
		
		gc.setStroke(Color.GREEN);
		gc.strokeRect(525, 250, 50,50);
		
		
		for(int i = 1; i < Count+1;i++)
		{

			if(i == 1) //01
			{
				gc.setStroke(Color.YELLOW);
				gc.setLineWidth(2);
				gc.strokeLine(545, 90, 550, 250); //x1, y1, x2, y2							
				
				gc.setFill(Color.RED);		
				gc.fillOval(525, 50, 40, 40); //2
				
				gc.setStroke(Color.BLACK);			
				gc.strokeText("A", 525, 50);
				
				gc.setStroke(Color.YELLOW);
				gc.strokeOval(525, 50, 40,40);

			}
			else if(i == 2) //02
			{
				gc.setStroke(Color.YELLOW);
				gc.setLineWidth(2);
				gc.strokeLine(675, 90, 575, 250); //x1, y1, x2, y2
				
				gc.setFill(Color.RED);
				gc.fillOval(675, 75, 40, 40); //3
				
				gc.setStroke(Color.BLACK);
				gc.strokeText("B", 675, 75);
				
				gc.setStroke(Color.YELLOW);
				gc.strokeOval(675, 75, 40,40);
			}
			else if(i == 3) //03
			{
				gc.setStroke(Color.YELLOW);
				gc.setLineWidth(2);
				gc.strokeLine(750, 265, 575, 275); //x1, y1, x2, y2
				
				gc.setFill(Color.RED);
				gc.fillOval(750, 250, 40, 40); //4				
				
				gc.setStroke(Color.BLACK);
				gc.strokeText("C", 750, 250);
				
				gc.setStroke(Color.YELLOW);
				gc.strokeOval(750, 250, 40,40);
				
			}
			else if(i == 4) //04
			{			
				gc.setStroke(Color.YELLOW);
				gc.setLineWidth(2);
				gc.strokeLine(675, 420, 575, 300); //x1, y1, x2, y2
				
				gc.setFill(Color.RED);
				gc.fillOval(675, 400, 40, 40); //5	
				
				gc.setStroke(Color.BLACK);
				gc.strokeText("D", 675, 400);

				gc.setStroke(Color.YELLOW);
				gc.strokeOval(675, 400, 40,40);
			}
			else if(i == 5)
			{
				gc.setStroke(Color.YELLOW);
				gc.setLineWidth(2);
				gc.strokeLine(545, 450, 550, 300);
				
				gc.setFill(Color.RED);
				gc.fillOval(525, 450, 40, 40); //6	
				
				gc.setStroke(Color.BLACK);
				gc.strokeText("E", 525, 450);
				
				gc.setStroke(Color.YELLOW);
				gc.strokeOval(525, 450, 40,40);
			}
			else if(i == 6)
			{
				gc.setStroke(Color.YELLOW);
				gc.setLineWidth(2);
				gc.strokeLine(390, 400, 530, 300);
				
				gc.setFill(Color.RED);
				gc.fillOval(375, 400, 40, 40); //7
				
				gc.setStroke(Color.BLACK);
				gc.strokeText("F", 375, 400);
				
				gc.setStroke(Color.YELLOW);
				gc.strokeOval(375, 400, 40,40);
			}
			else if(i == 7)
			{
				gc.setStroke(Color.YELLOW);
				gc.setLineWidth(2);
				gc.strokeLine(340, 270, 525, 270);
				
				gc.setFill(Color.RED);
				gc.fillOval(300, 250, 40, 40); //8
				
				gc.setStroke(Color.BLACK);
				gc.strokeText("G", 300, 250);
				
				gc.setStroke(Color.YELLOW);
				gc.strokeOval(300, 250, 40,40);
			}
			else if(i == 8)
			{
				gc.setStroke(Color.YELLOW);
				gc.setLineWidth(2);
				gc.strokeLine(400, 115, 540, 250);
				
				gc.setFill(Color.RED);
				gc.fillOval(375, 75, 40, 40); //9
				
				gc.setStroke(Color.BLACK);
				gc.strokeText("H", 375, 75);
				
				gc.setStroke(Color.YELLOW);
				gc.strokeOval(375, 75, 40,40);
				
			}	
		}	
		
	}
	/**
	 * Adds Nodes to Graph
	 * @param Num
	 */
	public void addNode(int Num) 
	{
		this.Count = Num; //sets counter
		updateGraph(); //runs updateGraph function to display nodes
	}
	
	public void addEdge(String Combo, String num)
	{
		this.ComboEdge = Combo; //sets Current Combo
		updateGraphEdge(num); //runs updateGraphEdge function to display edges
	}
	
	

}
