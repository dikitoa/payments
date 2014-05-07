package es.unileon.ulebank.brokerage.buyable;

import es.unileon.ulebank.Employee;
import es.unileon.ulebank.brokerage.pack.StockPack;
import es.unileon.ulebank.handler.Handler;
import java.util.Date;

public class Enterprise extends Buyable {

    public Enterprise(Handler id, int amount, double totalPrice) throws InvalidBuyableException {
        super(id, amount, totalPrice);
    }

    @Override
    public StockPack buy(int amount, Employee operator) throws NotEnoughParticipationsException {
        if (amount > (this.amount - this.purchasedAmount)) {
            throw new NotEnoughParticipationsException();
        }

        this.purchasedAmount += amount;

        return new StockPack(this, amount, this.getPPP(), new Date());
    }
}
