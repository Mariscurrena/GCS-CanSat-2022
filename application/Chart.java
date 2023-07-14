package application;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Chart extends Thread{
	@Override
	public void run() {
	}
	public void graphs(NumberAxis xA, NumberAxis yA, 
					   AreaChart<Number,Number> lineChart, 
					   String title, String lx, String ly, 
					   XYChart.Series<Number,Number>series, 
					   double x, double y) {
        yA.setLabel(ly);
        //xA.setLabel(lx);
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.rgb(31, 95, 160));
		shadow.setRadius(20);
		lineChart.setEffect(shadow);
        lineChart.setTitle(title);
        lineChart.getData().add(series);
        lineChart.setPrefSize(x,y);
        lineChart.setMaxSize(x,y);
        lineChart.setMinSize(x,y);
        lineChart.setCreateSymbols(false);
	}
	
	@SuppressWarnings("unchecked")	                                                                                                                     //Author: @Angel Mariscurrena
	public void mulgraphs(NumberAxis xA, NumberAxis yA, 
			   AreaChart<Number,Number> lineChart, 
			   String title, String lx, String ly, 
			   XYChart.Series<Number,Number>series1,
			   XYChart.Series<Number,Number>series2,
			   XYChart.Series<Number,Number>series3,
			   double x, double y) {
		yA.setLabel(ly);
		//xA.setLabel(lx);
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.rgb(31, 95, 160));
		shadow.setRadius(20);
		lineChart.setEffect(shadow);
		lineChart.setTitle(title);
		lineChart.getData().addAll(series1,series2,series3);
		lineChart.setPrefSize(x,y);
		lineChart.setMaxSize(x,y);
		lineChart.setMinSize(x,y);
		lineChart.setCreateSymbols(false);
		}
	public void restart(XYChart.Series<Number,Number>series) {
		if ( series.getData().size() % 100 == 0 ){
            series.getData().remove(0);
        }
	}
}