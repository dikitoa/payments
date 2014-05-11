package es.unileon.ulebank.brokerage;

import es.unileon.ulebank.users.Employee;
import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.exception.BalanceException;
import es.unileon.ulebank.brokerage.buyable.Enterprise;
import es.unileon.ulebank.brokerage.buyable.InvalidBuyableException;
import es.unileon.ulebank.brokerage.buyable.InvestmentFund;
import es.unileon.ulebank.brokerage.buyable.NotEnoughParticipationsException;
import es.unileon.ulebank.brokerage.pack.InvestmentFundPack;
import es.unileon.ulebank.brokerage.pack.Pack;
import es.unileon.ulebank.brokerage.pack.PackTransaction;
import es.unileon.ulebank.brokerage.pack.StockPack;
import es.unileon.ulebank.fees.DefaultFeeProvider;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.fees.FeeTransaction;
import es.unileon.ulebank.history.History;
import es.unileon.ulebank.history.TransactionException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvestmentAccount {

    private final ArrayList<Pack> stockPacks;
    private final ArrayList<Pack> fundPacks;
    private final History<PackTransaction> history;
    private final FeeStrategy buyStockageFee, sellStockageFee;

    public InvestmentAccount() {
        this.stockPacks = new ArrayList<>();
        this.fundPacks = new ArrayList<>();

        this.history = new History<>();

        this.buyStockageFee = DefaultFeeProvider.getInstance().getDefaultFee();
        this.sellStockageFee = DefaultFeeProvider.getInstance().getDefaultFee();
    }

    /**
     * Builds a new StockPack, generates the transaction and adds it to the
     * history and withraws the money from the buyer's account.
     *
     * @param e
     * @param acc Account where the amount will be charged.
     * @param amount Amount of stockage to buy
     * @param operator Bank operator which performs the transaction (the one who
     * is logged in probably).
     * @throws es.unileon.ulebank.brokerage.buyable.InvalidBuyableException
     * @throws es.unileon.ulebank.account.exception.BalanceException
     * @throws
     * es.unileon.ulebank.brokerage.buyable.NotEnoughParticipationsException
     * @throws es.unileon.ulebank.history.TransactionException
     */
    public void buyStockage(Enterprise e, Account acc, int amount, Employee operator) throws InvalidBuyableException, BalanceException, NotEnoughParticipationsException, es.unileon.ulebank.history.TransactionException {
        StockPack p = e.buy(amount, operator);
        p.setAccount(acc);
        stockPacks.add(p);

        double price = amount * e.getPPP();
        PackTransaction transaction = new PackTransaction(price, new Date(), "Compra de acciones", p, operator);
        FeeTransaction feeTransaction = new FeeTransaction(this.sellStockageFee.getFee(price), new Date(), transaction);

        try {
            p.getAccount().doWithdrawal(transaction);
            p.getAccount().doWithdrawal(feeTransaction);
        } catch (TransactionException ex) {
            // TODO: Wtf is TransactionException.
            Logger.getLogger(InvestmentAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.history.add(transaction);
    }

    public void sellStockage(StockPack p, int amount, Employee operator) throws NotEnoughParticipationsException, es.unileon.ulebank.history.TransactionException {
        if (p.getAmount() < amount) {
            throw new NotEnoughParticipationsException();
        }

        p.setAmount(p.getAmount() - amount);

        double price = p.getProduct().getPPP() * amount;
        PackTransaction transaction = new PackTransaction(price, new Date(), "Venta de acciones", p, operator);
        FeeTransaction feeTransaction = new FeeTransaction(this.sellStockageFee.getFee(price), new Date(), transaction);

        try {
            p.getAccount().doDeposit(transaction);
            p.getAccount().doWithdrawal(feeTransaction);
        } catch (TransactionException ex) {
            // TODO: Wtf is TransactionException.
            Logger.getLogger(InvestmentAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.history.add(transaction);

        this.prunePacks();
    }

    public void buyInvestmentFund(InvestmentFund i, Account acc, int amount, Employee operator) throws InvalidBuyableException, BalanceException, NotEnoughParticipationsException, es.unileon.ulebank.history.TransactionException {
        InvestmentFundPack ifp = i.buy(amount, operator);
        fundPacks.add(ifp);
        double price = amount * i.getPPP();
        PackTransaction transaction = new PackTransaction(price, new Date(), "Compra de fondos", ifp, operator);
        FeeTransaction feeTransaction = new FeeTransaction(i.getFee().getFee(price), new Date(), transaction);

        try {
            acc.doWithdrawal(transaction);
            acc.doWithdrawal(feeTransaction);
        } catch (TransactionException ex) {
            // TODO: Wtf is TransactionException.
            Logger.getLogger(InvestmentAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.history.add(transaction);
    }

    /**
     * @return the fundHistory
     */
    public History<PackTransaction> getHistory() {
        return history;
    }

    private void prunePacks() {
        // TODO: Delete 0-participations packs.
    }
}
