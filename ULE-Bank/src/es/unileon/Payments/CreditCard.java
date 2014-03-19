package es.unileon.Payments;

public class CreditCard extends Card {

	private int limitDebt;
	
	public CreditCard() {
		super(CardType.CREDIT);
		this.limitDebt = 1000;
	}
	
	public CreditCard(int payroll) {
		super(CardType.CREDIT);
		this.limitDebt = payroll*3;
	}

	/**
	 * Devuelve el límite de deuda de la tarjeta
	 * @return
	 */
	public int getLimitDebt() {
		return limitDebt;
	}

	/**
	 * Cambia el límite de deuda por el que recibe
	 * @param limitDebt
	 */
	public void setLimitDebt(int limitDebt) {
		this.limitDebt = limitDebt;
	}
	
}
