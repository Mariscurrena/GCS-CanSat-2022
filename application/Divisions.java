package application;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Divisions extends Thread{
	@Override
	public void run() {
	}
	public void div_grid(GridPane grid, int columns, int rows, int width, int height) {	                                                                                                                     //Author: @Angel Mariscurrena
		for(int i=0;i<columns;i++) {
        	ColumnConstraints colum = new ColumnConstraints(width/columns);
        	grid.getColumnConstraints().add(colum);
        }
        for(int i=0;i<rows;i++) {
        	RowConstraints row = new RowConstraints(height/rows);
        	grid.getRowConstraints().add(row);
        }
	}
}
