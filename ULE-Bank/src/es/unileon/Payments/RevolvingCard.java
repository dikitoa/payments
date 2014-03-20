package es.unileon.Payments;

public class RevolvingCard extends Card {

	private float interest = 1;
	
	public RevolvingCard() {
		super(CardType.REVOLVING);
	}

	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}
}
