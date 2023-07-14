package application;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class LoadScreen extends Scene {
	float cb=0;
	Timeline time;
	DecimalFormat df = new DecimalFormat("#.0");
	Rectangle r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,barr;
	BorderPane b1,b2,b4;
	Label lb;
	Buttons buttons = new Buttons();
	Bar bar = new Bar();
	ToggleButton btn1, btn2;
	GridPane grid;
	Divisions div = new Divisions();
	FadeTransition fadelf, fadelr, fadelt, fadelb;
	ImageCreator img = new ImageCreator();
    BorderPane image = new BorderPane();
    AudioClip mode = new AudioClip(getClass().getResource("/sounds/mode.mp3").toExternalForm());
    public LoadScreen(Pane root, int x, int y) {
        super(root, x, y);
        //System.out.println(x + " , " + y);
        System.out.println("Run in progress...");
        b1 = new BorderPane();
        b4 = new BorderPane();
        grid = new GridPane();
        AudioClip clip = new AudioClip(getClass().getResource("/sounds/interstellar.mp3").toExternalForm());
        AudioClip inter = new AudioClip(getClass().getResource("/sounds/Interface.mp3").toExternalForm());
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3500), new EventHandler<ActionEvent>() {  	                                                                                                                     //Author: @Angel Mariscurrena
		    @Override
		    public void handle(ActionEvent event) {
				Main.setEscenaDos();
				clip.stop();
				try {
					img.createImage("Images/Icon.png", image, x/3.5, x/3.5, Color.DEEPSKYBLUE);
				} catch (FileNotFoundException e) {e.printStackTrace(); }
		    }
		}));
        Timeline timelinesim = new Timeline(new KeyFrame(Duration.millis(3500), new EventHandler<ActionEvent>() {  
		    @Override
		    public void handle(ActionEvent event) {
				Main.setEscenaTres();
				clip.stop();
				try {
					img.createImage("Images/Icon.png", image, x/3.5, x/3.5, Color.DEEPSKYBLUE);
				} catch (FileNotFoundException e) {e.printStackTrace(); }
		    }
		}));
        Timeline timeicon = new Timeline(new KeyFrame(Duration.millis(3500), new EventHandler<ActionEvent>() {  	                                                                                                                     //Author: @Angel Mariscurrena
		    @Override
		    public void handle(ActionEvent event) {
				img.start(image,0,1,10);
		    }
		}));
      //Bar
        b2 = new BorderPane();
		lb = new Label("0.0 %");
		barr= new Rectangle();
		r1= new Rectangle();
        r2= new Rectangle();
        r3= new Rectangle();
        r4= new Rectangle();
        r5= new Rectangle();
        r6= new Rectangle();
        r7= new Rectangle();
        r8= new Rectangle();
        r9= new Rectangle();
        r10= new Rectangle();
		try {
			grid.addEventHandler(MouseEvent.MOUSE_CLICKED,
			           new EventHandler<MouseEvent>() {
				           @Override
				           public void handle(MouseEvent e) {
				   				clip.play();
				   				clip.setVolume(1.0);
				             }
			           });
			inter.play();
			inter.setVolume(0.75);
			bar.bar_sqd(grid,1,2,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,barr,lb,x);
			timeicon.play();
			img.createImage("Images/Icon.png", image, x/3.5, x/3.5, Color.DEEPSKYBLUE);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		//********End

		grid.add(image, 1, 1);
		animation(grid,x,y);
        div.div_grid(grid, 3, 3, x, y);
        //Flight Button
    	btn1 = new ToggleButton("FLY");
    	buttons.b_circle(btn1, Color.DEEPSKYBLUE, Color.LIGHTBLUE, "../CSSsheets/tg_1.css");
		btn1.setOnAction(e->{
			if(btn1.isSelected()){
				mode.play();
				cb=0;
				try {
					img.createImage("Images/icon3.png", image, x/3.5, x/3.5, Color.MEDIUMPURPLE);
					img.start(image,0,1,3);
					timeline.play();
					values(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,lb);
				}catch (FileNotFoundException e1){ e1.printStackTrace(); }
			}else{
		    }
		});
		b1.setCenter(btn1);
		//Simulation Button
    	btn2 = new ToggleButton("SIM");
    	buttons.b_circle(btn2, Color.DEEPSKYBLUE, Color.LIGHTBLUE, "../CSSsheets/tg_1.css");
		btn2.setOnAction(e->{
			if(btn2.isSelected()){
				mode.play();
				cb=0;
				try {
					img.createImage("Images/icon3.png", image, x/3.5, x/3.5, Color.STEELBLUE);
					img.start(image,0,1,3);
					timelinesim.play();
					values(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,lb);
				}catch (FileNotFoundException e1){ e1.printStackTrace(); }
			}else{
		    }
		});
		b4.setCenter(btn2);
		
		grid.add(b1,2,2);
		grid.add(b4,0,2);
        root.getChildren().add(grid);
    }
    public void animation (GridPane grid,int width,int height) {
    	Line lf = new Line();
    	Line lr = new Line();
    	Line lt = new Line();
    	Line lb = new Line();
    	BorderPane bf = new BorderPane();
    	DropShadow shadow = new DropShadow();
    	/*
    	fadelf = new FadeTransition(Duration.millis(10*(height-30)));
    	fadelr = new FadeTransition(Duration.millis(10*(height-30)));
    	fadelt = new FadeTransition(Duration.millis(10*(height-30)));
    	fadelb = new FadeTransition(Duration.millis(10*(height-30)));
    	*/
    	fadelf = new FadeTransition(Duration.millis(6000));
    	fadelr = new FadeTransition(Duration.millis(6000));
    	fadelt = new FadeTransition(Duration.millis(6000));
    	fadelb = new FadeTransition(Duration.millis(6000));
    	shadow.setColor(Color.DEEPSKYBLUE);
		shadow.setRadius(150);
		
		////////////////////////////////First option/////////////////////////////
		/*
		try {
			//Left Line
			line (lf, shadow, 30.0f, 30.0f, 30.0f, cbb);
			//Right Line
			line (lr, shadow, width-30, 30.0f, width-30, (height-30)-cbb);
			//Top Line
			line (lt, shadow, 30.0f, 30.0f, cbb, 30.0f);
			//Bottom Line
			line (lb, shadow, width-30, height-30, (width-30)-cbb, height-30);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		t = new Timeline(new KeyFrame(Duration.millis(10),
		        ae -> {
		        	cbb++;
		        	if(cbb<=height-30) {
		        		lf.setEndY(cbb);
		        		lr.setStartY((height-30)-cbb);
		        		lt.setEndX(cbb*width/height);
		        		lb.setStartX((width-30)-cbb*width/height);
		        		try {
							fade(fadelf,lf,0.0f);
							fade(fadelr,lr,0.0f);
							fade(fadelt,lt,0.0f);
							fade(fadelb,lb,0.0f);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
		        	}else{
		        		cbb=0;
		        	}
		        }));
		t.setCycleCount(Timeline.INDEFINITE);
		t.play();*/
		
		
		/////////////////////////////////SECOND OPTION/////////////////////////////////
		try {
			//Left Line
			line (lf, shadow, 30.0f, 30.0f, 30.0f, (height/4));
			//Right Line
			line (lr, shadow, width-30, (height-30), width-30, (height-30)-(height/4));
			//Top Line
			line (lt, shadow, 30.0f, 30.0f, (width/4), 30.0f);
			//Bottom Line
			line (lb, shadow, width-30, height-30, (width-30)-(width/4), height-30);
			
			///////////////////FADE//////////////////////////
			fade(fadelf,lf,0.0f);
			fade(fadelr,lr,0.0f);
			fade(fadelt,lt,0.0f);
			fade(fadelb,lb,0.0f);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		///////////////////////////////////////////////////////////////////////////////////
    	
    	bf.getChildren().addAll(lf,lr,lt,lb);
    	grid.add(bf, 0, 0, 3, 3);
    }
    private void line (Line line, DropShadow shadow, double startx, double starty, double endx, double endy) throws FileNotFoundException{
    	line.setStartX(startx);
    	line.setStartY(starty);
    	line.setEndX(endx);
    	line.setEndY(endy);
    	line.setStrokeWidth(7.5);
    	line.setFill(Color.rgb(255, 255, 255, 0.85));
		line.setStroke(Color.rgb(255, 255, 255, 0.85));
		line.setEffect(shadow);
	}
    private void fade (FadeTransition ft, Line l, double min) throws FileNotFoundException{
		ft.setNode(l);
		ft.setFromValue(1.0);
		ft.setToValue(min);
		ft.setAutoReverse(true);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.play();
	}
    public void values(Rectangle r1,Rectangle r2,Rectangle r3,Rectangle r4,Rectangle r5,Rectangle r6,Rectangle r7,Rectangle r8,Rectangle r9,Rectangle r10, Label lb) throws FileNotFoundException{
		time = new Timeline(new KeyFrame(Duration.millis(3.5),
		        ae -> {
		        	cb++;
		        	if(cb<=1000) {
		        		double num = (double) ((double)cb/10);
			        	lb.setText(String.valueOf(df.format(num))+" %");
			        	if(cb<=100) {
			        		r1.setFill(Color.rgb(0, 255, 0, 0.25));
			        	}else if(cb>100&&cb<=200) {
			        		r1.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r2.setFill(Color.rgb(0, 255, 0, 0.25));
			        	}else if(cb>200&&cb<=300) {
			        		r1.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r2.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r3.setFill(Color.rgb(0, 255, 0, 0.25));
			        	}else if(cb>300&&cb<=400) {
			        		r1.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r2.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r3.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r4.setFill(Color.rgb(0, 255, 0, 0.25));
			        	}else if(cb>400&&cb<=500) {
			        		r1.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r2.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r3.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r4.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r5.setFill(Color.rgb(0, 255, 0, 0.25));
			        	}else if(cb>500&&cb<=600) {
			        		r1.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r2.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r3.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r4.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r5.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r6.setFill(Color.rgb(0, 255, 0, 0.25));
			        	}else if(cb>600&&cb<=700) {
			        		r1.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r2.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r3.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r4.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r5.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r6.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r7.setFill(Color.rgb(0, 255, 0, 0.25));
			        	}else if(cb>700&&cb<=800) {
			        		r1.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r2.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r3.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r4.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r5.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r6.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r7.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r8.setFill(Color.rgb(0, 255, 0, 0.25));
			        	}else if(cb>800&&cb<=900) {
			        		r1.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r2.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r3.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r4.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r5.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r6.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r7.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r8.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r9.setFill(Color.rgb(0, 255, 0, 0.25));
			        	}else if(cb>900&&cb<1000) {
			        		r1.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r2.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r3.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r4.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r5.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r6.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r7.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r8.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r9.setFill(Color.rgb(0, 255, 0, 0.25));
			        		r10.setFill(Color.rgb(0, 255, 0, 0.25));
			        	}else if(cb==1000){
			        		r1.setFill(Color.rgb(0, 0, 51, 0.5));
			        		r2.setFill(Color.rgb(0, 0, 51, 0.5));
			        		r3.setFill(Color.rgb(0, 0, 51, 0.5));
			        		r4.setFill(Color.rgb(0, 0, 51, 0.5));
			        		r5.setFill(Color.rgb(0, 0, 51, 0.5));
			        		r6.setFill(Color.rgb(0, 0, 51, 0.5));
			        		r7.setFill(Color.rgb(0, 0, 51, 0.5));
			        		r8.setFill(Color.rgb(0, 0, 51, 0.5));
			        		r9.setFill(Color.rgb(0, 0, 51, 0.5));
			        		r10.setFill(Color.rgb(0, 0, 51, 0.5));
			        		lb.setText("0.0 %");
			        	}
		        	}else{
		        		time.stop();
		        	}
		        }));
		time.setCycleCount(Timeline.INDEFINITE);
		time.play();
	}
}