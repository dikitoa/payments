package es.unileon.ulebank.command;

import java.util.logging.Logger;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.command.handler.CommandHandler;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.exceptions.PaymentException;

/**
 * @author Israel Comando para la sustitucion de la tarjeta
 */
public class ReplacementCardCommand implements Command {
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
     * Tarjeta que vamos a sustituir
     */
    private Card card;
    /**
     * Identificador de la tarjeta a sustituir
     */
    private final Handler cardId;
    /**
     * Cuenta a la que esta asociada la tarjeta
     */
    private final Account account;
    /**
     * PIN antes de la sustitucion
     */
    private String oldPin;
    /**
     * PIN nuevo que es generado
     */
    private String newPin;
    /**
     * CVV antes de la sustitucion
     */
    private String oldCvv;
    /**
     * CVV nuevo que es generado
     */
    private String newCvv;
    /**
     * Anterior fecha de caducidad
     */
    private String oldExpirationDate;
    /**
     * Nueva fecha de caducidad
     */
    private String newExpirationDate;
    /**
     * Variable para saber si el comando ha sido ejecutado o no
     */
    private boolean executed = false;
    /**
     * Variable para saber si el comando ha sido deshecho o no
     */
    private boolean undone = false;

    /**
     * Constructor de la clase
     * 
     * @param cardId
     * @param office
     * @param dni
     * @param accountHandler
     */
    public ReplacementCardCommand(Handler cardId, Office office, Handler dni,
            Handler accountHandler)  {
        this.id = new CommandHandler(cardId);
        this.cardId = cardId;
        this.account = office.searchClient(dni).searchAccount(accountHandler);
    }

    /**
     * Realiza la sustitucion de la tarjeta
     * 
     * @throws CommandException 
     */
    @Override
    public void execute() throws CommandException {
        // Buscamos la tarjeta en la cuenta a la que esta asociada a traves
        // del identificador
        this.card = this.account.searchCard(this.cardId);
        // Guardamos el PIN anterior
        this.oldPin = this.card.getPin();
        // Generamos el nuevo PIN y lo almacenamos
        this.newPin = this.card.generatePinCode();
        // Cambiamos el PIN por el nuevo
        this.card.setPin(this.newPin);
        // Guardamos el anterior CVV
        this.oldCvv = this.card.getCvv();
        // Generamos un CVV nuevo y lo guardamos
        this.newCvv = this.card.generateCVV();
        // Cambiamos el CVV por el nuevo
        this.card.setCvv(this.newCvv);
        // Guardamos la anterior fecha de caducidad
        this.oldExpirationDate = this.card.getExpirationDate();
        // Generamos la nueva fecha de caducidad y la almacenamos
        this.newExpirationDate = this.card.generateExpirationDate();
        // Cambiamos la fecha de caducidad por la nueva
        this.card.setExpirationDate(this.newExpirationDate);
        this.executed = true;
    }

    /**
     * Restaura los valores antes de la sustitucion
     * 
     * @throws CommandException
     */
    @Override
    public void undo() throws CommandException {
        if (this.executed) {
            // Restaura el CVV
            this.card.setCvv(this.oldCvv);
            // Restaura el codigo PIN
            this.card.setPin(this.oldPin);
            // Restaura la fecha de caducidad
            this.card.setExpirationDate(this.oldExpirationDate);
            this.undone = true;
        } else {
            LOG.severe(ReplacementCardCommand.ERROR_UNDO);
            throw new PaymentException(ReplacementCardCommand.ERROR_UNDO);
        }
    }

    /**
     * Vuelve a modificar los valores de la sustitucion
     * 
     * @throws CommandException
     */
    @Override
    public void redo() throws CommandException {
        if (this.undone) {
            // Vuelve a cambiar el CVV por el nuevo
            this.card.setCvv(this.newCvv);
            // Vuelve a cambiar el PIN por el nuevo
            this.card.setPin(this.newPin);
            // Vuelve a cambiar la fecha de caducidad por la nueva
            this.card.setExpirationDate(this.newExpirationDate);
        } else {
            LOG.severe(ReplacementCardCommand.ERROR_REDO);
            throw new PaymentException(ReplacementCardCommand.ERROR_REDO);
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
