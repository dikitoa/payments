package es.unileon.ulebank.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * CommandHandler class
 * @author Rober dCR
 * @date 9/04/2014
 * @brief Class of the identifier for Commands
 */
public class CommandHandler implements Handler {

	private Handler id;
	private String date;
	
	/**
	 * Class constructor
	 * @param id
	 */
	public CommandHandler(Handler id){
		this.id = id;
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		this.date = dateFormat.format(Calendar.getInstance().toString());
	}
	
	@Override
	public int compareTo(Handler another) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Getter id
	 * @return id
	 */
	public Handler getId(){
		return this.id;
	}

	/**
	 * Getter date
	 * @return date
	 */
	public String getDate(){
		return this.date.toString();
	}
}
