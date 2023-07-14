package application;

import java.io.FileNotFoundException;

import javafx.animation.FadeTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ImageCreator extends Thread{
	@Override
	public void run() {
	}
	FadeTransition fade;
	public void createImage(String file, BorderPane border, double x, double y, Color color) throws FileNotFoundException {	                                                                                                                     //Author: @Angel Mariscurrena
	    fade = new FadeTransition();
    	DropShadow shadow = new DropShadow();
	    ImageView imageView = new ImageView(file);
	    shadow.setColor(color);
		shadow.setRadius(45);
	    imageView.setX(0);
	    imageView.setY(0);
	    imageView.setFitHeight(y);
	    imageView.setFitWidth(x);
	    imageView.setPreserveRatio(true);
	    imageView.setEffect(shadow);
	    border.setCenter(imageView);
	}
	public void start(BorderPane border, int min, int max, int dur) {
		fade.setDuration(Duration.seconds(dur));
		fade.setNode(border);
		fade.setFromValue(min);
		fade.setToValue(max);
		//fade.setAutoReverse(true);
		//fade.setCycleCount(Timeline.INDEFINITE);
		fade.play();
	}
	public void stop(BorderPane border) {
		fade.setNode(border);
		fade.setFromValue(0);
		fade.setToValue(1.0);
		fade.setCycleCount(0);
		fade.stop();
	}
}
