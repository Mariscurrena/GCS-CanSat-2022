package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Status extends Thread{
	@Override
	public void run() {
	}
	
	public void linedimensions(Line line, double x, double y) {
	    line.setStartX(0.0f);
	    line.setStartY(5*y/54);
	    line.setEndX(x/25);
	    line.setEndY(5*y/54);
	    line.setStroke(Color.rgb(255, 255, 255, 0.12));
	    line.setStrokeWidth(6);
	}
	public void cirdimensions(Circle cir, double x, double y) {
		cir.setFill(Color.rgb(255, 255, 255, 0.05));
	    cir.setStrokeWidth(4);
	    cir.setStroke(Color.rgb(255, 255, 255, 0.12));
	    cir.setCenterX(0);
	    cir.setCenterY(5*y/54);
	    cir.setRadius(x/140);
	}
	public void text(Label lb) {
		lb.getStylesheets().add(getClass().getResource("../CSSsheets/statusbar.css").toExternalForm());
	}
	public void bar(Line l1, Line l2, Line l3, Line l4, Line l5, Line l6, Circle c1, Circle c2, Circle c3, Circle c4, Circle c5, Circle c6, Circle c7, Label lb1, Label lb2, Label lb3, Label lb4, Label lb5, Label lb6, Label lb7, VBox vbbar, double x, double y) {	                                                                                                                     //Author: @Angel Mariscurrena
		HBox hbox = new HBox(0);
		HBox hbtext = new HBox(x/45);
		vbbar.setSpacing(x/300);
		lb1.setText("LAUNCH_WAIT");
		lb2.setText("ASCENT");
		lb3.setText("ROCKET_SEPARATION");
		lb4.setText("DESCENT");
		lb5.setText("SP_RELEASE");
		lb6.setText("TP_RELEASE");
		lb7.setText("LANDED");
		DropShadow sha = new DropShadow();
		sha.setColor(Color.DEEPSKYBLUE);
		linedimensions(l1,x,y);
		linedimensions(l2,x,y);
		linedimensions(l3,x,y);
		linedimensions(l4,x,y);
		linedimensions(l5,x,y);
		linedimensions(l6,x,y);
		cirdimensions(c1,x,y);
		cirdimensions(c2,x,y);
		cirdimensions(c3,x,y);
		cirdimensions(c4,x,y);
		cirdimensions(c5,x,y);
		cirdimensions(c6,x,y);
		cirdimensions(c7,x,y);
		text(lb1); text(lb2); text(lb3); text(lb4); text(lb5); text(lb6); text(lb7);
		hbox.setAlignment(Pos.CENTER);
	    hbox.setEffect(sha);
	    hbox.getChildren().addAll(c1,l1,c2,l2,c3,l3,c4,l4,c5,l5,c6,l6,c7);
	    hbtext.setAlignment(Pos.CENTER);
	    hbtext.getChildren().addAll(lb1,lb2,lb3,lb4,lb5,lb6,lb7);
	    vbbar.setAlignment(Pos.CENTER);
	    vbbar.getChildren().addAll(hbtext,hbox);
	}
	public void barvalues(Circle c1, Circle c2, Circle c3, Circle c4, Circle c5, Circle c6, Circle c7, Line l1, Line l2, Line l3, Line l4, Line l5, Line l6, Label lb1, Label lb2, Label lb3, Label lb4, Label lb5, Label lb6, Label lb7, String cb) {
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.DEEPSKYBLUE);
		shadow.setRadius(30);
		DropShadow transparent = new DropShadow();
		transparent.setColor(Color.TRANSPARENT);
		if(cb.equals("")) {
			lb1.setEffect(transparent);
			c1.setFill(Color.rgb(55, 155, 255, 0.05));		c1.setEffect(transparent);
            c1.setStroke(Color.rgb(255, 255, 255, 0.12));	
            l1.setStroke(Color.rgb(255, 255, 255, 0.12));	l1.setEffect(transparent);
            lb2.setEffect(transparent);
            c2.setFill(Color.rgb(55, 155, 255, 0.05));		c2.setEffect(transparent);
            c2.setStroke(Color.rgb(255, 255, 255, 0.12));	l2.setEffect(transparent);
            l2.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb3.setEffect(transparent);
            c3.setFill(Color.rgb(255, 255, 255, 0.05));		c3.setEffect(transparent);
            c3.setStroke(Color.rgb(255, 255, 255, 0.12));	l3.setEffect(transparent);
            l3.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb4.setEffect(transparent);
            c4.setFill(Color.rgb(55, 155, 255, 0.05));		c4.setEffect(transparent);
            c4.setStroke(Color.rgb(255, 255, 255, 0.12));	l4.setEffect(transparent);
            l4.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb5.setEffect(transparent);
            c5.setFill(Color.rgb(55, 155, 255, 0.05));		c5.setEffect(transparent);
            c5.setStroke(Color.rgb(255, 255, 255, 0.12));	l5.setEffect(transparent);
            l5.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb6.setEffect(transparent);
            c6.setFill(Color.rgb(55, 155, 255, 0.05));		c6.setEffect(transparent);
            c6.setStroke(Color.rgb(255, 255, 255, 0.12));	l6.setEffect(transparent);
            l6.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb7.setEffect(transparent);
            c7.setFill(Color.rgb(55, 155, 255, 0.05));		c7.setEffect(transparent);
            c7.setStroke(Color.rgb(255, 255, 255, 0.12));	
		}else if(cb.equals("LAUNCH_WAIT")) {
			lb1.setEffect(shadow);
			c1.setFill(Color.rgb(55, 155, 255, 0.5));		c1.setEffect(shadow);
            c1.setStroke(Color.rgb(255, 255, 255, 1));	
            l1.setStroke(Color.rgb(255, 255, 255, 0.12));	l1.setEffect(transparent);
            lb2.setEffect(transparent);
            c2.setFill(Color.rgb(55, 155, 255, 0.05));		c2.setEffect(transparent);
            c2.setStroke(Color.rgb(255, 255, 255, 0.12));	l2.setEffect(transparent);
            l2.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb3.setEffect(transparent);
            c3.setFill(Color.rgb(255, 255, 255, 0.05));		c3.setEffect(transparent);
            c3.setStroke(Color.rgb(255, 255, 255, 0.12));	l3.setEffect(transparent);
            l3.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb4.setEffect(transparent);
            c4.setFill(Color.rgb(55, 155, 255, 0.05));		c4.setEffect(transparent);
            c4.setStroke(Color.rgb(255, 255, 255, 0.12));	l4.setEffect(transparent);
            l4.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb5.setEffect(transparent);
            c5.setFill(Color.rgb(55, 155, 255, 0.05));		c5.setEffect(transparent);
            c5.setStroke(Color.rgb(255, 255, 255, 0.12));	l5.setEffect(transparent);
            l5.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb6.setEffect(transparent);
            c6.setFill(Color.rgb(55, 155, 255, 0.05));		c6.setEffect(transparent);
            c6.setStroke(Color.rgb(255, 255, 255, 0.12));	l6.setEffect(transparent);
            l6.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb7.setEffect(transparent);
            c7.setFill(Color.rgb(55, 155, 255, 0.05));		c7.setEffect(transparent);
            c7.setStroke(Color.rgb(255, 255, 255, 0.12));	
		}else if(cb.equals("ASCENT")) {
			lb1.setEffect(shadow);
			c1.setFill(Color.rgb(55, 155, 255, 0.5));		c1.setEffect(shadow);
            c1.setStroke(Color.rgb(255, 255, 255, 1));		l1.setEffect(shadow);
            l1.setStroke(Color.rgb(255, 255, 255, 1));
            lb2.setEffect(shadow);
            c2.setFill(Color.rgb(55, 155, 255, 0.5));		c2.setEffect(shadow);
            c2.setStroke(Color.rgb(255, 255, 255, 1));
            l2.setStroke(Color.rgb(255, 255, 255, 0.12));	l2.setEffect(transparent);
            lb3.setEffect(transparent);
            c3.setFill(Color.rgb(255, 255, 255, 0.05));		c3.setEffect(transparent);
            c3.setStroke(Color.rgb(255, 255, 255, 0.12));	l3.setEffect(transparent);
            l3.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb4.setEffect(transparent);
            c4.setFill(Color.rgb(55, 155, 255, 0.05));		c4.setEffect(transparent);
            c4.setStroke(Color.rgb(255, 255, 255, 0.12));	l4.setEffect(transparent);
            l4.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb5.setEffect(transparent);
            c5.setFill(Color.rgb(55, 155, 255, 0.05));		c5.setEffect(transparent);
            c5.setStroke(Color.rgb(255, 255, 255, 0.12));	l5.setEffect(transparent);
            l5.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb6.setEffect(transparent);
            c6.setFill(Color.rgb(55, 155, 255, 0.05));		c6.setEffect(transparent);
            c6.setStroke(Color.rgb(255, 255, 255, 0.12));	l6.setEffect(transparent);
            l6.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb7.setEffect(transparent);
            c7.setFill(Color.rgb(55, 155, 255, 0.05));		c7.setEffect(transparent);
            c7.setStroke(Color.rgb(255, 255, 255, 0.12));	
		}else if(cb.equals("ROCKET_SEPARATION")) {
			lb1.setEffect(shadow);
			c1.setFill(Color.rgb(55, 155, 255, 0.5));		c1.setEffect(shadow);
            c1.setStroke(Color.rgb(255, 255, 255, 1));		l1.setEffect(shadow);
            l1.setStroke(Color.rgb(255, 255, 255, 1));
            lb2.setEffect(shadow);
            c2.setFill(Color.rgb(55, 155, 255, 0.5));		c2.setEffect(shadow);
            c2.setStroke(Color.rgb(255, 255, 255, 1));		l2.setEffect(shadow);
            l2.setStroke(Color.rgb(255, 255, 255, 1));
            lb3.setEffect(shadow);
            c3.setFill(Color.rgb(55, 155, 255, 0.5));		c3.setEffect(shadow);
            c3.setStroke(Color.rgb(255, 255, 255, 1));		l3.setEffect(transparent);
            l3.setStroke(Color.rgb(255, 255, 255, 0.12));	
            lb4.setEffect(transparent);
            c4.setFill(Color.rgb(55, 155, 255, 0.05));		c4.setEffect(transparent);
            c4.setStroke(Color.rgb(255, 255, 255, 0.12));	l4.setEffect(transparent);
            l4.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb5.setEffect(transparent);
            c5.setFill(Color.rgb(55, 155, 255, 0.05));		c5.setEffect(transparent);
            c5.setStroke(Color.rgb(255, 255, 255, 0.12));	l5.setEffect(transparent);
            l5.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb6.setEffect(transparent);
            c6.setFill(Color.rgb(55, 155, 255, 0.05));		c6.setEffect(transparent);
            c6.setStroke(Color.rgb(255, 255, 255, 0.12));	l6.setEffect(transparent);
            l6.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb7.setEffect(transparent);
            c7.setFill(Color.rgb(55, 155, 255, 0.05));		c7.setEffect(transparent);
            c7.setStroke(Color.rgb(255, 255, 255, 0.12));	
		}else if(cb.equals("DESCENT")) {
			lb1.setEffect(shadow);
			c1.setFill(Color.rgb(55, 155, 255, 0.5));		c1.setEffect(shadow);
            c1.setStroke(Color.rgb(255, 255, 255, 1));		l1.setEffect(shadow);
            l1.setStroke(Color.rgb(255, 255, 255, 1));
            lb2.setEffect(shadow);
            c2.setFill(Color.rgb(55, 155, 255, 0.5));		c2.setEffect(shadow);
            c2.setStroke(Color.rgb(255, 255, 255, 1));		l2.setEffect(shadow);
            l2.setStroke(Color.rgb(255, 255, 255, 1));
            lb3.setEffect(shadow);
            c3.setFill(Color.rgb(55, 155, 255, 0.5));		c3.setEffect(shadow);
            c3.setStroke(Color.rgb(255, 255, 255, 1));		l3.setEffect(shadow);
            l3.setStroke(Color.rgb(255, 255, 255, 1));	
            lb4.setEffect(shadow);
            c4.setFill(Color.rgb(55, 155, 255, 0.5));		c4.setEffect(shadow);
            c4.setStroke(Color.rgb(255, 255, 255, 1));		l4.setEffect(transparent);
            l4.setStroke(Color.rgb(255, 255, 255, 0.12));
            lb5.setEffect(transparent);
            c5.setFill(Color.rgb(55, 155, 255, 0.05));		c5.setEffect(transparent);
            c5.setStroke(Color.rgb(255, 255, 255, 0.12));	l5.setEffect(transparent);
            l5.setStroke(Color.rgb(255, 255, 255, 0.12));	
            lb6.setEffect(transparent);
            c6.setFill(Color.rgb(55, 155, 255, 0.05));		c6.setEffect(transparent);
            c6.setStroke(Color.rgb(255, 255, 255, 0.12));	l6.setEffect(transparent);
            l6.setStroke(Color.rgb(255, 255, 255, 0.12));	
            lb7.setEffect(transparent);
            c7.setFill(Color.rgb(55, 155, 255, 0.05));		c7.setEffect(transparent);
            c7.setStroke(Color.rgb(255, 255, 255, 0.12));	
		}else if(cb.equals("SP_RELEASED")) {
			lb1.setEffect(shadow);
			c1.setFill(Color.rgb(55, 155, 255, 0.5));		c1.setEffect(shadow);
            c1.setStroke(Color.rgb(255, 255, 255, 1));		l1.setEffect(shadow);
            l1.setStroke(Color.rgb(255, 255, 255, 1));
            lb2.setEffect(shadow);
            c2.setFill(Color.rgb(55, 155, 255, 0.5));		c2.setEffect(shadow);
            c2.setStroke(Color.rgb(255, 255, 255, 1));		l2.setEffect(shadow);
            l2.setStroke(Color.rgb(255, 255, 255, 1));
            lb3.setEffect(shadow);
            c3.setFill(Color.rgb(55, 155, 255, 0.5));		c3.setEffect(shadow);
            c3.setStroke(Color.rgb(255, 255, 255, 1));		l3.setEffect(shadow);
            l3.setStroke(Color.rgb(255, 255, 255, 1));
            lb4.setEffect(shadow);
            c4.setFill(Color.rgb(55, 155, 255, 0.5));		c4.setEffect(shadow);
            c4.setStroke(Color.rgb(255, 255, 255, 1));		l4.setEffect(shadow);
            l4.setStroke(Color.rgb(255, 255, 255, 1));	
            lb5.setEffect(shadow);
            c5.setFill(Color.rgb(55, 155, 255, 0.5));		c5.setEffect(shadow);
            c5.setStroke(Color.rgb(255, 255, 255, 1));		l5.setEffect(transparent);
            l5.setStroke(Color.rgb(255, 255, 255, 0.12));	
            lb6.setEffect(transparent);
            c6.setFill(Color.rgb(55, 155, 255, 0.05));		c6.setEffect(transparent);
            c6.setStroke(Color.rgb(255, 255, 255, 0.12));	l6.setEffect(transparent);
            l6.setStroke(Color.rgb(255, 255, 255, 0.12));	
            lb7.setEffect(transparent);
            c7.setFill(Color.rgb(55, 155, 255, 0.05));		c7.setEffect(transparent);
            c7.setStroke(Color.rgb(255, 255, 255, 0.12));	
		}else if(cb.equals("TP_RELEASED")) {
			lb1.setEffect(shadow);
			c1.setFill(Color.rgb(55, 155, 255, 0.5));		c1.setEffect(shadow);
            c1.setStroke(Color.rgb(255, 255, 255, 1));		l1.setEffect(shadow);
            l1.setStroke(Color.rgb(255, 255, 255, 1));
            lb2.setEffect(shadow);
            c2.setFill(Color.rgb(55, 155, 255, 0.5));		c2.setEffect(shadow);
            c2.setStroke(Color.rgb(255, 255, 255, 1));		l2.setEffect(shadow);
            l2.setStroke(Color.rgb(255, 255, 255, 1));
            lb3.setEffect(shadow);
            c3.setFill(Color.rgb(55, 155, 255, 0.5));		c3.setEffect(shadow);
            c3.setStroke(Color.rgb(255, 255, 255, 1));		l3.setEffect(shadow);
            l3.setStroke(Color.rgb(255, 255, 255, 1));
            lb4.setEffect(shadow);
            c4.setFill(Color.rgb(55, 155, 255, 0.5));		c4.setEffect(shadow);
            c4.setStroke(Color.rgb(255, 255, 255, 1));		l4.setEffect(shadow);
            l4.setStroke(Color.rgb(255, 255, 255, 1));	
            lb5.setEffect(shadow);
            c5.setFill(Color.rgb(55, 155, 255, 0.5));		c5.setEffect(shadow);
            c5.setStroke(Color.rgb(255, 255, 255, 1));		l5.setEffect(shadow);
            l5.setStroke(Color.rgb(255, 255, 255, 1));	
            lb6.setEffect(shadow);
            c6.setFill(Color.rgb(55, 155, 255, 0.5));		c6.setEffect(shadow);
            c6.setStroke(Color.rgb(255, 255, 255, 1));		l6.setEffect(transparent);
            l6.setStroke(Color.rgb(255, 255, 255, 0.12));	
            lb7.setEffect(transparent);
            c7.setFill(Color.rgb(55, 155, 255, 0.05));		c7.setEffect(transparent);
            c7.setStroke(Color.rgb(255, 255, 255, 0.12));	
		}else if(cb.equals("LANDED")) {
			lb1.setEffect(shadow);
			c1.setFill(Color.rgb(55, 155, 255, 0.5));		c1.setEffect(shadow);
            c1.setStroke(Color.rgb(255, 255, 255, 1));		l1.setEffect(shadow);
            l1.setStroke(Color.rgb(255, 255, 255, 1));
            lb2.setEffect(shadow);
            c2.setFill(Color.rgb(55, 155, 255, 0.5));		c2.setEffect(shadow);
            c2.setStroke(Color.rgb(255, 255, 255, 1));		l2.setEffect(shadow);
            l2.setStroke(Color.rgb(255, 255, 255, 1));
            lb3.setEffect(shadow);
            c3.setFill(Color.rgb(55, 155, 255, 0.5));		c3.setEffect(shadow);
            c3.setStroke(Color.rgb(255, 255, 255, 1));		l3.setEffect(shadow);
            l3.setStroke(Color.rgb(255, 255, 255, 1));
            lb4.setEffect(shadow);
            c4.setFill(Color.rgb(55, 155, 255, 0.5));		c4.setEffect(shadow);
            c4.setStroke(Color.rgb(255, 255, 255, 1));		l4.setEffect(shadow);
            l4.setStroke(Color.rgb(255, 255, 255, 1));	
            lb5.setEffect(shadow);
            c5.setFill(Color.rgb(55, 155, 255, 0.5));		c5.setEffect(shadow);
            c5.setStroke(Color.rgb(255, 255, 255, 1));		l5.setEffect(shadow);
            l5.setStroke(Color.rgb(255, 255, 255, 1));	
            lb6.setEffect(shadow);
            c6.setFill(Color.rgb(55, 155, 255, 0.5));		c6.setEffect(shadow);
            c6.setStroke(Color.rgb(255, 255, 255, 1));		l6.setEffect(shadow);
            l6.setStroke(Color.rgb(255, 255, 255, 1));	
            lb7.setEffect(shadow);
            c7.setFill(Color.rgb(55, 155, 255, 0.5));		c7.setEffect(shadow);
            c7.setStroke(Color.rgb(255, 255, 255, 1));	
		}
	}
	public void status(Text status, String cb) {
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.DEEPSKYBLUE);
		shadow.setRadius(30);
		DropShadow transparent = new DropShadow();
		transparent.setColor(Color.TRANSPARENT);
		if(cb.equals("")) {
			status.setEffect(transparent);
			status.setText(" ");
		}else if(cb.equals("LAUNCH_WAIT")) {
			status.setEffect(shadow);
			status.setText("LAUNCH_WAIT");
		}else if(cb.equals("ASCENT")) {
			status.setEffect(shadow);
			status.setText("ASCENT");
		}else if(cb.equals("ROCKET_SEPARATION")) {
			status.setEffect(shadow);
			status.setText("ROCKET_SEPARATION");
		}else if(cb.equals("DESCENT")) {
			status.setEffect(shadow);
			status.setText("DESCENT");
		}else if(cb.equals("SP_RELEASED")) {
			status.setEffect(shadow);
			status.setText("SP_RELEASED");
		}else if(cb.equals("TP_RELEASED")) {
			status.setEffect(shadow);
			status.setText("TP_RELEASED");
		}else if(cb.equals("LANDED")) {
			status.setEffect(shadow);
			status.setText("LANDED");
		}
	}
}
