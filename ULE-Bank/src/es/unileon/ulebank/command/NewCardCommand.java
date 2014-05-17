package es.unileon.ulebank.command;

import java.io.IOException;

import org.apache.log4j.Logger;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.exceptions.CommissionException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.payments.SecurityCard;

/**
 * @author Israel
 * Comando para la creacion de las tarjetas
 */
public class NewCardCommand implements Command {
	/**
	 * El logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(NewCardCommand.class.getName());
	/**
	 * Tarjeta que se va a crear
	 */
	private Card card;
	/**
	 * Identificador del comando
	 */
	private Handler id;
	/**
	 * Cuenta a la que se ha de asociar la tarjeta
	 */
	private Account account;
	/**
	 * Oficina en la que esta la cuenta a la que se va a asociar la tarjeta
	 */
	private Office office;
	/**
	 * DNI del propietario de la tarjeta
	 */
	private Handler dni;
	/**
	 * Identificador de la cuenta a la que se va a asociar la tarjeta
	 */
	private Handler accountHandler;
	/**
	 * Tipo de tarjeta a crear
	 */
	private CardType type;
	/**
	 * Identificador de la tarjeta
	 */
	private CardHandler cardHandler;
	/**
	 * Limite de compra diario para la tarjeta
	 */
	private double buyLimitDiary;
	/**
	 * Limite de compra mensual para la tarjeta
	 */
	private double buyLimitMonthly;
	/**
	 * Limite de extraccion en cajero diario para la tarjeta
	 */
	private double cashLimitDiary;
	/**
	 * Limite de extraccion en cajero mensual para la tarjeta
	 */
	private double cashLimitMonthly;
	/**
	 * Comision de emision de la tarjeta
	 */
	private double commissionEmission;
	/**
	 * Comision de mantenimiento de la tarjeta
	 */
	private double commissionMaintenance;
	/**
	 * Comision de renovacion de la tarjeta
	 */
	private double commissionRenovate;

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
	public NewCardCommand(Office office, Handler dni, Handler accountHandler, CardType type,
			String bankId, String officeId, String cardId,
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			float commissionEmission, float commissionMaintenance, float commissionRenovate) {
		cardHandler = new CardHandler(new BankHandler(bankId), officeId, cardId);
		this.id = new CommandHandler(cardHandler);
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
	}

	/**
	 * Realiza la creacion de la tarjeta con todos los parametros necesarios
	 * @throws InvalidFeeException 
	 * @throws ClientNotFoundException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	@Override
	public void execute() throws InvalidFeeException, ClientNotFoundException, NumberFormatException, IOException {
		//Obtiene el cliente de la sucursal con el DNI
		Client client = office.searchClient((DNIHandler) dni);
		//Busca la cuenta del cliente con el identificador de la cuenta
		this.account = client.searchAccount((AccountHandler) accountHandler);
		//Crea una tarjeta en funcion del tipo indicado
		switch (type) {
		case CREDIT:

			try {
				this.card = new CreditCard(cardHandler, client, account, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, commissionMaintenance, commissionRenovate);
			} catch (CommissionException e1) {
				LOG.info("Error creating commission");
			}

			break;
		case DEBIT:
			try {
				this.card = new DebitCard(cardHandler, client, account, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, commissionMaintenance, commissionRenovate);
			} catch (CommissionException e1) {
				LOG.info("Error creating commission");
			}
			break;
		case REVOLVING:

			break;
		case PURSE:

			break;
		}
		this.card.setDefaultCardProperties();
		new SecurityCard(card);
		//Por ultimo asocia la tarjeta a la cuenta
		account.addCard(card);
		
	}

	/**
	 * Deshace la creacion de la tarjeta
	 * @throws ClientNotFoundException 
	 */
	@Override
	public void undo() throws ClientNotFoundException {
		CancelCardCommand cancel = new CancelCardCommand(cardHandler, office, dni, accountHandler);
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