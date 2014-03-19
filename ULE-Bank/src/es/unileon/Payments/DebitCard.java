package es.unileon.Payments;

public class DebitCard extends Card {
	private StrategyCommission commission;
	
	public DebitCard() {
		super(CardType.DEVIT);
	}
}
