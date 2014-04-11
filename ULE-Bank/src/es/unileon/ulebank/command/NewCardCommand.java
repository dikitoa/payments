package es.unileon.ulebank.command;

import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.payments.Client;
import es.unileon.ulebank.strategy.StrategyCommission;

/**
 * @author Israel
 */
public class NewCardCommand implements Command {
	
	private Handler id;
	private Client owner;
	private Account account;
	private CardType type;
	private CardHandler cardId;
	private double buyLimitDiary;
	private double buyLimitMonthly;
	private double cashLimitDiary;
	private double cashLimitMonthly;
	private StrategyCommission commissionEmission;
	private StrategyCommission commissionMaintenance;
	private StrategyCommission commissionRenovate;
	private double limitDebit;
	
	public NewCardCommand(Client owner, Account account, CardType type, 
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			StrategyCommission commissionEmission, StrategyCommission commissionMaintenance, 
			StrategyCommission commissionRenovate, double limitDebit) {
		this.account = account;
		this.owner = owner;
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
	
	@Override
	public void execute() {
		cardId = new CardHandler();
		Card card = new Card(cardId, owner, account, type, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, commissionMaintenance, commissionRenovate, limitDebit);
		account.addCard(card);
	}

	@Override
	public void undo() {
		new CancelCardCommand(cardId, account);
	}

	@Override
	public void redo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Handler getId() {
		return this.id;
	}
}
