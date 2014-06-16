package es.unileon.ulebank.command.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.time.Time;

/**
 * CommandHandler class
 * 
 * @author Rober dCR
 * @date 9/04/2014
 * @brief Class of the identifier for Commands
 */
@Entity
@Table(name = "GENERIC_HANDLER", catalog = "ULEBANK_FINAL")
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "CommandHandler")
public class CommandHandler extends Handler {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Class constructor
     * 
     * @param handler
     */
    public CommandHandler(Handler handler) {
        final DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmssss");
        this.setId(handler.toString() + " "
                + dateFormat.format(new Date()).toString());
    }

    public CommandHandler() {
        String uuid = UUID.randomUUID().toString();
        String time = String.valueOf(Time.getInstance().getTime());
        this.setId(uuid + "-" + time);
    }
}
