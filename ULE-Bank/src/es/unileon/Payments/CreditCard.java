package es.unileon.Payments;

public class CreditCard extends Card {
	private StrategyCommissionCredit commission;
	
	public CreditCard() {
		super(CardType.CREDIT);
	}
}
