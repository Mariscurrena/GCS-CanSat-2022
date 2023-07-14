package application;


import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class MessageActionListener implements IMqttActionListener {

	protected final String messageText;
    protected final String topic;
    protected final String userContext;
	
	public MessageActionListener(String topic, String messageText, String userContext) {
		        this.topic = topic;
		        this.messageText = messageText;
				this.userContext = userContext;
		    }
	@Override
	public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
		System.out.println("El error es: " + exception );
	}

	@Override
	public void onSuccess(IMqttToken asyncActionToken) {
		 if ((asyncActionToken != null) && asyncActionToken.getUserContext().equals(userContext)) 
	        {
			 System.out.println( String.format("Mensaje '%s' publicado en el tema '%s'",messageText, topic));
	        }
	}
}