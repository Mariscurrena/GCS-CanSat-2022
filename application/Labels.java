package application;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Labels extends Thread{
	@Override
	public void run() {
	}
	public void textformat(Text text,double size,int width) {
		text.setFont(new Font("Lucida Console",size));
        text.setFill(Color.rgb(189, 238, 255));
        text.setWrappingWidth(width);
        text.setTextAlignment(TextAlignment.CENTER);
    }
    public void rect_text(Rectangle rec, int x, int y, Color cfill, Color cstroke) {	                                                                                                                     //Author: @Angel Mariscurrena
    	rec.setX(0);
    	rec.setY(0);
    	rec.setWidth((x/3)-48);
    	rec.setHeight((3*y/18)-2);
    	rec.setArcWidth(7);
    	rec.setArcHeight(7);
    	rec.setFill(cfill);
    	rec.setStroke(cstroke);
    	rec.setStrokeWidth(3);
    }
	public void fade_shadow(Color color, Label lb, int radius) {
		FadeTransition ft = new FadeTransition(Duration.seconds(1));
		DropShadow shadow = new DropShadow();
		shadow.setColor(color);
		shadow.setRadius(radius);
        lb.setEffect(shadow);
        ft.setNode(lb);
		ft.setFromValue(1.0);
		ft.setToValue(0.075);
		ft.setAutoReverse(true);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.play();
	}
	public void textbox(TextField text, int x, int y, Color color) {
		DropShadow shadow = new DropShadow();
		shadow.setColor(color);
		shadow.setRadius(30);
		textsize(text,(2*x/9)-42,(5*y/81)-8);
        text.setEffect(shadow);
    }
	private void textsize(TextField text, int width, int height) {
		text.setAlignment(Pos.CENTER);
		text.setMaxWidth(width);
        text.setMinWidth(width);
        text.setMaxHeight(height);
        text.setMinHeight(height);
    }
}
