package application;

import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Bar extends Thread{
	@Override
	public void run() {
	}
	public void bar_sqd(GridPane grid, int x, int y, Rectangle r1, Rectangle r2, Rectangle r3, Rectangle r4, Rectangle r5, Rectangle r6, Rectangle r7, Rectangle r8, Rectangle r9, Rectangle r10, Rectangle bar, Label lb, int width) throws FileNotFoundException {
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.DEEPSKYBLUE);
		StackPane recc = new StackPane();
        HBox hbox1 = new HBox(1);
		VBox vbox1 = new VBox(5);
		rectangle(r1,width/30.72,width/30.72,width/76.8,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r2,width/30.72,width/30.72,width/76.8,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r3,width/30.72,width/30.72,width/76.8,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r4,width/30.72,width/30.72,width/76.8,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r5,width/30.72,width/30.72,width/76.8,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r6,width/30.72,width/30.72,width/76.8,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r7,width/30.72,width/30.72,width/76.8,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r8,width/30.72,width/30.72,width/76.8,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r9,width/30.72,width/30.72,width/76.8,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r10,width/30.72,width/30.72,width/76.8,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
		bar.setEffect(shadow);
        rectangle(bar,width/2.8981,width/30.72,width/76.8,width/384,Color.TRANSPARENT,Color.rgb(216, 237, 255, 0.85));
        hbox1.getChildren().addAll(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10);
        hbox1.setAlignment(Pos.CENTER);
		recc.setAlignment(Pos.CENTER);
		recc.getChildren().addAll(hbox1,bar);
		vbox1.getChildren().addAll(recc,lb);
		vbox1.setAlignment(Pos.CENTER);
		grid.add(vbox1, x, y);
	}
	public void bar_hbox(GridPane grid, int x, int y, Rectangle r1, Rectangle r2, Rectangle r3, Rectangle r4, Rectangle r5, Rectangle r6, Rectangle r7, Rectangle r8, Rectangle r9, Rectangle r10, Rectangle bar, Label lb, HBox hbox1, HBox hbox2, StackPane recc, Color color, Line line, Color colorline, int width) throws FileNotFoundException {
		DropShadow shadow = new DropShadow();
		HBox linebox = new HBox();
		shadow.setColor(color);
        hbox1.setSpacing(1);
        hbox2.setSpacing(10);
        line.setStartX(0);
		line.setStartY(0);
		line.setEndY(0);
		line.setFill(colorline);
		line.setStroke(colorline);
		line.setStrokeWidth(30);
		line.setEffect(shadow);
		linebox.getChildren().add(line);
		linebox.setAlignment(Pos.CENTER_LEFT);
		rectangle(r1,width/51.2,width/51.2,width/307.2,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r2,width/51.2,width/51.2,width/307.2,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r3,width/51.2,width/51.2,width/307.2,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r4,width/51.2,width/51.2,width/307.2,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r5,width/51.2,width/51.2,width/307.2,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r6,width/51.2,width/51.2,width/307.2,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r7,width/51.2,width/51.2,width/307.2,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r8,width/51.2,width/51.2,width/307.2,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r9,width/51.2,width/51.2,width/307.2,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
        rectangle(r10,width/51.2,width/51.2,width/307.2,width/614.4,Color.rgb(186, 223, 255, 0.05),Color.rgb(186, 223, 255, 0.25));
		bar.setEffect(shadow);
        rectangle(bar,10+325*width/1536,width/51.2,width/307.2,width/384,Color.TRANSPARENT,Color.rgb(216, 237, 255, 0.85));
        hbox1.getChildren().addAll(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10);
        hbox1.setAlignment(Pos.CENTER);
		recc.setAlignment(Pos.CENTER);
		recc.getChildren().addAll(hbox1,bar,linebox);
		hbox2.getChildren().addAll(recc,lb);
		hbox2.setAlignment(Pos.CENTER);
		grid.add(hbox2, x, y);
	}
	private void rectangle (Rectangle r, double width, double height, double arc, double stk, Color fill, Color stroke) throws FileNotFoundException{  	                                                                                                                     //Author: @Angel Mariscurrena
		r.setX(0);
        r.setY(0);
        r.setWidth(width);
        r.setHeight(height);
        r.setArcWidth(arc);
        r.setArcHeight(arc);
        r.setFill(fill);
        r.setStroke(stroke);
        r.setStrokeWidth(stk);
	}
	
}
