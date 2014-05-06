package es.unileon.ulebank.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.Client;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.payments.Office;

/**
 * @author Israel
 * Comando para la creacion de las tarjetas
 */
public class NewCardCommand implements Command {
	//Tarjeta que se va a crear
	private Card card;
	//Identificador del comando
	private Handler id;
	//Cuenta a la que se ha de asociar la tarjeta
	private Account account;
	//Oficina en la que esta la cuenta a la que se va a asociar la tarjeta
	private Office office;
	//DNI del propietario de la tarjeta
	private DNIHandler dni;
	//Identificador de la cuenta a la que se va a asociar la tarjeta
	private AccountHandler accountHandler;
	//Tipo de tarjeta a crear
	private CardType type;
	//Identificador de la tarjeta
	private CardHandler cardId;
	//Limite de compra diario para la tarjeta
	private double buyLimitDiary;
	//Limite de compra mensual para la tarjeta
	private double buyLimitMonthly;
	//Limite de extraccion en cajero diario para la tarjeta
	private double cashLimitDiary;
	//Limite de extraccion en cajero mensual para la tarjeta
	private double cashLimitMonthly;
	//Comision de emision de la tarjeta
	private float commissionEmission;
	//Comision de mantenimiento de la tarjeta
	private float commissionMaintenance;
	//Comision de renovacion de la tarjeta
	private float commissionRenovate;
	//Limite de deuda de la tarjeta
	private double limitDebit;
	
	/**
	 * Constructor de la clase
	 * @param office
	 * @param dni
	 * @param accountHandler
	 * @param type
	 * @param buyLimitDiary
	 * @param buyLimitMonthly
	 * @param cashLimitDiary
	 * @param cashLimitMonthly
	 * @param commissionEmission
	 * @param commissionMaintenance
	 * @param commissionRenovate
	 * @param limitDebit
	 */
	public NewCardCommand(Office office, DNIHandler dni, AccountHandler accountHandler, CardType type, 
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			float commissionEmission, float commissionMaintenance, 
			float commissionRenovate, double limitDebit) {
		cardId = new CardHandler();
		this.id = new CommandHandler(cardId);
		this.office = office;
		this.dni = dni;
		this.accountHandler = accountHandler;
		this.type = type;
		this.buyLimitDiary = buyLimitDiary;
		this.buyLimitMonthly = buyLimitMonthly;
		this.cashLimitDiary = cashLimitDiary;
		this.cashLimitMonthly = cashLimitMonthly;
		this.commissionEmission = commissionEmission;
		this.commissionMaintenance = commissionMaintenance;
		this.commissionRenovate = commissionRenovate;
		this.limitDebit = limitDebit;
	}
	
	/**
	 * Realiza la creacion de la tarjeta con todos los parametros necesarios
	 */
	@Override
	public void execute() {
		try {
			//Obtiene el cliente de la sucursal con el DNI
			Client client = office.searchClient(dni);
			//Busca la cuenta del cliente con el identificador de la cuenta
			this.account = client.searchAccount(accountHandler);
			//Crea una tarjeta en funcion del tipo indicado
			switch (type) {
			case CREDIT:
				this.card = new CreditCard(cardId, client, account, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, commissionMaintenance, commissionRenovate, limitDebit);
				break;
			case DEBIT:
				this.card = new DebitCard(cardId, client, account, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, commissionMaintenance, commissionRenovate, limitDebit);
				break;
			case REVOLVING:

				break;
			case PURSE:

				break;
			}
		} catch (CommissionException | NumberFormatException | IOException | ClientNotFoundException e) {
			Logger.getLogger(NewCardCommand.class.toString()).log(Level.SEVERE, null, e);
		}
		//Por ultimo asocia la tarjeta a la cuenta
		account.addCard(card);
	}

	/**
	 * Deshace la creacion de la tarjeta
	 */
	@Override
	public void undo() {
		CancelCardCommand cancel = new CancelCardCommand(cardId, office, dni, accountHandler);
		cancel.execute();
	}

	/**
	 * Operacion no soportada, no se puede deshacer la cancelacion
	 */
	@Override
	public void redo() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Devuelve el identificador del comando
	 */
	@Override
	public Handler getId() {
		return this.id;
	}
}
