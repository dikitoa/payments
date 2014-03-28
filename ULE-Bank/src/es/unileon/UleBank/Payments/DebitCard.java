package es.unileon.UleBank.Payments;

import es.unileon.UleBank.Strategy.StrategyCommission;
import es.unileon.UleBank.Strategy.StrategyCommissionEmissionDebit;

public class DebitCard extends Card {
	private StrategyCommission commission;
	
	public DebitCard() {
		super(CardType.DEBIT);
		this.commission = new StrategyCommissionEmissionDebit();
	}
	
	public int getCreditAccount() {
//TODO terminar metodo para comprobar el saldo de la cuenta
		return 0;
	}

	/**
	 * Devuelve la comisión actual de la tarjeta de crédito
	 * @return
	 */
	public StrategyCommission getCommission() {
		return commission;
	}
	
	/**
	 * Cambia la comisión empleando el patrón Strategy
	 * @param commission
	 */
	public void setStrategy(StrategyCommission commission) {
		this.commission = commission;
	}
}
