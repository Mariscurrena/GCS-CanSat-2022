package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSV_CONTAINER {
	StringBuilder sb;
	String archivo;
	PrintWriter pw;
	String datos = "TEAM ID, MISSION_TIME, PACKET_COUNT, PACKET_TYPE, MODE, TP_RELEASED, ALTITUDE, TEMP, VOLTAGE, GPS_TIME, GPS_LATITUDE, GPS_LONGITUDE, GPS_ALTITUDE, GPS_SATS, SOFTWARE_STATE, CMD_ECHO";	                                                                                                                     //Author: @Angel Mariscurrena
	public CSV_CONTAINER() throws FileNotFoundException { 
		archivo = "Telemetry_Container.csv";
		pw = new PrintWriter(new File(archivo));
		sb = new StringBuilder();
		sb.append(datos);
	}
	public void save(String line) throws FileNotFoundException{
		sb.append('\n');
		sb.append(line);
		sb.append(',');
	}

	public void close(){
			pw.write(sb.toString());
			pw.close();
	}
}