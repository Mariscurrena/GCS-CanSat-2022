package application;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.chart.AreaChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class Nodes extends Thread{
	@Override
	public void run() {
	}
	public void sizing(BorderPane border, GridPane grid, AreaChart<Number,Number> area, int width, int height, int x, int y) {	                                                                                                                     //Author: @Angel Mariscurrena

		border.setCenter(area);
		grid.add(border, x, y);
		
	    ScaleTransition st = new ScaleTransition(); 
		st.setDuration(Duration.millis(2000));
		st.setNode(area); 
		area.addEventHandler(MouseEvent.MOUSE_CLICKED,
	           new EventHandler<MouseEvent>() {
		           @Override
		           public void handle(MouseEvent e) {
		               st.setByX(0.8f);
		          	   st.setByY(0.8f);
		          	   st.play();
		             }
	           });
		area.addEventHandler(MouseEvent.MOUSE_DRAGGED,
		           new EventHandler<MouseEvent>() {
			           @Override
			           public void handle(MouseEvent e) {
			               st.setByX(-0.8f);
			          	   st.setByY(-0.8f);
			          	   st.play();
			             }
		           });
	}
}
