package application;

import java.text.DecimalFormat;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;

public class CircleCreator extends Thread{
	@Override
	public void run() {
	}
	DecimalFormat df = new DecimalFormat("#0.0");
	public void arcs(Arc arc, double radius, double stroke, Color colors, float length,float begin) {
		arc.setFill(Color.rgb(255,255,255,0.0));
        arc.setStrokeWidth(stroke);
        arc.setStroke(colors);
        arc.setCenterX(0);
        arc.setCenterY(0);
        arc.setRadiusX(radius);
        arc.setRadiusY(radius);
        arc.setStartAngle(begin);
        arc.setLength(length);
	}
	public void arcsg(Arc arc, double radius, double stroke, LinearGradient colors, float length,float begin) {	                                                                                                                     //Author: @Angel Mariscurrena
		arc.setFill(Color.rgb(255,255,255,0.0));
        arc.setStrokeWidth(stroke);
        arc.setStroke(colors);
        arc.setCenterX(0);
        arc.setCenterY(0);
        arc.setRadiusX(radius);
        arc.setRadiusY(radius);
        arc.setStartAngle(begin);
        arc.setLength(length);
	}
	public void temperature(Arc a, double cb, Label lb) {
    	if(cb>=-10&&cb<=80) {
    		double real=4*(cb+10);
    		lb.setText(String.valueOf(df.format(cb)) + " °C");
        	a.setLength(-1*real);
    	}//else{
//    		a.setLength(-1*4*90);
//    		lb.setText("Unvalid");
//    	}
	}
	public void pressure(Arc a, double cb, Label lb) {
    	if(cb>=265&&cb<=500) {
    		double real=1.53*(cb-265);
    		lb.setText(String.valueOf(df.format(cb)) + " MPa");
        	a.setLength(-1*real);
    	}else if(cb<0) {
    		double real=0*(cb);
    		lb.setText(String.valueOf(df.format(0)) + " MPa");
        	a.setLength(-1*real);
    	}//else{
//    		a.setLength(-1*1.53*(500-265));
//    		lb.setText("Unvalid");
//    	}
	}
	public void altitude(Arc a, double cb, Label lb) {
    	if(cb>=0&&cb<=1700) {
    		double real=(0.2117)*(cb);
    		lb.setText(String.valueOf(df.format(cb)) + " m");
        	a.setLength(-1*real);
    	}else if(cb<0) {
    		double real=0*(cb);
    		lb.setText(String.valueOf(df.format(0)) + " m");
        	a.setLength(-1*real);
   	}//else{
//    		a.setLength(-1*(0.2117)*(1700));
//    		lb.setText("Unvalid");
//    	}
	}
	public void accel(Arc a, double cb, Label lb) {
    	if(cb>=0&&cb<=57000) {
    		double real=(0.00631579)*(cb);
    		lb.setText(String.valueOf(df.format(cb)));
        	a.setLength(-1*real);
        	System.out.println(real);
    	}else{
    		a.setLength(-1*(0.00631579)*57000);
    	}
	}
	public void temper_tp(Arc a, double cb, Label lb) {
    	if(cb>=-10&&cb<=80) {
    		double real=3.333333*(cb+10);
    		lb.setText(String.valueOf(df.format(cb)) + " °C");
        	a.setLength(-1*real);
    	}//else{
//    		a.setLength(3.333333*(90));
//    		lb.setText("Unvalid");
//    	}
	}
	public void position (GridPane grid, BorderPane border, HBox hbox, VBox vbox,Arc a1, Arc a2, int x, int y){
		border.getChildren().addAll(a1,a2);
        hbox.getChildren().addAll(border);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hbox);
        vbox.setAlignment(Pos.CENTER);
        grid.add(vbox, x, y);
	}
	public void positiontext (GridPane grid, BorderPane border, HBox hbox, VBox vbox,Arc a1, Arc a2, int x, int y, Text text, Color color){
		VBox vb = new VBox(65);
		BorderPane bp = new BorderPane();
		BorderPane bpgen = new BorderPane();
		DropShadow shadow = new DropShadow();
		shadow.setColor(color);
		border.getChildren().addAll(a1,a2);
        hbox.getChildren().addAll(border);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(hbox);
        vbox.setAlignment(Pos.CENTER);
        bp.setCenter(text);
        bp.setEffect(shadow);
        vb.getChildren().addAll(bp,vbox);
        bpgen.setCenter(vb);
        grid.add(bpgen, x, y);
	}
}
