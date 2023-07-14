package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SimulationMode extends Scene {
	
	static MqttAsyncClient cliente; 
	static String topic = "teams/1017";
	static int Usuario,Contraseña;
	int width = (int) Screen.getPrimary().getBounds().getWidth();
	static SerialPort Puerto;
	GridPane grid, commands, master_button, ports, cont_values, payl_values, g_bars, actions, g_barstatus,g_barmission, g_missionstatus, g_graphs, g_divgraphs, g_contgraphs, g_paylgraphs, g_cgraphs, g_pgraphs, g_ts;	                                                                                                                     //Author: @Angel Mariscurrena
	GridPane imageprueb, g_img, g_indicators, g_contcirc, g_paycirc, g_btndiv;
	Indicators indicators = new Indicators();
	Circle ci1,ci2,ci3,ci4;
	Divisions div = new Divisions();
	Buttons buttons = new Buttons();
	Labels labels = new Labels();
	Status status = new Status();
	Chart chart = new Chart();
	ImageCreator img = new ImageCreator();
	CircleCreator circreator = new CircleCreator();
	Button btn, refresh, calibration, buzzer;
	AudioClip laser = new AudioClip(getClass().getResource("/sounds/Laser.mp3").toExternalForm());
	AudioClip cbxsound = new AudioClip(getClass().getResource("/sounds/combo.mp3").toExternalForm());
	ToggleButton btn_comm,btn_csv;
	ComboBox <String> combo = new ComboBox <String> ();
	static ComboBox<String> cbx = new ComboBox<String>();
	public static long t0;
	String line="0";
	String Line1="0";
	String Line2="0";
    CSV_CONTAINER csv_container;
    CSV_PAYLOAD csv_payload;
    TextField comm_insert;
    BorderPane image;
    //Payload Values
    static Text prueb = new Text("0.0");
  //Container text values
    static Text pressure = new Text("0.000 MPa");
    static Text voltage = new Text("0.0 V");
    static Text latitude = new Text("0.0000");
    static Text longitude = new Text("0.0000");
    static Text SATS_C = new Text("0.0 m");
    static Text temp_c = new Text("0.0 °C");
    //Payload text values
    static Text pressurep = new Text("0.00 MPa");
    static Text temp_tp = new Text("0.0 °C");
    static Text rot = new Text("0.00i, 0.00j, 0.00k");
    static Text accel_v = new Text("0.00i, 0.00j, 0.00k");
    static Text mag_v = new Text("0.00i, 0.00j, 0.00k");
    //COMMANDS
    static String loads = "LOAD SCREEN";
    static String exit = "EXIT";
    static String simmode = "CMD,1017,SIM,ACTIVATE";
    static String com_buzzer = "CMD,1017,FLY,BUZZER";
	static String com_releasedp = "CMD,1017,RELP,RELEASE";
	static String com_parachute = "CMD,1017,PARCH,RELEASE";
	static String cmdcxoff = "CMD,1017,CX,OFF";
	static String cmdcxon = "CMD,1017,CX,ON";
	static String payload_telemetry = "CMD,1017,TP,ON";
	static String payload_telemetry_off = "CMD,1017,TP,OFF";
    String mode;
    ObservableList<String> listData = FXCollections.observableArrayList();
    //Bar
    DecimalFormat df = new DecimalFormat("#0.00");
	Bar bar = new Bar();
	HBox hboxc1,hboxc2,hboxp1,hboxp2;
	StackPane recC,recP;
    //Bar battery container
    Label lbb_c;
    Rectangle bar_c,r1c,r2c,r3c,r4c,r5c,r6c,r7c,r8c,r9c,r10c;
    float bat_c=0;
    Line cont_line;
    //Bar battery container
    Label lbb_p;
    Rectangle bar_p,r1p,r2p,r3p,r4p,r5p,r6p,r7p,r8p,r9p,r10p;
    float bat_p=0;
    Line pay_line;
    //Bar Status
    Line l1,l2,l3,l4,l5,l6;
    Circle c1,c2,c3,c4,c5,c6,c7;
    Label lb1,lb2,lb3,lb4,lb5,lb6,lb7;
    static Text missionsta = new Text(" ");
    //Chart series
    XYChart.Series <Number,Number> series1;
    XYChart.Series <Number,Number> series2;
    XYChart.Series <Number,Number> series3;
    XYChart.Series <Number,Number> series4;
    XYChart.Series <Number,Number> series5;
    XYChart.Series <Number,Number> series6;
    XYChart.Series <Number,Number> series7;
    XYChart.Series <Number,Number> series8;
    XYChart.Series <Number,Number> series9;
    XYChart.Series <Number,Number> series10;
    XYChart.Series <Number,Number> series11;
    XYChart.Series <Number,Number> series12;
    XYChart.Series <Number,Number> series81;
    XYChart.Series <Number,Number> series82;
    //Toggle Switch
    GridPane g_divts, g_posts;
    //Cicle Indicators
    Label lbc1,lbc2,lbc3,lbc4,lbc5,lbc6,lbc7,lbc8,lbc9;
    Arc cir1,cir2,cir3,cir4,cir5,cir6,cir7,cir8,cir9,cir10,cir11;
    Arc arc1,arc2,arc3,arc4,arc5,arc6,arc7,arc8,arc9,arc10,arc11;
    float cirvalue=0;
    //Clock
    static boolean ActiveClock = false;
    static Thread clock;
    static Text timer = new Text("00:00:00");
    static String missionTime = "";
    static long initialTime=0,currentTime, t;
	static long hora,seg,min;
	//Radar
	GridPane g_radar,g_raddiv,g_radval,g_accel,g_accelval,g_temperature,g_temp,g_tempval;
	Radar radar = new Radar();
	Label rval, accel, acceltext, temp, temptext;
	GridPane g_accelerometer;
	final FileChooser select = new FileChooser();
	public static final String SEPARADOR = "";
	
	//DATA SEND
	static ArrayList<String> datos = new ArrayList<>();
	static ArrayList<String> senData = new ArrayList<>();
	static int contador1=0;
	static int contador2=0;
	static int ValorSinTitulo=0;
	static int ValorEnviado;
	static Thread hilo2;
    
    public SimulationMode(Pane root, int x, int y){
        super(root, x, y);
        //Grid call
        grid = new GridPane();
        commands = new GridPane();
        master_button = new GridPane();
        ports = new GridPane();
        cont_values = new GridPane();
        payl_values = new GridPane();
        g_bars = new GridPane();
        actions = new GridPane();
        g_barstatus = new GridPane();
        g_barmission = new GridPane();
        g_img = new GridPane();
        imageprueb = new GridPane();
        g_indicators = new GridPane();
        g_graphs = new GridPane();
        g_divgraphs = new GridPane();
        g_contgraphs = new GridPane();
        g_paylgraphs = new GridPane();
        g_cgraphs = new GridPane();
        g_pgraphs = new GridPane();
        g_ts = new GridPane();
        g_divts = new GridPane();
        g_posts = new GridPane();
        g_radar = new GridPane();
        g_raddiv = new GridPane();
        g_radval = new GridPane();
        g_accelerometer = new GridPane();
        g_accel = new GridPane();
        g_accelval = new GridPane();
        g_temperature = new GridPane();
        g_temp = new GridPane();
        g_tempval = new GridPane();
        g_contcirc = new GridPane();
        g_paycirc = new GridPane();
        g_btndiv = new GridPane();
        div.div_grid(grid, 3, 9, x, y);
        div.div_grid(commands, 1, 3, 2*x/9, 5*y/27);
        div.div_grid(master_button, 2, 1, 2*x/9, 5*y/81);
        div.div_grid(ports, 3, 2, x/3, 5*y/27);
        div.div_grid(g_btndiv, 2, 1, x/9, 5*y/54);
        div.div_grid(cont_values, 1, 4, x/3, 2*y/9);
        div.div_grid(payl_values, 1, 4, x/3, 2*y/9);
        div.div_grid(g_bars, 1, 3, x/3, 2*y/9);
        div.div_grid(actions, 6, 1, x/3, 2*y/27);
        div.div_grid(g_barstatus, 6, 1, 2*x/3, 5*y/27);
        div.div_grid(g_barmission, 1, 1, 4*x/9, 5*y/27);
        div.div_grid(g_img, 5, 1, x/3, 1*y/3);
        div.div_grid(imageprueb, 1, 1, x/5, y/3);
        div.div_grid(g_indicators, 1, 4, x/9, y/3);
        div.div_grid(g_graphs, 1, 1, x/3, 2*y/3);
        div.div_grid(g_divgraphs, 3, 12, x/3, 2*y/3);
        div.div_grid(g_contgraphs, 3, 5, x/3, 11*y/18);
        div.div_grid(g_paylgraphs, 3, 5, x/3, 11*y/18);
        div.div_grid(g_cgraphs, 1, 5, 2*x/9, 11*y/18);
        div.div_grid(g_pgraphs, 1, 5, 2*x/9, 11*y/18);
        div.div_grid(g_ts, 3, 1, x/3, 1*y/18);
        div.div_grid(g_divts, 1, 3, x/9, 1*y/18);
        div.div_grid(g_posts, 1, 1, x/9, 1*y/27);
        div.div_grid(g_radar, 32, 12, x/3, 2*y/9);
        div.div_grid(g_raddiv, 1, 1, x/12, 4*y/27);
        div.div_grid(g_radval, 1, 1, x/12, y/18);
        div.div_grid(g_accelerometer, 1, 1, x/12, 4*y/27);
        div.div_grid(g_accel, 1, 1, x/12, y/18);
        div.div_grid(g_accelval, 1, 1, x/12, y/18);
        div.div_grid(g_temperature, 1, 1, x/12, 4*y/27);
        div.div_grid(g_temp, 1, 1, x/12, y/18);
        div.div_grid(g_tempval, 1, 1, x/12, y/18);
        div.div_grid(g_contcirc, 1, 3, x/9, 11*y/18);
        div.div_grid(g_paycirc, 1, 3, x/9, 11*y/18);
        
        //General Layouts
        BorderPane b1 = new BorderPane();
        BorderPane b2 = new BorderPane();
        BorderPane b3 = new BorderPane();
        BorderPane b4 = new BorderPane();
        BorderPane b5 = new BorderPane();
        BorderPane b6 = new BorderPane();
        BorderPane b7 = new BorderPane();
        BorderPane b8 = new BorderPane();
        BorderPane b9 = new BorderPane();
        BorderPane b10 = new BorderPane();
        BorderPane b11 = new BorderPane();
        BorderPane b12 = new BorderPane();
        BorderPane b13 = new BorderPane();
        BorderPane b14 = new BorderPane();
        BorderPane b15 = new BorderPane();
        BorderPane b16 = new BorderPane();
        BorderPane b17 = new BorderPane();
        BorderPane b18 = new BorderPane();
        BorderPane b19 = new BorderPane();
        BorderPane b19p = new BorderPane();
        BorderPane b20 = new BorderPane();
        BorderPane b21 = new BorderPane();
		BorderPane b22 = new BorderPane();
		BorderPane b23 = new BorderPane();
		BorderPane b24 = new BorderPane();
		BorderPane b25 = new BorderPane();
		BorderPane b26 = new BorderPane();
		BorderPane b27 = new BorderPane();
		BorderPane b30 = new BorderPane();
		BorderPane b31 = new BorderPane();
		BorderPane b32 = new BorderPane();
		BorderPane b34 = new BorderPane();
		BorderPane b35 = new BorderPane();
		BorderPane b36 = new BorderPane();
		BorderPane b37 = new BorderPane();
		BorderPane b38 = new BorderPane();
		BorderPane b39 = new BorderPane();
		BorderPane b41 = new BorderPane();
		BorderPane b42 = new BorderPane();
		BorderPane b43 = new BorderPane();
		BorderPane b44 = new BorderPane();
		BorderPane b45 = new BorderPane();
		BorderPane b46 = new BorderPane();
		BorderPane b47 = new BorderPane();
        VBox vbbar = new VBox();
        BorderPane blabel1 = new BorderPane();
        Rectangle rec_con = new Rectangle();
        Rectangle rec_pay = new Rectangle();
        StackPane stack_con = new StackPane();
        StackPane stack_pay = new StackPane();
        HBox hbc1 = new HBox();
        HBox hbc2 = new HBox();
        HBox hbc3 = new HBox();
        HBox hbc4 = new HBox();
        HBox hbc5 = new HBox();
        VBox vbc = new VBox();
        HBox hbp1 = new HBox();
        HBox hbp2 = new HBox();
        HBox hbp3 = new HBox();
        HBox hbp4 = new HBox();
        HBox hbp5 = new HBox();
        HBox hbp6 = new HBox();
        VBox vbp = new VBox();
        comm_insert = new TextField();
        //Container text
        Text press = new Text("Pressure is: ");
        Text volt = new Text("Voltage is: ");
        Text lat = new Text("Latitude is: ");
        Text lon = new Text("Longitude is: ");
        Text sats = new Text("Altitude is: ");
        Text c_temp = new Text("Temperature is: ");
        //Payload text field
        Text pressp = new Text("Pressure is: ");
        Text p_temp = new Text("Temperature is: ");
        Text rotp = new Text("Rotation is: ");
        Text accel_p = new Text("Altitude is: ");
        Text mag_p = new Text("Voltage is: ");
        //Main Label
        Label com = new Label("ENTRY COMMAND");
		DropShadow shadow = new DropShadow();
		DropShadow shadowp = new DropShadow(); shadowp.setColor(Color.rgb(184,0,101));
		DropShadow shadowr = new DropShadow(); shadowr.setColor(Color.RED);
        
        //Buttons
        //**************Main button*************//
        btn = new Button("Send");
		g_btndiv.add(b5, 0, 0);
		g_btndiv.add(b47, 1, 0);
        buttons.b_normal(btn, Color.DEEPSKYBLUE, Color.rgb(195, 201, 238, 0.75), "../CSSsheets/mbtn_sim.css", 20, x, y);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	laser.play();
            	mode = comm_insert.getText().toUpperCase();
            	if(mode.equals(loads)) {
            		Main.setEscenaUno();
            	}else if(mode.equals(exit)) {
            		System.out.println("Port Closed -> Successful");
            		System.exit(0);
            		Puerto.closePort();
            	}else if(mode.equals(simmode)) {
                    ports.add(g_btndiv, 1, 1);
            		System.out.println("Simulation mode activate -> Successful");
            		comm_insert.setText("");
            	}else if(mode.equals(com_buzzer)) {
            		byte[] A = "A.".getBytes();
    				Puerto.writeBytes(A, A.length);
            		System.out.println("Buzzer prueb command sent -> Sucessful");
            		comm_insert.setText("");
            	}
            }
        });
        comm_insert.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	if(event.getCode() == KeyCode.ENTER){
            		mode = comm_insert.getText().toUpperCase();
                	if(mode.equals(loads)) {
                		Main.setEscenaUno();
                	}else if(mode.equals(exit)) {
                		System.out.println("Port Closed -> Successful");
                		System.exit(0);
                		Puerto.closePort();
                	}else if(mode.equals(simmode)) {
                        ports.add(b5, 1, 1);
                		System.out.println("Simulation mode activate-> Successful");
                		comm_insert.setText("");
                	}else if(mode.equals(cmdcxon)) {
    					t0=(System.currentTimeMillis());
                		System.out.println("Telemetry on -> Successful");
                		comm_insert.setText("");
                		byte[] A = "A.".getBytes();
        				Puerto.writeBytes(A, A.length);
                	}else if(mode.equals(cmdcxoff)) {
                		System.out.println("Telemetry off -> Successful");
                		comm_insert.setText("");
                		byte[] B = "B.".getBytes();
        				Puerto.writeBytes(B, B.length);
                	}else if(mode.equals(com_parachute)) {
                		System.out.println("Manual release of second parachute -> Successful");
                		comm_insert.setText("");
                		byte[] I = "I.".getBytes();
        				Puerto.writeBytes(I, I.length);
                	}else if(mode.equals(com_releasedp)) {
                		System.out.println("Manual release tethered payload -> Successful");
                		comm_insert.setText("");
                		byte[] J = "J.".getBytes();
        				Puerto.writeBytes(J, J.length);
                	}else if(mode.equals(com_buzzer)) {
                		byte[] H = "H.".getBytes();
        				Puerto.writeBytes(H, H.length);
                		System.out.println("Buzzer prueb command sent -> Sucessful");
                		comm_insert.setText("");
                	}else if(mode.equals(payload_telemetry)) {
                		byte[] P = "P.".getBytes();
        				Puerto.writeBytes(P, P.length);
                		System.out.println("Payload telemetry on command -> Sucessful");
                		comm_insert.setText("");
                	}else if(mode.equals(payload_telemetry_off)) {
                		byte[] D = "D.".getBytes();
        				Puerto.writeBytes(D, D.length);
                		System.out.println("Payload telemetry off command -> Sucessful");
                		comm_insert.setText("");
                	}
      	        }
      	    }
      	});
        //**************Connect button*************//
        btn_comm = new ToggleButton("Connect");
        btn_comm.setStyle("-fx-font-size: 70%;");
        buttons.btogg_sim(btn_comm, Color.DEEPSKYBLUE, Color.rgb(240, 205, 205, 0.75), "../CSSsheets/mbtn_sim.css", 20, (x/18)-35, (5*y/81)-17);
        btn_comm.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				laser.play();
				System.out.println("Established connection -> Successful");
				if(btn_comm.getText() == "Connect") {
					btn_comm.setText("Disconnect");
						try {
							img.stop(image);
							csv_container = new CSV_CONTAINER();
							csv_payload = new CSV_PAYLOAD();
							reading();
						}catch (Exception e) {
							e.printStackTrace();
							System.out.println("ERROR->Main Button"+e);
						}			
				}else{
					//img.start(image);
					csv_container.close();
					csv_payload.close();
					Puerto.closePort();
					btn_comm.setText("Connect");
					detener();
				}
			}
		});
        //**************CSV button*************//
        btn_csv = new ToggleButton("CSV");
        btn_csv.setStyle("-fx-font-size: 100%;");
        buttons.btogg_sim(btn_csv, Color.DEEPSKYBLUE, Color.rgb(240, 205, 205, 0.75), "../CSSsheets/mbtn_sim.css", 20, (x/18)-35, (5*y/81)-17);
        btn_csv.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					FileChooser select = new FileChooser();
					select.setTitle("Open the Simulation Archive");
					File file = select.showOpenDialog(new Stage());
					leerArchivo(file);
					addBytes(datos);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
        //*************Refresh Button**************//
        ImageView imagerefresh = new ImageView(new Image("Images/refresh.png"));
        imagerefresh.setFitWidth((5*y/36)-50);
        imagerefresh.setFitHeight((5*y/36)-50);
        refresh = new Button("",imagerefresh);
        buttons.b_normal(refresh, Color.DEEPSKYBLUE, Color.rgb(255, 170, 225, 0.75), "../CSSsheets/refreshflight.css", 20, x, y);   
        refresh.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				laser.play();
				System.out.println("Serial Port Refreshed");
				cbx.getItems().clear();
                SP();	
			}
		});
        //*************Calibration Button**************//
        ImageView imagecal = new ImageView(new Image("Images/radar.png"));
        imagecal.setFitWidth((y/18)-10);
        imagecal.setFitHeight((y/18)-10);
        calibration = new Button("",imagecal);
        calibration.setContentDisplay(ContentDisplay.CENTER);
        buttons.b_action(calibration, Color.DEEPSKYBLUE, Color.rgb(255, 170, 225, 0.75), "../CSSsheets/actionsflight.css", 20, x, y);   
        calibration.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				laser.play();
				System.out.println("Calibration Command");
			}
		});
      //*************Buzzer Button**************//
        ImageView imagebuz = new ImageView();
        imagebuz.setFitWidth((y/18)-10);
        imagebuz.setFitHeight((y/18)-10);
        imagebuz.setImage(new Image("Images/sound.png"));
        buzzer = new Button("",imagebuz);
        buzzer.setContentDisplay(ContentDisplay.CENTER);
        buttons.b_action(buzzer, Color.DEEPSKYBLUE, Color.rgb(255, 170, 225, 0.75), "../CSSsheets/actionsflight.css", 20, x, y);   
        buzzer.setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				laser.play();
				System.out.println("Buzzer Command");
			}
		});
        
        //Label Entry Command
        labels.fade_shadow(Color.DEEPSKYBLUE, com, 50);
        blabel1.setCenter(com);
        commands.add(blabel1, 0, 0);
        
      //Combobox
        //*********Commands*********//
        buttons.combobox(combo,Color.DEEPSKYBLUE,30, x);
        combo.addEventHandler(MouseEvent.MOUSE_CLICKED,
		           new EventHandler<MouseEvent>() {
			           @Override
			           public void handle(MouseEvent e) {
			   				cbxsound.play();
			             }
					}
		);
        //***********Ports***********//
        comboport(cbx,Color.DEEPSKYBLUE,30, x);
        cbx.addEventHandler(MouseEvent.MOUSE_CLICKED,
		           new EventHandler<MouseEvent>() {
			           @Override
			           public void handle(MouseEvent e) {
			   				cbxsound.play();
			             }
        			}
        );
        
        //TextField
        labels.textbox(comm_insert, x, y, Color.DEEPSKYBLUE);
        	//Action needed to set the command into TextField
	        combo.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	            	if(event.getCode() == KeyCode.ENTER){
	            		comm_insert.setText(combo.getValue());
	      	        }
	      	    }
	      	});
	    /////*********Mission Time********************///////////
	        labels.textformat(timer, x/25.6, (x/3)-20);
	        timer.setEffect(shadow);
	    /////*********Container values***************////////////
	        //Rec
	        labels.rect_text(rec_con, x, y, Color.rgb(112, 48, 160,0.075), Color.rgb(31, 95, 160,0.3));
	        labels.textformat(press, x/90, (3*x/18)); labels.textformat(pressure, x/90, (3*x/18));
	        labels.textformat(volt, x/90, (3*x/18)); labels.textformat(voltage, x/90, (3*x/18));
	        labels.textformat(c_temp, x/90, (3*x/18)); labels.textformat(temp_c, x/90, (3*x/18));
	        labels.textformat(lat, x/90, (3*x/18)); labels.textformat(latitude, x/90, (3*x/18));
	        labels.textformat(lon, x/90, (3*x/18)); labels.textformat(longitude, x/90, (3*x/18));
	        labels.textformat(sats, x/90, (3*x/18)); labels.textformat(SATS_C, x/90, (3*x/18));
	        hbox(hbc1,b10,press,pressure);
	        hbox(hbc2,b11,volt,voltage);
	        hbox(hbp2,b16,c_temp,temp_c);
	        hbox(hbc3,b12,lat,latitude);
	        hbox(hbc4,b13,lon,longitude);
	        hbox(hbc5,b14,sats,SATS_C);
	        vbox(vbc,b10,b11,b16,b12,b13,b14);
	    /////*********Payload values****************////////////
	        //Rec
	        labels.rect_text(rec_pay, x, y, Color.rgb(112, 48, 160,0.075), Color.rgb(31, 95, 160,0.3));
	        labels.textformat(pressp, x/85, (3*x/18)); labels.textformat(pressurep, x/85, 3*(x/18));
	        labels.textformat(p_temp, x/85, (3*x/18)); labels.textformat(temp_tp, x/85, 3*(x/18));
	        labels.textformat(rotp, x/85, (3*x/18)); labels.textformat(rot, x/100, 3*(x/18));
	        labels.textformat(accel_p, x/85,(3*x/18)); labels.textformat(accel_v, x/100, 3*(x/18));
	        labels.textformat(mag_p, x/85, (3*x/18)); labels.textformat(mag_v, x/100, 3*(x/18));
	        hbox(hbp1,b15,pressp,pressurep);
	        hbox(hbp3,b17,p_temp,temp_tp);
	        hbox(hbp4,b18,rotp,rot);
	        hbox(hbp5,b19,accel_p,accel_v);
	        hbox(hbp6,b19p,mag_p,mag_v);
	        vbx(vbp,b15,b17,b18,b19,b19p);
	    
	    ////////////////Bar//////////////////////////////////////////////////////
	        //Container
			lbb_c = new Label("0.0 V");
			lbb_c.getStyleClass().add("battery");
			lbb_c.setMinWidth(x/20);
			lbb_c.setMaxWidth(x/20);
			bar_c= new Rectangle();
			r1c= new Rectangle();
	        r2c= new Rectangle();
	        r3c= new Rectangle();
	        r4c= new Rectangle();
	        r5c= new Rectangle();
	        r6c= new Rectangle();
	        r7c= new Rectangle();
	        r8c= new Rectangle();
	        r9c= new Rectangle();
	        r10c= new Rectangle();
	        hboxc1 = new HBox();
	        hboxc2 = new HBox();
	        recC = new StackPane();
	        cont_line = new Line();
			try {
				bar.bar_hbox(g_bars,0,0,r1c,r2c,r3c,r4c,r5c,r6c,r7c,r8c,r9c,r10c,bar_c,lbb_c,hboxc1,hboxc2,recC,Color.DEEPSKYBLUE, cont_line, Color.rgb(0,255,0,0.125),x);
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
			//Payload
			lbb_p = new Label("0.0 V");
			lbb_p.getStyleClass().add("battery");
			lbb_p.setMinWidth(x/20);
			lbb_p.setMaxWidth(x/20);
			bar_p= new Rectangle();
			r1p= new Rectangle();
	        r2p= new Rectangle();
	        r3p= new Rectangle();
	        r4p= new Rectangle();
	        r5p= new Rectangle();
	        r6p= new Rectangle();
	        r7p= new Rectangle();
	        r8p= new Rectangle();
	        r9p= new Rectangle();
	        r10p= new Rectangle();
	        hboxp1 = new HBox();
	        hboxp2 = new HBox();
	        recP = new StackPane();
	        pay_line = new Line();
			try {
				bar.bar_hbox(g_bars,0,1,r1p,r2p,r3p,r4p,r5p,r6p,r7p,r8p,r9p,r10p,bar_p,lbb_p,hboxp1,hboxp2,recP,Color.DEEPSKYBLUE, pay_line, Color.rgb(0,255,0,0.125),x);
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
		
		//////////////Image Prueb//////////////////
			image = new BorderPane();	
			try {
				img.createImage("Images/modelgif.gif", image, 2*x/4.5, /*2*x/10.74615*/ 2*x/12, Color.DEEPSKYBLUE);
				//img.start(image);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		///////////////Bar Status////////////////////////
			l1 = new Line();
			l2 = new Line();
			l3 = new Line();
			l4 = new Line();
			l5 = new Line();
			l6 = new Line();
			c1 = new Circle();
			c2 = new Circle();
			c3 = new Circle();
			c4 = new Circle();
			c5 = new Circle();
			c6 = new Circle();
			c7 = new Circle();
			lb1 = new Label();
			lb2 = new Label();
			lb3 = new Label();
			lb4 = new Label();
			lb5 = new Label();
			lb6 = new Label();
			lb7 = new Label();
			status.bar(l1,l2,l3,l4,l5,l6,c1,c2,c3,c4,c5,c6,c7,lb1,lb2,lb3,lb4,lb5,lb6,lb7,vbbar,x,y);
			b22.setCenter(vbbar);
			b22.getStyleClass().add("border");
			labels.textformat(missionsta, x/85, (x/9)-20);
///////////////Toggle Switch////////////////////////
			ToggleSwitch button = new ToggleSwitch();
			shadow.setColor(Color.DEEPSKYBLUE);
			button.setEffect(shadow);
			b24.setCenter(button);
			g_posts.add(b24, 0, 0);
			g_divts.add(g_posts, 0, 0, 1, 2);
			g_ts.add(g_divts, 2, 0);
///////////////CHARTS GRAPHICS////////////////////////
///////////////Container Charts////////////////////////
			series1 = new XYChart.Series<Number,Number>();
			series2 = new XYChart.Series<Number,Number>();
			series3 = new XYChart.Series<Number,Number>();
			series4 = new XYChart.Series<Number,Number>();
			series5 = new XYChart.Series<Number,Number>();
			//****************One*******************//
			final NumberAxis xAxis1 = new NumberAxis();
	        final NumberAxis yAxis1 = new NumberAxis();
	        final AreaChart<Number,Number> lineChart1 = new AreaChart<Number,Number>(xAxis1,yAxis1);
	        chart.graphs(xAxis1,yAxis1,lineChart1, "Pressure", "Time (s)", "Pa", series1, (2*x/9)-20,(11*y/60)-40);
	        series1.getData().size();
	        //****************Two*******************//
			final NumberAxis xAxis2 = new NumberAxis();
	        final NumberAxis yAxis2 = new NumberAxis();
	        final AreaChart<Number,Number> lineChart2 = new AreaChart<Number,Number>(xAxis2,yAxis2);
	        chart.graphs(xAxis2,yAxis2,lineChart2, "Temperature", "Time (s)", "°C", series2, (2*x/9)-20,(11*y/60)-40);
	        series2.getData().size();
	        //****************Three*******************//
			final NumberAxis xAxis3 = new NumberAxis();
	        final NumberAxis yAxis3= new NumberAxis();
	        final AreaChart<Number,Number> lineChart3 = new AreaChart<Number,Number>(xAxis3,yAxis3);
	        chart.graphs(xAxis3,yAxis3,lineChart3, "Latitude", "Time (s)", "lat", series3, (2*x/9)-20,(11*y/60)-40);
	        series3.getData().size();
	        //****************Four*******************//
			final NumberAxis xAxis4 = new NumberAxis();
	        final NumberAxis yAxis4 = new NumberAxis();
	        final AreaChart<Number,Number> lineChart4 = new AreaChart<Number,Number>(xAxis4,yAxis4);
	        chart.graphs(xAxis4,yAxis4,lineChart4, "Longitude", "Time (s)", "lon", series4, (2*x/9)-20,(11*y/60)-40);
	        series4.getData().size();
	        //****************Five*******************//
			final NumberAxis xAxis5 = new NumberAxis();
	        final NumberAxis yAxis5 = new NumberAxis();
	        final AreaChart<Number,Number> lineChart5 = new AreaChart<Number,Number>(xAxis5,yAxis5);
	        chart.graphs(xAxis5,yAxis5,lineChart5, "Altitude", "Time (s)", "m", series5, (2*x/9)-20,(11*y/60)-40);
	        series5.getData().size();
	        //**************Graph end
	        g_cgraphs.add(lineChart1, 0, 0);
	        g_cgraphs.add(lineChart2, 0, 1);
	        g_cgraphs.add(lineChart3, 0, 2);
	        g_cgraphs.add(lineChart4, 0, 3);
	        g_cgraphs.add(lineChart5, 0, 4);
	        ///////////////////Container Circle Indicators////////////////////////////////////
	        Stop[] stop1 = {new Stop(0.4, Color.rgb(240,0,50,0.95)),new Stop(1, Color.DEEPSKYBLUE)};
			LinearGradient lg2 = new LinearGradient(0, 0,1, 0, true, CycleMethod.NO_CYCLE, stop1);
////////////One//////////////
	        arc1 = new Arc(); arc1.setEffect(shadowp);
			cir1 = new Arc(); cir1.setEffect(shadow);
			lbc1 = new Label("0.0 Pa"); lbc1.setStyle("-fx-font-size: 150%;");
			HBox hbcc1 = new HBox();
			VBox vbcc1 = new VBox(0);
			Text cpressure = new Text("Pressure");
			labels.textformat(cpressure, x/85, (x/9)-50);
			circreator.arcs(cir1,width/27,6,Color.rgb(195, 225, 255, 0.3),360.0f,0.0f);
			circreator.arcsg(arc1,width/27,6,lg2,cirvalue,90.0f);
			circreator.positiontext(g_contcirc,b25,hbcc1,vbcc1,arc1,cir1,0,0,cpressure,Color.DEEPSKYBLUE); 
			vbcc1.getChildren().add(lbc1);
			////////////Two//////////////
	        arc2 = new Arc(); arc2.setEffect(shadowp);
			cir2 = new Arc(); cir2.setEffect(shadow);
			lbc2 = new Label("0.0 °C"); lbc2.setStyle("-fx-font-size: 150%;");
			HBox hbcc2 = new HBox();
			VBox vbcc2 = new VBox(0);
			Text ctemp = new Text("Temperature");
			labels.textformat(ctemp, x/85, (x/9)-50);
			circreator.arcs(cir2,width/27,6,Color.rgb(195, 225, 255, 0.3),360.0f,0.0f);
			circreator.arcsg(arc2,width/27,6,lg2,cirvalue,90.0f);
			circreator.positiontext(g_contcirc,b26,hbcc2,vbcc2,arc2,cir2,0,1,ctemp,Color.DEEPSKYBLUE);
			vbcc2.getChildren().add(lbc2);
			////////////Three//////////////
	        arc3 = new Arc(); arc3.setEffect(shadowp);
			cir3 = new Arc(); cir3.setEffect(shadow);
			lbc3 = new Label("0.0 m"); lbc3.setStyle("-fx-font-size: 150%;");
			HBox hbcc3 = new HBox();
			VBox vbcc3 = new VBox(0);
			Text calt = new Text("Altitude");
			labels.textformat(calt, x/85, (x/9)-50);
			circreator.arcs(cir3,width/27,6,Color.rgb(195, 225, 255, 0.3),360.0f,0.0f);
			circreator.arcsg(arc3,width/27,6,lg2,cirvalue,90.0f);
			circreator.positiontext(g_contcirc,b27,hbcc3,vbcc3,arc3,cir3,0,2,calt,Color.DEEPSKYBLUE);
			vbcc3.getChildren().add(lbc3);
	        
	    /////////////Payload Charts////////////////////////
			series6 = new XYChart.Series<Number,Number>();
			series7 = new XYChart.Series<Number,Number>();
			series8 = new XYChart.Series<Number,Number>();
			series9 = new XYChart.Series<Number,Number>();
			series10 = new XYChart.Series<Number,Number>();
			series11 = new XYChart.Series<Number,Number>();
			series12 = new XYChart.Series<Number,Number>();
			series81 = new XYChart.Series<Number,Number>();
			series82 = new XYChart.Series<Number,Number>();
			//****************One*******************//
			final NumberAxis xAxis6 = new NumberAxis();
	        final NumberAxis yAxis6 = new NumberAxis();
	        final AreaChart<Number,Number> lineChart6 = new AreaChart<Number,Number>(xAxis6,yAxis6);
	        chart.graphs(xAxis6,yAxis6,lineChart6, "Pressure", "Time (s)", "Pa", series6, (2*x/9)-20,(11*y/60)-40);
	        series6.getData().size();
	        //****************Two*******************//
			final NumberAxis xAxis7 = new NumberAxis();
	        final NumberAxis yAxis7 = new NumberAxis();
	        final AreaChart<Number,Number> lineChart7 = new AreaChart<Number,Number>(xAxis7,yAxis7);
	        chart.graphs(xAxis7,yAxis7,lineChart7, "Temperature", "Time (s)", "°C", series7, (2*x/9)-20,(11*y/60)-40);
	        series7.getData().size();
	        //****************Three*******************//
			final NumberAxis xAxis8 = new NumberAxis();
	        final NumberAxis yAxis8 = new NumberAxis();
	        final AreaChart<Number,Number> lineChart8 = new AreaChart<Number,Number>(xAxis8,yAxis8);
	        chart.mulgraphs(xAxis8,yAxis8,lineChart8, "Magnetometer", "Time (s)", "°", series8,series81,series82, (2*x/9)-20,(11*y/60)-40);
	        series8.getData().size();
	        series81.getData().size();
	        series82.getData().size();
	        lineChart8.getStylesheets().add(getClass().getResource("../CSSsheets/Fly_chart.css").toExternalForm());
	        //****************Four*******************//
			final NumberAxis xAxis9 = new NumberAxis();
	        final NumberAxis yAxis9 = new NumberAxis();
	        final AreaChart<Number,Number> lineChart9 = new AreaChart<Number,Number>(xAxis9,yAxis9);
	        chart.graphs(xAxis9,yAxis9,lineChart9, "Altitude", "Time (s)", "m", series9,(2*x/9)-20,(11*y/60)-40);
	        series9.getData().size();
	        //****************Five*******************//
			final NumberAxis xAxis10 = new NumberAxis();
	        final NumberAxis yAxis10 = new NumberAxis();
	        final AreaChart<Number,Number> lineChart10 = new AreaChart<Number,Number>(xAxis10,yAxis10);
	        chart.mulgraphs(xAxis10,yAxis10,lineChart10, "Accelerometer", "Time (s)", "m/s",series12,series11,series10,(2*x/9)-20,(11*y/60)-40);
	        series10.getData().size();
	        series11.getData().size();
	        series12.getData().size();
	        lineChart10.getStylesheets().add(getClass().getResource("../CSSsheets/Fly_chart.css").toExternalForm());
	       
	      //**************Graph end
	        g_pgraphs.add(lineChart6, 0, 0);
	        g_pgraphs.add(lineChart7, 0, 1);
	        g_pgraphs.add(lineChart8, 0, 2);
	        g_pgraphs.add(lineChart9, 0, 3);
	        g_pgraphs.add(lineChart10, 0, 4);
	        ///////////////////Container Circle Indicators////////////////////////////////////
////////////One//////////////
	        arc6 = new Arc(); arc6.setEffect(shadowp);
			cir6 = new Arc(); cir6.setEffect(shadow);
			lbc6 = new Label("0.0 Pa"); lbc6.setStyle("-fx-font-size: 150%;");
			HBox hbcc6 = new HBox();
			VBox vbcc6 = new VBox(0);
			Text ppressure = new Text("Pressure");
			labels.textformat(ppressure, x/85, (x/9)-50);
			circreator.arcs(cir6,width/27,6,Color.rgb(195, 225, 255, 0.3),360.0f,0.0f);
			circreator.arcsg(arc6,width/27,6,lg2,cirvalue,90.0f);
			circreator.positiontext(g_paycirc,b30,hbcc6,vbcc6,arc6,cir6,0,0,ppressure,Color.DEEPSKYBLUE);
			vbcc6.getChildren().add(lbc6);
			////////////Two//////////////
			arc7 = new Arc(); arc7.setEffect(shadowp);
			cir7 = new Arc(); cir7.setEffect(shadow);
			lbc7 = new Label("0.0 °C"); lbc7.setStyle("-fx-font-size: 150%;");
			HBox hbcc7 = new HBox();
			VBox vbcc7 = new VBox(0);
			Text ptemp = new Text("Temperature");
			labels.textformat(ptemp, x/85, (x/9)-50);
			circreator.arcs(cir7,width/27,6,Color.rgb(195, 225, 255, 0.3),360.0f,0.0f);
			circreator.arcsg(arc7,width/27,6,lg2,cirvalue,90.0f);
			circreator.positiontext(g_paycirc,b31,hbcc7,vbcc7,arc7,cir7,0,1,ptemp,Color.DEEPSKYBLUE);
			vbcc7.getChildren().add(lbc7);
			////////////Three//////////////
			arc8 = new Arc(); arc8.setEffect(shadowp);
			cir8 = new Arc(); cir8.setEffect(shadow);
			lbc8 = new Label("0.0 m"); lbc8.setStyle("-fx-font-size: 150%;");
			HBox hbcc8 = new HBox();
			VBox vbcc8 = new VBox(0);
			Text palt = new Text("Altitude");
			labels.textformat(palt, x/85, (x/9)-50);
			circreator.arcs(cir8,width/27,6,Color.rgb(195, 225, 255, 0.3),360.0f,0.0f);
			circreator.arcsg(arc8,width/27,6,lg2,cirvalue,90.0f);
			circreator.positiontext(g_paycirc,b32,hbcc8,vbcc8,arc8,cir8,0,2,palt,Color.DEEPSKYBLUE);
			vbcc8.getChildren().add(lbc8);
			
	    ///////////Radar///////////////////////////
			radar.design(x/12, 4*y/27, b34, Color.DEEPSKYBLUE, Color.rgb(184, 0, 101));
			rval = new Label();
			rval.getStyleClass().add("battery");
			rval.setText(String.valueOf(df.format(0))+"°");
			b35.setCenter(rval);
			b35.setEffect(shadow);
		//////////Indicators///////////////////////////
			Text standby = new Text("STANDBY");
			Text released = new Text("RELEASED");
			Text tar_point = new Text("TARGET POINTING");
			Text land = new Text("LANDED");
			ci1 = new Circle();
			ci2 = new Circle();
			ci3 = new Circle();
			ci4 = new Circle();
			
			labels.textformat(standby, x/85, (x/9)-50);
			labels.textformat(released, x/85, (x/9)-40);
			labels.textformat(tar_point, x/85, (x/9)-40);
			labels.textformat(land, x/85, (x/9)-40);
			
			indicators.design(standby, Color.DEEPSKYBLUE, ci1, b36, y/15);		g_indicators.add(b36, 0, 0);
			indicators.design(released, Color.DEEPSKYBLUE, ci2, b37, y/15);		g_indicators.add(b37, 0, 1);
			indicators.design(tar_point, Color.DEEPSKYBLUE, ci3, b38, y/15);	g_indicators.add(b38, 0, 2);
			indicators.design(land, Color.DEEPSKYBLUE, ci4, b39, y/15);			g_indicators.add(b39, 0, 3);
			
			Stop[] stop = {new Stop(0.4, Color.rgb(255,185,225,0.95)),new Stop(0.8, Color.rgb(95, 170, 255, 0.95))};
			LinearGradient lg1 = new LinearGradient(0, 0,1, 0, true, CycleMethod.NO_CYCLE, stop);
		////////////Accelerometer//////////////
	        arc10 = new Arc(); arc10.setEffect(shadow);
			cir10 = new Arc(); cir10.setEffect(shadow);
			HBox hbcc10 = new HBox();
			VBox vbcc10 = new VBox(0);
			circreator.arcs(cir10,(width/30),6,Color.rgb(255, 225, 255, 0.25),300.0f,300.0f);
			circreator.arcsg(arc10,(width/30),6,lg1,cirvalue,240.0f);
			circreator.position(g_accelerometer,b41,hbcc10,vbcc10,arc10,cir10,0,0);
			cir10.setFill(Color.rgb(255,255,255,0.0175));
			accel = new Label();
			accel.getStyleClass().add("battery"); accel.setStyle("-fx-font-size: 120%;");
			accel.setText(String.valueOf(df.format(0))+"");
			b42.setCenter(accel);
			b42.setEffect(shadow);
			acceltext = new Label();
			acceltext.getStyleClass().add("battery"); acceltext.setStyle("-fx-font-size: 125%;");
			acceltext.setText("ACCELEROMETER");
			b43.setCenter(acceltext);
			b43.setEffect(shadow);
		////////////Temperature//////////////
	        arc11 = new Arc(); arc11.setEffect(shadow);
			cir11 = new Arc(); cir11.setEffect(shadow);
			HBox hbcc11 = new HBox();
			VBox vbcc11 = new VBox(0);
			circreator.arcs(cir11,(width/30),6,Color.rgb(255, 225, 255, 0.25),300.0f,300.0f);
			circreator.arcsg(arc11,(width/30),6,lg1,cirvalue,240.0f);
			circreator.position(g_temperature,b44,hbcc11,vbcc11,arc11,cir11,0,0);
	        cir11.setFill(Color.rgb(255,255,255,0.0175));
			temp = new Label();
			temp.getStyleClass().add("battery"); temp.setStyle("-fx-font-size: 120%;");
			temp.setText(String.valueOf(df.format(0))+"");
			b45.setCenter(temp);
			b45.setEffect(shadow);
			temptext = new Label();
			temptext.getStyleClass().add("battery"); temptext.setStyle("-fx-font-size: 125%;");
			temptext.setText("TEMPERATURE");
			b46.setCenter(temptext);
			b46.setEffect(shadow);
			
        //Children Call Layouts
		b47.setCenter(btn_csv);
		b23.setCenter(missionsta);
		b21.setRight(buzzer);
		b20.setRight(calibration);
        b9.setCenter(rec_pay);
        stack_pay.getChildren().addAll(b9,vbp);
	    b8.setCenter(rec_con);
        stack_con.getChildren().addAll(b8,vbc);
	    b7.setCenter(timer);
	    b6.setCenter(refresh);
	    b5.setCenter(btn_comm);
	    b4.setCenter(cbx);
        b3.setCenter(comm_insert);
		b2.setCenter(combo);
        b1.setCenter(btn);
        
        //Grid
        g_tempval.add(b46, 0, 0);
        g_temp.add(b45, 0, 0);
        g_accelval.add(b43, 0, 0);
        g_accel.add(b42, 0, 0);
        g_radval.add(b35, 0, 0);
        g_raddiv.add(b34,0,0);
        g_radar.add(g_tempval, 21, 8, 8, 3);
        g_radar.add(g_temp, 21, 4, 8, 3);
        g_radar.add(g_temperature, 21, 1, 8, 8);
        g_radar.add(g_accelval, 1, 8, 8, 3);
        g_radar.add(g_accel, 1, 4, 8, 3);
        g_radar.add(g_accelerometer, 1, 1, 8, 8);
        g_radar.add(g_radval, 11, 9, 8, 3);
        g_radar.add(g_raddiv, 11, 1, 8, 8);
        g_paylgraphs.add(g_paycirc,0,0,1,5);
        g_contgraphs.add(g_contcirc,0,0,1,5);
        g_contgraphs.add(g_cgraphs,1,0,2,5);
        g_paylgraphs.add(g_pgraphs,1,0,2,5);
        g_divgraphs.add(g_ts,0,0,3,1);
        g_graphs.add(g_divgraphs,0,0,1,1);
        g_barmission.add(b22, 0, 0);
        g_barstatus.add(g_barmission, 2, 0, 4, 1);
        imageprueb.add(image, 0, 0);
        g_img.add(imageprueb, 0, 0, 1, 3);
        g_img.add(g_indicators, 3, 0, 2, 1);
        actions.add(b21, 3, 0);
        actions.add(b20, 0, 0);
        payl_values.add(stack_pay, 0, 1, 1, 3);
        cont_values.add(stack_con, 0, 1, 1, 3);
        ports.add(b23,0,1);
        ports.add(b6, 2, 0, 1, 2);
        ports.add(b4, 1, 0);
        master_button.add(b2, 0, 0);
        master_button.add(b1, 1, 0);
        commands.add(b3, 0, 1);
        commands.add(master_button, 0, 2);
        g_bars.add(actions, 0, 2);
        grid.add(g_radar, 1, 5, 1, 2);
        grid.add(g_barstatus, 0, 7, 2, 2);
        grid.add(g_img, 1, 2, 1, 3);
        grid.add(g_bars, 0, 5, 1, 2);
        grid.add(cont_values, 0, 3, 1, 2);
        grid.add(payl_values, 0, 1, 1, 2);
        grid.add(g_graphs, 2, 1, 1, 6);
        grid.add(b7, 1, 1);
        grid.add(ports, 2, 7, 1, 2);
        grid.add(commands, 0, 7, 1, 2);
        root.getChildren().add(grid);
        SP();
    }
    public void comboport(ComboBox<String> comboBox, Color color, int radius, int x) {
		DropShadow shadow = new DropShadow();
		shadow.setColor(color);
		shadow.setRadius(radius);
        comboBox.setMinWidth((x/9)-30);
        comboBox.setMaxWidth((x/9)-30);
        comboBox.setMinWidth((x/9)-30);
        comboBox.setMaxWidth((x/9)-30);
        comboBox.setEffect(shadow);
	}
    public void hbox(HBox hb, BorderPane border, Text ttext, Text tval) {
    	hb.setSpacing(10);
    	hb.getChildren().addAll(ttext,tval);
    	hb.setAlignment(Pos.CENTER);
    	border.setCenter(hb);
    }
    public void vbox(VBox vb, BorderPane b1, BorderPane b2, BorderPane b3, BorderPane b4, BorderPane b5, BorderPane b6) {
    	vb.setSpacing(5);
    	vb.getChildren().addAll(b1,b2,b3,b4,b5,b6);
    	vb.setAlignment(Pos.CENTER);
    }
    public void vbx(VBox vb, BorderPane b1, BorderPane b2, BorderPane b3, BorderPane b4, BorderPane b5) {
    	vb.setSpacing(5);
    	vb.getChildren().addAll(b1,b2,b3,b4,b5);
    	vb.setAlignment(Pos.CENTER);
    }
    ///////////////////////Serial Port////////////////////////////
    public void SP() {
    	SerialPort[] SPort = SerialPort.getCommPorts();
		   for(int i=0; i< SPort.length; i++) {
			   //cbx.getItems().add(SPort[i].getSystemPortName());
			   listData.addAll(SPort[i].getSystemPortName());
		       cbx.setItems(listData);
		   }
		   
	   }
	public void reading()  {
		String broker = "tcp://cansat.info";
		  String ClienteId = MqttAsyncClient.generateClientId();
		  String UserName = "1017";
	      String Password = "Nueqhese811";
		  MemoryPersistence persistence = new MemoryPersistence();	
				
 	    try {
			cliente = new MqttAsyncClient(broker, ClienteId, persistence);
		} catch (MqttException e2) {
			e2.printStackTrace();
		}
		
		  MqttConnectOptions opciones = new MqttConnectOptions();
		  opciones.setUserName(UserName);
		  opciones.setPassword(Password.toCharArray());
        opciones.setCleanSession(true);
        opciones.isAutomaticReconnect();
		
		  IMqttToken token;
		try {
			token = cliente.connect(opciones);
			  token.waitForCompletion();
		} catch (MqttSecurityException e1) {
			e1.printStackTrace();
		} catch (MqttException e1) {
			e1.printStackTrace();
		}
		  System.out.println(cbx.getValue().toString());
		  Puerto = SerialPort.getCommPort(cbx.getValue().toString());
		  Puerto.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

		  if(Puerto.openPort()) {
			 System.out.println("-Established Connection-");
			 btn_comm.setText("Disconnect");
			 Reloj();
			 Thread thread = new Thread() {
				 @Override 
				 public void run(){     
					 try (Scanner scanner = new Scanner(Puerto.getInputStream())) {  
						 while(scanner.hasNextLine()){
		                    	line=(scanner.nextLine());
		                    	String[] tel = line.split(",");
		                    	System.out.println(line);
		                    	contador1++;
		                    	if(ValorEnviado>senData.size()) {
		                			
		                			hilo2.stop();
									}
		                    	try{
		                    		if(tel.length==16) {
			                    		Platform.runLater(new Runnable() {
			                                @Override 
			                                public void run() {
			                                	float density = 13550; //kg/m^3
			                                	float gravity = 9.81f; //m/s^2 
					                    		float press = (float) (density*gravity*Float.parseFloat(String.valueOf(tel[6]))/(Math.pow(10,6)))+297.75312f;
					                    		System.out.println(press);
					                    		DecimalFormat form = new DecimalFormat("#0.000");
					                    		t = (System.currentTimeMillis()-t0)/1000;
					                    		
			                                	//Text Values
					                    		pressure.setText(form.format(press) + " MPa");
					                    		temp_c.setText(tel[7] + " °C");
					                    		voltage.setText(tel[8]+" V");
					                    		DecimalFormat coord = new DecimalFormat("#0.000000");
					                    		latitude.setText(String.valueOf(coord.format(Float.parseFloat(tel[10]))));
					                    		longitude.setText(String.valueOf(coord.format(Float.parseFloat(tel[11]))));
					                    		SATS_C.setText(tel[13]);
					                    		
					                    		//Voltage bar
					                    		vp(cont_line,lbb_c,Float.parseFloat(tel[8]));
					                    		
					                    		//Chart series
					                    		series(series1,t,press); //Pressure
					                    		series(series2,t,Float.parseFloat(tel[7])); //Temperature
					                    		series(series3,t,Float.parseFloat(tel[10])); //Latitude
					                    		series(series4,t,Float.parseFloat(tel[11])); //Longitude
					                    		series(series5,t,Float.parseFloat(tel[6])); //Altitude
					                    		
					                    		//Circle indicators
					                    		circreator.pressure(arc1,press, lbc1);
					                    		circreator.temperature(arc2,Double.parseDouble(tel[7]), lbc2); 
					                    		circreator.altitude(arc3,Double.parseDouble(tel[6]), lbc3); 
					                    		
					                    		//Status bar
					                    		status.barvalues(c1, c2, c3, c4, c5, c6, c7, l1, l2, l3, l4, l5, l6, lb1, lb2, lb3, lb4, lb5, lb6, lb7, tel[14]);
					                    		status.status(missionsta, tel[14]);
					                    		
					                    		//Payload standby
					                    		indicators.standby(ci1,tel[14]);
			                                }
			                            });
			                    		Line1 = tel[0]+","+tel[1]+","+tel[2]+","+tel[3]+","
			                    				+tel[4]+","+tel[5]+","+tel[6]+","+tel[7]+","
			                    				+tel[8]+","+tel[9]+","+tel[10]+","+tel[11]+","
			                    				+tel[12]+","+tel[13]+","+tel[14]+","+tel[15]+"\n";
			                    		csv_container.save(Line1);
//			                    		PublishTextMessage(line);
			                    		
			                    		
		                    		}else if(tel.length==18) {
			                    		Platform.runLater(new Runnable() {
			                                @Override 
			                                public void run() {
			                                	float density_p = 13550; //kg/m^3
			                                	float gravity_p = 9.81f; //m/s^2 
					                    		float press_p = (float) (density_p*gravity_p*Float.parseFloat(String.valueOf(tel[4]))/(Math.pow(10,6)))+297.75312f;
			                                    DecimalFormat pressform = new DecimalFormat("#.000");
			                                    DecimalFormat form = new DecimalFormat("#");
			                                    t = (System.currentTimeMillis()-t0)/1000;
			                                    
			                                    //Text values
			                                	pressurep.setText(pressform.format(press_p) + " MPa");
			                                    accel_v.setText(String.valueOf(form.format(Float.parseFloat(tel[10])))+"i"+", "+String.valueOf(form.format(Float.parseFloat(tel[11])))+"j"+", "+String.valueOf(form.format(Float.parseFloat(tel[12])))+"k");
			                                    temp_tp.setText(tel[5] + " °C");
			                                    mag_v.setText(String.valueOf(form.format(Float.parseFloat(tel[13])))+"i"+", "+String.valueOf(form.format(Float.parseFloat(tel[14])))+"j"+", "+String.valueOf(form.format(Float.parseFloat(tel[15])))+"k");
			                                    rot.setText(String.valueOf(form.format(Float.parseFloat(tel[7])))+"i"+", "+String.valueOf(form.format(Float.parseFloat(tel[8])))+"j"+", "+String.valueOf(form.format(Float.parseFloat(tel[9])))+"k");
			                                    
			                                    //Voltage bar
					                    		v9(pay_line,lbb_p,Float.parseFloat(tel[6]));
					                    		
					                    		//Chart series
					                    		series(series6,t,press_p); //Pressure
					                    		series(series7,t,Float.parseFloat(tel[5])); //Temperature
					                    		series(series8,t,Float.parseFloat(tel[13])); //Magnetometer
					                    		series(series81,t,Float.parseFloat(tel[14])); //Magnetometer
					                    		series(series82,t,Float.parseFloat(tel[15])); //Magnetometer
					                    		series(series9,t,Float.parseFloat(tel[4])); //Altitude
					                    		series(series10,t,Float.parseFloat(tel[10])); //Accelerometer
					                    		series(series11,t,Float.parseFloat(tel[11])); //Accelerometer
					                    		series(series12,t,Float.parseFloat(tel[12])); //Accelerometer
					                    		
					                    		//Circle indicators
					                    		circreator.pressure(arc6,press_p, lbc6);
					                    		circreator.temperature(arc7,Double.parseDouble(tel[5]), lbc7);
					                    		circreator.altitude(arc8,Double.parseDouble(tel[4]), lbc8);
					                    		
					                    		//Status circles indicators
					                    		indicators.mode(ci2,tel[17],"RELEASED");
					                    		indicators.mode(ci3,tel[17],"TARGET_POINTING");
					                    		indicators.mode(ci4,tel[17],"LANDED");
					                    		
					                    		//Pointing error
					                    		float angle = Float.parseFloat(tel[16]) + 90;
					                    		radar.values(angle);
					                    		rval.setText(tel[16]+"°");
					                    		
					                    		//Accelerometer center circle
					                    		double ax = Math.pow(Float.parseFloat(tel[10]),2);
					                    		double ay = Math.pow(Float.parseFloat(tel[11]),2);
					                    		double az = Math.pow(Float.parseFloat(tel[12]),2);
					                    		double accelerations = Math.sqrt(ax+ay+az);
					                    		circreator.accel(arc10,accelerations,accel);
					                    		
					                    		//Temperature center circle
					                    		circreator.temper_tp(arc11,Float.parseFloat(tel[5]),temp);
			                                }
			                            });
			                    		Line2 = tel[0]+","+tel[1]+","+tel[2]+","+tel[3]+","
			                    				+tel[4]+","+tel[5]+","+tel[6]+","+tel[7]+","
			                    				+tel[8]+","+tel[9]+","+tel[10]+","+tel[11]+","
			                    				+tel[12]+","+tel[13]+","+tel[14]+","+tel[15]+","
			                    				+tel[16]+","+tel[17]+"\n";
			                    		csv_payload.save(Line2);
			                    		//PublishTextMessage(line);
		                    		}
//		                    		PublishTextMessage(line);
		                    		
		                    	}catch (Exception e) {
		        					e.printStackTrace();
		        					System.out.println("ERROR->String Data"+e);
		        				}
		                    	contador2++;
		                   }
					 }catch (Exception e) {
							e.printStackTrace();
							System.out.println("ERROR->Reading Method"+e);
					 }
				 }
			 };
		     thread.start();  
		     }else {
		    	 Reloj();
		     }
		  }
	 //////////////////END SERIAL PORT////////////////////////////////////////////
	public void vp(Line l, Label lb, float cb){
		double num = (double) ((double)cb);
		//System.out.println(num);
    	lb.setText(String.valueOf(df.format(num))+" V");
    	if(num>=0&&num<=9) {
    		double val = cb*(width)/18.5;
    		l.setEndX(val);
    	}else{
    		l.setEndX(0);
    		lb.setText(String.valueOf(df.format(0))+" V");
    		voltage.setText(String.valueOf(df.format(0))+" V");
    	}
	}
	public void v9(Line l, Label lb, float cb){
		double num = (double) ((double)cb);
		//System.out.println(num);
    	lb.setText(String.valueOf(df.format(num))+" V");
    	if(num>=0&&num<=9) {
    		double val = cb*(width)/45;
    		l.setEndX(val);
    	}else{
    		l.setEndX(0);
    		lb.setText(String.valueOf(df.format(0))+" V");
    		voltage.setText(String.valueOf(df.format(0))+" V");
    	}
	}
	public void series(XYChart.Series <Number,Number> series, float t, float value){
		series.getData().add(
				new XYChart.Data(Float.parseFloat(String.valueOf(t)),
				Float.parseFloat(String.valueOf(value))));
	}
///////////////////////////TOGGLE SWITCH CLASS/////////////////////////////////////
	public class ToggleSwitch extends HBox {
		
		private final Label label = new Label();
		private final Button button = new Button();
		int width = (int) Screen.getPrimary().getBounds().getWidth();
		int height = (int) Screen.getPrimary().getBounds().getHeight();
		private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
		public SimpleBooleanProperty switchOnProperty() { return switchedOn; }
		
		private void init() {
			label.setText("PAYLOAD");
	        g_divgraphs.add(g_contgraphs,0,1,1,11);
			getChildren().addAll(label, button);	
			button.setOnAction((e) -> { switchedOn.set(!switchedOn.get()); });
			label.setOnMouseClicked((e) -> { switchedOn.set(!switchedOn.get()); });
			setStyle();
			bindProperties();
		}
		
		private void setStyle() {
			setPrefWidth((width/9)-30);
			setMaxWidth((width/9)-30);
			setMinWidth((width/9)-30);
			setPrefHeight((height/36)-4);
			setMaxHeight((height/36)-4);
			setMinHeight((height/36)-4);
			label.setStyle("-fx-font-size: 75%;");
			label.setAlignment(Pos.CENTER);
			setStyle("-fx-background-color: rgba(31, 95, 160, 0.475); -fx-text-fill: white; -fx-background-radius: 4; -thumb-move-animation-time: 100;");
			setAlignment(Pos.CENTER_LEFT);
		}
		
		private void bindProperties() {
			label.prefWidthProperty().bind(widthProperty().divide(2));
			label.prefHeightProperty().bind(heightProperty());
			button.prefWidthProperty().bind(widthProperty().divide(2));
			button.prefHeightProperty().bind(heightProperty());
		}
		
		public ToggleSwitch() {
			init();
			switchedOn.addListener((a,b,c) -> {
				if (c) {
					laser.play();
	        		label.setText("CONTAINER");
	        		g_divgraphs.getChildren().remove(g_contgraphs);
	                g_divgraphs.add(g_paylgraphs,0,1,1,11);
	        		setStyle("-fx-background-color: rgba(235, 0, 50, 0.75);");
	        		label.toFront();
	            }else {
	            	laser.play();
	    			label.setText("PAYLOAD");
	    			g_divgraphs.getChildren().remove(g_paylgraphs);
	    	        g_divgraphs.add(g_contgraphs,0,1,1,11);
	    			setStyle("-fx-background-color: rgba(31, 95, 160, 0.475);");
	        		button.toFront();
	    		}
			});
		}
	}
	//Cronommeter
	public static void Reloj() {
		ActiveClock = !ActiveClock;
		clock = new Thread(new Runnable(){
			@Override 
			public void run() {
				Runnable updater = new Runnable() {
					@Override
					public void run() {
						timer.setText(missionTime);
						t = currentTime/1000;
					}
				};
				while(ActiveClock){
					if(ActiveClock){
						try {
							Thread.sleep(250);
							missionTime="";
							if(initialTime==0)
								initialTime=System.currentTimeMillis();
							else {
							currentTime=System.currentTimeMillis()-initialTime;
							}
							
							hora=(long) (currentTime/3600000);
							min=(long) ((currentTime-(hora*3600000))/60000);
							seg=(long)((currentTime-((hora*3600000)+(min*60000)))/1000);
							
							if (hora<10){
								missionTime+= "0" + Long.toString(hora)+":";
							}else{
								missionTime+=Long.toString(hora)+":";
							}
							if (min<10){
								missionTime+= "0" +Long.toString(min)+":";
							}else{
								missionTime+= Long.toString(min)+":";
							}
							if (seg<10){
								missionTime+= "0" +Long.toString(seg);
							}else{
								missionTime+=Long.toString(seg);
							}
							 Platform.runLater(updater);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}	
				}
			}

		});
		if(ActiveClock) {
			initialTime=0;
			clock.start();	
		}else {
		}
	}
	public static void detener() {
		ActiveClock = false;
	}
	public static void PublishTextMessage (String messageText) {
        byte[] bytesMessage;
        int calidad = 2;
        String userContext = "Publish message";
        MessageActionListener actionListener = new MessageActionListener(topic, messageText, userContext);
        try {
            bytesMessage = messageText.getBytes();
            MqttMessage message;
            message = new MqttMessage(bytesMessage);
            message.setQos(calidad);
            cliente.publish(topic, message,userContext, actionListener);
	    } catch (MqttException e) {
	        e.printStackTrace();
	    } 
    }
	
	private static void leerArchivo(File arch)throws IOException{
		
		try(BufferedReader in = new BufferedReader(new FileReader(arch))){
			
			String linea=in.readLine();
			while(linea!= null) {
				datos.add(linea);
//				System.out.println(linea);
				linea= in.readLine();
			}
		}
		
	}
	
	
	private static void addBytes(ArrayList<String> dat) {
		try {
			String [] campos = {};
			for(int i=2; i<datos.size(); i=i+2) //Modificar para los saltos de linea OJO del CSV.
			{
				campos = datos.get(i).split(",");
				if(Puerto.openPort()) {
					senData.add(campos[3]);
					
				}
			}
			hilo2 = new Thread(hiloCsv());
			hilo2.start();
		} catch (Exception e) {
			System.out.println("No se pueden agregar los datos");
		}
	}
	
	public static Runnable hiloCsv() {
		return new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					for(ValorEnviado = 0; ValorEnviado<=senData.size() && (contador1-contador2)==0;ValorEnviado++) {
		             	try{
		             		System.out.println(senData.get(ValorEnviado));		         
			             	byte[] J = senData.get(ValorEnviado).getBytes();  
			             	byte[] N = ("\n").getBytes();
			             	Puerto.writeBytes(J, J.length);
			             	Puerto.writeBytes(N, N.length);
			             	
		             	}
		             	catch(Exception e) {
		             		System.out.println("The archive is finished");
		             	}
					 	try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				 	}
			 	break;
				}
			}
		};
	}

	
	
//////////////////////////END///////////////////////////////////////////////
}