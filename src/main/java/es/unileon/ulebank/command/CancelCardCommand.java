package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.command.handler.CommandHandler;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;

/**
 * @author Israel Comando para realizar la cancelacion de la tarjeta
 */
public class CancelCardCommand implements Command {
	 /**
     * Logger de la clase
     */
    private static final Logger LOG = Logger
            .getLogger(CancelCardCommand.class.getName());
    /**
     * Identificador del comando
     */
    private Handler id;
    /**
     * Identificador de la tarjeta a cancelar
     */
    private Handler cardId;
    /**
     * Cuenta a la que esta asociada la tarjeta que se va a cancelar
     */
    private Account account;

    /**
     * Constructor de la clase
     * 
     * @param cardId
     * @param office
     * @param dni
     * @param accountNumber
     * @throws ClientNotFoundException
     */
    public CancelCardCommand(String cardId, Office office, String dni,
            String accountNumber) {
        this.cardId = new GenericHandler(cardId);
        this.id = new CommandHandler(this.cardId);
        this.account = office.searchClient(new GenericHandler(dni)).searchAccount(new GenericHandler(accountNumber));
    }

    /**
     * Realiza la cancelacion de la tarjeta
     * @throws CommandException 
     */
    @Override
    public void execute() {
        // Se borra la tarjeta de la lista de tarjetas de la cuenta
        if (this.account.removeCard(this.cardId)) {
        	LOG.log(Level.INFO, "Card cancelled successfully.");
        } else {
        	LOG.log(Level.SEVERE, "Card cannot cancelled.");
        }
    }

    /**
     * Operacion no soportada, no se puede deshacer una cancelacion
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    /**
     * Operacion no soportada, como no se puede deshacer una cancelacion tampoco
     * se puede rehacer
     */
    @Override
    public void redo() {
        throw new UnsupportedOperationException();
    }

    /**
     * Devuelve el identificador del comando
     * 
     * @return command id
     */
    @Override
    public Handler getID() {
        return this.id;
    }
}