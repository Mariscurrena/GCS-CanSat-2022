package application;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Screen;

public class Radar extends Thread{
	@Override
	public void run() {
	}
	float cb=0;
    float angle=90;
    int width = (int) Screen.getPrimary().getBounds().getWidth();
    int radius=(width/12)/2;
    Line line3;
	public void design(int x, int y, BorderPane root, Color cmain, Color clate) {
		DropShadow drop = new DropShadow();
		drop.setColor(cmain);
		DropShadow drop1 = new DropShadow();
		drop1.setColor(clate);
		Circle circle = new Circle();
		Circle circle1 = new Circle();
		Circle circle2 = new Circle();
		Circle circle3 = new Circle();
		Line line = new Line();
		Line line1 = new Line();
		Line line2 = new Line();
		line3 = new Line();
		double x0 = radius+((radius/Math.sin(Math.toRadians(90)))*(Math.sin(Math.toRadians(90-angle))));
		double y0 = radius-(radius/Math.sin(Math.toRadians(90)))*(Math.sin(Math.toRadians(angle)));
		
		circledesign(circle,x,y,radius,3,7.5d,Color.rgb(255,255,255,0.75),Color.rgb(255,255,255,0.025),drop);
		circledesign(circle1,x,y,2*radius/3,1,0.0d,Color.rgb(255,255,255,0.75),Color.rgb(255,255,255,0.025),drop);	                                                                                                                     //Author: @Angel Mariscurrena
		circledesign(circle2,x,y,radius/3,1,0.0d,Color.rgb(255,255,255,0.75),Color.rgb(255,255,255,0.025),drop);
        circledesign(circle3,x,y,radius/12,1,0.0d,Color.rgb(255,255,255,0.75),Color.rgb(255,255,255,0.025),drop);
        
		linedesign(line, x/2, (y/2)-radius, x/2, y/2, 3, Color.rgb(255,110,110,0.7), drop1);
		linedesign(line1, 0.0f, y/2, x, y/2, 1, Color.rgb(255,255,255,0.75), drop);
		linedesign(line2, x/2, 0.0f, x/2, y, 1, Color.rgb(255,255,255,0.75), drop);
		linedesign(line3, x0, y0, x/2, y/2, 4, Color.rgb(255,255,255,0.0), drop);

        root.getChildren().addAll(circle,circle1,circle2,circle3,line1,line2,line3,line);
	}
	public void values(double angle) {
		line3.setStroke(Color.rgb(255,255,255,1.0));
		line3.setStartX(radius+((radius/Math.sin(Math.toRadians(90)))*(Math.sin(Math.toRadians(90-angle)))));
		line3.setStartY(radius-(radius/Math.sin(Math.toRadians(90)))*(Math.sin(Math.toRadians(angle))));
		//System.out.println("X: "+(radius+(radius/Math.sin(Math.toRadians(90)))*(Math.sin(Math.toRadians(90-angle)))));
		//System.out.println("Y: "+(radius-(radius/Math.sin(Math.toRadians(90)))*(Math.sin(Math.toRadians(angle)))));
	}
	private void linedesign(Line line, double x0, double y0, double x1, double y1, int stroke, Color color, DropShadow shadow) {
		line.setStartX(x0);
		line.setStartY(y0);
		line.setEndX(x1);
		line.setEndY(y1);
		line.setStrokeWidth(stroke);
		line.setStroke(color);
		line.setEffect(shadow);
	}
	private void circledesign(Circle circle, int x, int y, int radius, int stroke, double btw, Color colorstroke, Color colorfill, DropShadow shadow) {
		circle.setCenterX(x/2);
        circle.setCenterY(y/2);
        circle.setRadius(radius);
        circle.getStrokeDashArray().addAll(7.5d, btw);
        circle.setStroke(colorstroke);
        circle.setStrokeWidth(stroke);
		circle.setFill(colorfill);
		circle.setEffect(shadow);
	}

}
