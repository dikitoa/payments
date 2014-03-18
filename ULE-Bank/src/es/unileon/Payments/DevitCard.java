package es.unileon.Payments;

public class DevitCard extends Card {
	private StrategyCommission commission;
	
	public DevitCard() {
		super(CardType.DEVIT);
	}
}
