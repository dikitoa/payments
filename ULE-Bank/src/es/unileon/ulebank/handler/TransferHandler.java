package es.unileon.ulebank.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class of Transfer Handler
 * @author Rober dCR
 * @date 9/04/2014
 * @brief Identifier of Transfer
 */
public class TransferHandler implements Handler {

	private String id;
	private String date;
	private String sender;
	private String receiver;
	
	/**
	 * Class constructor
	 * @param sender
	 * @param receiver
	 */
	public TransferHandler(String sender, String receiver){
		this.sender = sender.substring(sender.length()/2);
		this.receiver = receiver.substring(receiver.length()/2);
		this.setDateCode();
		this.id = this.sender + this.receiver + this.date;
	}
	
	@Override
	public int compareTo(Handler another) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Getter id
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Method that obtains the code from the date
	 */
	private void setDateCode(){
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		this.date = dateFormat.format(Calendar.getInstance().toString());
	}
}
