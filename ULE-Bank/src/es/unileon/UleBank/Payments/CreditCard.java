package es.unileon.UleBank.Payments;

public class CreditCard extends Card {

	private StrategyCommission commission;
	private int limitDebt;
	
	public CreditCard() {
		super(CardType.CREDIT);
		this.limitDebt = 1000;
		this.commission = new StrategyCommissionEmissionCredit();
	}
	
	public CreditCard(int payroll) {
		super(CardType.CREDIT);
		this.limitDebt = payroll*3;
		this.commission = new StrategyCommissionEmissionCredit();
	}

	/**
	 * Devuelve el limite de deuda de la tarjeta
	 * @return
	 */
	public int getLimitDebt() {
		return limitDebt;
	}

	/**
	 * Cambia el limite de deuda por el que recibe
	 * @param limitDebt
	 */
	public void setLimitDebt(int limitDebt) {
		this.limitDebt = limitDebt;
	}

	/**
	 * Devuelve la comisión actual para la tarjeta de crédito
	 * @return
	 */
	public StrategyCommission getCommission() {
		return commission;
	}

	/**
	 * Cambia la comisión de la tarjeta empleando el patrón Strategy
	 * @param commission
	 */
	public void setStrategy(StrategyCommission commission) {
		this.commission = commission;
	}
}
