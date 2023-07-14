package application;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Indicators extends Thread{
	@Override
	public void run() {
	}
	public void design(Text text, Color color, Circle circle, BorderPane border, int y) {	                                                                                                                     //Author: @Angel Mariscurrena
		DropShadow shadow = new DropShadow();
		HBox hbox = new HBox(0);
		BorderPane root1 = new BorderPane();
		BorderPane root2 = new BorderPane();
		shadow.setColor(color);
		text.setEffect(shadow);
		root1.setCenter(text);
		circle.setFill(Color.rgb(255,255,255,0.0));
        circle.setStrokeWidth(1.5f);
        circle.setStroke(Color.rgb(255,255,255,0.7));
        circle.setCenterX(0);
        circle.setCenterY(0);
        circle.setRadius(y/6);
        circle.setEffect(shadow);
        root2.setCenter(circle);
        hbox.getChildren().addAll(root2,root1);
        border.setCenter(hbox);
	}
	public void mode(Circle circle, String cb, String mode) {
		if(cb.equals(mode)) {
			circle.setFill(Color.rgb(0,255,0,0.150));
		}else{
			circle.setFill(Color.rgb(255,0,0,0.675));
		}
	}
	public void standby(Circle circle, String cb) {
		if(cb.equals("LAUNCH_WAIT")) {
			circle.setFill(Color.rgb(0,255,0,0.150));
		}else if(cb.equals("ASCENT")) {
			circle.setFill(Color.rgb(0,255,0,0.150));
		}else if(cb.equals("ROCKET_SEPARATION")) {
			circle.setFill(Color.rgb(0,255,0,0.150));
		}else if(cb.equals("DESCENT")) {
			circle.setFill(Color.rgb(0,255,0,0.150));
		}else{
			circle.setFill(Color.rgb(255,0,0,0.675));
		}
	}
}
