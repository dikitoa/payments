package es.unileon.ulebank.command;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.command.handler.CommandHandler;
import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.domain.CardBean;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.domain.Offices;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.payments.handler.CardHandler;

/**
 * @author Israel Comando para la creacion de las tarjetas
 */
public class NewCardCommand implements Command {
	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(NewCardCommand.class
			.getName());
	/**
	 * Tarjeta que se va a crear
	 */
	private Cards card;
	/**
	 * Identificador del comando
	 */
	private Handler id;
	/**
	 * Cuenta a la que se ha de asociar la tarjeta
	 */
	private Accounts account;
	/**
	 * Oficina en la que esta la cuenta a la que se va a asociar la tarjeta
	 */
	private Offices office;
	/**
	 * Tipo de tarjeta a crear
	 */
	private String cardType;
	/**
	 * Identificador de la tarjeta
	 */
	private Handler cardHandler;
	/**
	 * Duegno de la tarjeta
	 */
	private final Client client;
	
	private List<Cards> result;
	
	private CardBean bean;

	/**
	 * Constructor del comando que recibe los datos necesarios para crear una
	 * tarjeta
	 * 
	 * @param office
	 * @param client
	 * @param account
	 * @param cardType
	 * @param officeId
	 * @param cardId
	 * @throws CommandException
	 */
	public NewCardCommand(Client client, Accounts account, CardBean bean, List<Cards> result) throws CommandException {
		this.client = client;
		this.account = account;
		this.result = result;
		this.bean = bean;
		try {
			this.cardHandler = new CardHandler(bean.getCardNumber());
		} catch (MalformedHandlerException e) {
			LOG.log(Level.SEVERE, e.getMessage());
		}
		this.cardType = bean.getCardType();
		this.id = new CommandHandler(this.cardHandler);
	}

	/**
	 * Realiza la creacion de la tarjeta con todos los parametros necesarios
	 * 
	 * @throws CommandException
	 * 
	 * @throws InvalidFeeException
	 * @throws ClientNotFoundException
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	@Override
	public void execute() throws CommandException {

		try {
			if ("CREDIT".equalsIgnoreCase(this.cardType)) {
				this.card = new CreditCard(this.cardHandler, this.client,
						this.account);
			} else if ("DEBIT".equalsIgnoreCase(this.cardType)) {
				this.card = new DebitCard(this.cardHandler, this.client,
						this.account);
			}
			this.card.setBuyLimitDiary(bean.getBuyLimitDiary());
			this.card.setBuyLimitMonthly(bean.getBuyLimitMonthly());
			this.card.setCashLimitDiary(bean.getCashLimitDiary());
			this.card.setCashLimitMonthly(bean.getCashLimitMonthly());
			this.card.setFees(bean.getFee());
			this.card.setClient(client);
			this.card.setAccounts(account);
		} catch (NumberFormatException e) {
			LOG.log(Level.SEVERE, e.getMessage());
			throw new NumberFormatException(e.getMessage());
		}

		if (this.card != null) {
			this.account.addCard(this.card);
			result.add(card);
		}
	}

	/**
	 * Deshace la creacion de la tarjeta
	 * 
	 * @throws CommandException
	 * 
	 * @throws ClientNotFoundException
	 */
	@Override
	public void undo() throws CommandException {
		CancelCardCommand cancel;
		cancel = new CancelCardCommand(this.cardHandler.toString(), this.office,
				this.client.getId(), this.account.getAccountNumber());
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
	 * 
	 * @return command id
	 */
	@Override
	public Handler getID() {
		return this.id;
	}
}