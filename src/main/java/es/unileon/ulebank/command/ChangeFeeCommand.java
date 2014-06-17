package es.unileon.ulebank.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.command.handler.CommandHandler;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.exceptions.PaymentException;



/**
 * 
 * @author David Gomez Riesgo
 *
 */
public class ChangeFeeCommand implements Command{
	 /**
     * Logger de la clase
     */
    private static final Logger LOG = Logger.getLogger(ModifyBuyLimitCommand.class.getName());
    /**
     * String of incorrect undo error
     */
    private static final String ERROR_UNDO = "Can't undo because command has not undoned yet.";
    /**
     * String of incorrect redo error
     */
    private static final String ERROR_REDO = "Can't redo because command has not undoned yet.";
    /**
     * Identificador del comando
     */
    private final Handler id;
    /**
     * Objeto tarjeta del que se modificaran los datos
     */
    private final Cards card;
    /**
     * Cantidad nueva a modificar
     */
    private final int newFee;
    /**
     * Cantidad antes de realizar la modificacion
     */
    private int oldFee;
    /**
     * Variable para saber si el comando ha sido ejecutado o no
     */
    private boolean executed = false;
    /**
     * Variable para saber si el comando ha sido deshecho o no
     */
    private boolean undone = false;

/**
 * 
 * @param cardId
 * @param card
 * @param fee
 * @param type
 */
    public ChangeFeeCommand(Handler cardId, Cards card, int fee) {
        this.id = new CommandHandler(cardId);
        this.card = card;
        this.newFee = fee;
    }

    /**
     * Realiza la modificacion del limite de compra ya sea diario o mensual
     * 
     * @throws CommandException
     */
    @Override
    public void execute() throws CommandException {
        
                // Guardamos la cantidad anterior para poder deshacer la
                // operacion
                this.oldFee = this.card.getFees();
                this.executed = true;
                // Cambiamos el fee por el indicado
                this.card.setFees(this.newFee);
    }

    /**
     * Deshacer la modificacion de los fees para dejarlo como estaba anteriormente.
     * 
     * @throws CommandException
     */
    @Override
    public void undo() throws CommandException {
        // Si el tipo es diario
        if (this.executed) {              
                    // Recuperamos el limite anterior
                    this.card.setFees(this.oldFee);
                    this.undone = true;
        } else {
        	LOG.log(Level.SEVERE, ERROR_UNDO);
            throw new PaymentException(ChangeFeeCommand.ERROR_UNDO);
        }

    }

    /**
     * Rehacer la modificacion de los fees despues de haberlo deshecho
     * 
     * @throws CommandException
     */
    @Override
    public void redo() throws CommandException {
        if (this.undone) {
            
        			//Volvemos a cambiar el fee por el que habiamos cambiado antes.
                    this.card.setFees(this.newFee);
                    this.undone = false;
        } else {
        	LOG.log(Level.SEVERE, ERROR_REDO);
             throw new PaymentException(ChangeFeeCommand.ERROR_REDO);
        }
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