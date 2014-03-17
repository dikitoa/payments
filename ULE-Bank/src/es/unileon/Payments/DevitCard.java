package es.unileon.Payments;

public class DevitCard extends Card {
	private StrategyCommissionDevit commission;
	
	public DevitCard() {
		super(CardType.DEVIT);
	}
}
