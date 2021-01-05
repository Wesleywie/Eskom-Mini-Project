package Main;
import java.util.List;
/**
 * Main Class file
 * @author Wesley Wienand
 */
import java.util.ArrayList;
import java.util.Collection;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.TYPE;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import UI.GraphPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{		
	
	private GraphPane pane = null;
	
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		//create the Graph, set up the Scene and Stage
		primaryStage.setTitle("Eskom Resource Allocation Project");
		//Creates the ClientPane
		pane = new GraphPane();
		
		//Set the Scene
		Scene scene = new Scene(pane);
	//	scene.getStylesheets().add("file:data/stylesheet.css"); style sheet attempt
		
		primaryStage.setWidth(1220);
		primaryStage.setHeight(880);
		primaryStage.setScene(scene);
		
		//primary stage.setMaximized
		
		//Shows GUI
		primaryStage.show();	
	}

}
