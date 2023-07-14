package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSV_PAYLOAD {
	StringBuilder sb;
	String archivo;
	PrintWriter pw;
	String datos = "TEAM ID, MISSION_TIME, PACKET_COUNT, PACKET_TYPE, TP_ALTITUDE, TP_TEMP, TP_VOLTAGE, GYRO_R, GYRO_P, GYRO_Y, ACCEL_R, ACCEL_P, ACCEL_Y, MAG_R, MAG_P, MAG_Y, POITING_ERROR, TP_SOFTWARE_STATE";          	                                                                                                                     //Author: @Angel Mariscurrena              
	public CSV_PAYLOAD() throws FileNotFoundException {
		archivo = "Telemetry_Payload.csv";
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