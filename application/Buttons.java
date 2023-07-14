package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Buttons extends Thread{
	@Override
	public void run() {
	}
	public void combobox(ComboBox<String> comboBox, Color color, int radius, int x) {
		DropShadow shadow = new DropShadow();
		ObservableList<String> commands = 
			    FXCollections.observableArrayList(
		    		"LOAD SCREEN",
			        "CMD,1017,FLY,ACTIVATE",	//Encender telemetria
			        "CMD,1017,CX,ON",			//Encender telemetria
			        "CMD,1017,CX,OFF",			//Apagar telemetria
			        "CMD,1017,SIM,ENABLE",		//Habilitar modo simulación
			    	"CMD,1017,SIM,ACTIVATE",	//Activar modo simulación
			        "CMD,1017,SIM,DISABLE",		//Deshabilita modo simulación
			        "CMD,1017,ST,13:35:59",		//Hora
			        "CMD,1017,SIMP,101325",		//Mandar presión
			        "CMD,1017,PARCH,RELEASE",	//Activar paracaídas
			        "CMD,1017,RELP,RELEASE",	//Liberación del payload
			        "CMD,1017,FLY,BUZZER",		//Encender buzzer container
			        "CMD,1017,TP,ON",			//Encender Telemetría Payload
			        "CMD,1017,TP,OFF",			//Apagar Telemetría Payload
			        "EXIT"
			    );
		shadow.setColor(color);
		shadow.setRadius(radius);
        comboBox.setMaxWidth((x/9)-40);
        comboBox.setMaxWidth((x/9)-40);
        comboBox.setEffect(shadow);
		comboBox.setItems(commands);
	}
	public void b_circle(ToggleButton btn, Color c1, Color c2, String adress) {	                                                                                                                     //Author: @Angel Mariscurrena
        color_edit(btn,adress);
		shadow_color(btn, c1, 30);
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
              new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                shadow_color(btn, c2, 50);
                }
             }); 
        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
            new EventHandler<MouseEvent>() { 
              @Override
              public void handle(MouseEvent e) {
               shadow_color(btn, c1, 30);
               }
            });
	}
	public void b_action(Button btn, Color c1, Color c2, String adress, int radius, int x, int y) {	
        color_button(btn,adress);
		shadow_button(btn, c1, radius);
        btn.setMaxWidth((x/18)-5);
        btn.setMinWidth((x/18)-5);
        btn.setMaxHeight((y/27)-2);
        btn.setMinHeight((y/27)-2);
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
              new EventHandler<MouseEvent>() {
                @Override
              public void handle(MouseEvent e) {
                  shadow_button(btn, c2, radius);
               }
             }); 
        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
            new EventHandler<MouseEvent>() { 
              @Override
              public void handle(MouseEvent e) {
            	  shadow_button(btn, c1, radius);
               }
            });
	}
	public void b_normal(Button btn, Color c1, Color c2, String adress, int radius, int x, int y) {	
        color_button(btn,adress);
		shadow_button(btn, c1, radius);
        btn.setMaxWidth((x/9)-35);
        btn.setMinWidth((x/9)-35);
        btn.setMaxHeight((5*y/81)-17);
        btn.setMinHeight((5*y/81)-17);
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
              new EventHandler<MouseEvent>() {
                @Override
              public void handle(MouseEvent e) {
                  shadow_button(btn, c2, radius+8);
               }
             }); 
        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
            new EventHandler<MouseEvent>() { 
              @Override
              public void handle(MouseEvent e) {
            	  shadow_button(btn, c1, radius);
               }
            });
	}
	public void btogg_normal(ToggleButton btn, Color c1, Color c2, String adress, int radius, int x, int y) {	
        color_edit(btn,adress);
		shadow_color(btn, c1, radius);
        btn.setMaxWidth((x/9)-35);
        btn.setMinWidth((x/9)-35);
        btn.setMaxHeight((5*y/81)-17);
        btn.setMinHeight((5*y/81)-17);
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
              new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                	shadow_color(btn, c2, radius+8);
                }
             }); 
        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
            new EventHandler<MouseEvent>() { 
              @Override
              public void handle(MouseEvent e) {
            	  shadow_color(btn, c1, radius);
               }
            });
	}
	public void btogg_sim(ToggleButton btn, Color c1, Color c2, String adress, int radius, int x, int y) {	
        color_edit(btn,adress);
		shadow_color(btn, c1, radius);
        btn.setMaxWidth(x);
        btn.setMinWidth(x);
        btn.setMaxHeight(y);
        btn.setMinHeight(y);
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
              new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                	shadow_color(btn, c2, radius+8);
                }
             }); 
        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
            new EventHandler<MouseEvent>() { 
              @Override
              public void handle(MouseEvent e) {
            	  shadow_color(btn, c1, radius);
               }
            });
	}
	public void shadow_color(ToggleButton btn, Color color, int radius) {
		DropShadow shadow = new DropShadow();
		shadow.setColor(color);
		shadow.setRadius(radius);
		btn.setEffect(shadow);
	}
	public void shadow_button(Button btn, Color color, int radius) {
		DropShadow shadow = new DropShadow();
		shadow.setColor(color);
		shadow.setRadius(radius);
		btn.setEffect(shadow);
	}
	public void color_edit(ToggleButton btn, String adress) {
		btn.getStylesheets().add(getClass().getResource(adress).toExternalForm());
	}
	public void color_button(Button btn, String adress) {
		btn.getStylesheets().add(getClass().getResource(adress).toExternalForm());
	}
}
