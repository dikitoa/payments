package es.unileon.ulebank.payments;

/**
 * @author Israel
 */
public class DebitCard extends Card {
	
	public DebitCard() {
		super(CardType.DEBIT);
	}
	
	public int getCreditAccount() {
//TODO terminar metodo para comprobar el saldo de la cuenta
		return 0;
	}
}
