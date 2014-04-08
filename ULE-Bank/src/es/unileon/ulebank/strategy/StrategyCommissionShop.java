package es.unileon.ulebank.strategy;

public class StrategyCommissionShop implements StrategyCommission {

	@Override
	public int calculateCommission(int ownerAge) {
		// Not necessary in this Strategy
		return 0;
	}

	@Override
	public float calculateCommission(float interest, float quantity) {
		return quantity * interest;
	}




}
