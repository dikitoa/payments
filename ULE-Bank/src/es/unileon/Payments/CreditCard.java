package es.unileon.Payments;

public class CreditCard extends Card {
	private StrategyCommission commission;
	
	public CreditCard() {
		super(CardType.CREDIT);
	}
}
