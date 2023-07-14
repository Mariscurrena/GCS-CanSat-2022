package application;
	
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);

    }
    private static Stage guiStage;
    static  private LoadScreen load;
    private int x;
    private int y;
    static private FlightMode flight;
    static private SimulationMode sim;

    public static void setEscenaUno(){
        guiStage.setScene(load);
    }
    public static void setEscenaDos(){
        guiStage.setScene(flight);
    }
    public static void setEscenaTres(){
        guiStage.setScene(sim);
    }

    @Override
    public void start(Stage stage) {
    	x = (int) Screen.getPrimary().getBounds().getWidth();
        y = (int) Screen.getPrimary().getBounds().getHeight();
        load = new LoadScreen(new StackPane(), x, y);
        flight = new FlightMode(new StackPane(), x, y);
        sim = new SimulationMode(new StackPane(), x, y);

        guiStage = stage;
        stage.setTitle("GCS Cuauhtémoc");
        load.getStylesheets().add(getClass().getResource("../CSSsheets/Load.css").toExternalForm());
        flight.getStylesheets().add(getClass().getResource("../CSSsheets/Flight.css").toExternalForm());
        sim.getStylesheets().add(getClass().getResource("../CSSsheets/Simulation.css").toExternalForm());	                                                                                                                     //Author: @Angel Mariscurrena
        stage.setScene(load);//Remember that it must start with "load scene"
        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
		stage.getIcons().add(new Image("Images/Icon.png"));
        stage.show();
    }
}