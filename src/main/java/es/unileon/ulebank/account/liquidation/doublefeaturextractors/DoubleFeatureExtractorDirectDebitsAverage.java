package es.unileon.ulebank.account.liquidation.doublefeaturextractors;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.liquidation.AbstractFeatureExtractor;
import es.unileon.ulebank.command.CommandFilterTransactionByDates;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.history.DirectDebitTransaction;
import es.unileon.ulebank.history.conditions.WrongArgsException;

public class DoubleFeatureExtractorDirectDebitsAverage implements
        AbstractFeatureExtractor<Double> {
    private double value;
    private static final Logger LOG = Logger
            .getLogger(DoubleFeatureExtractorDirectDebitsAverage.class);

    @Override
    public String getFeatureName() {
        return "importe medio pagos domiciliados";
    }

    @Override
    public void updateFeature(Account account, Date min, Date max) {
        double sum = 0;
        int count = 0;
        List<DirectDebitTransaction> list = new ArrayList<DirectDebitTransaction>();
        try {
            final Iterator<DirectDebitTransaction> it = account
                    .getDirectDebitHistory().getIterator();
            final CommandFilterTransactionByDates<DirectDebitTransaction> command = new CommandFilterTransactionByDates<DirectDebitTransaction>(
                    min, max, it, new GenericHandler(""));
            command.execute();
            list = command.getList();
        } catch (final WrongArgsException e) {
            DoubleFeatureExtractorDirectDebitsAverage.LOG.error(e);
        }
        for (final DirectDebitTransaction actual : list) {
            if (actual.getAmount() < 0) {
                sum += actual.getAmount();
                ++count;
            }
        }
        this.value = sum / count;
    }

    @Override
    public Double getFeature() {
        return this.value;
    }

    @Override
    public Double generateRandomFeature() {
        return new Random().nextDouble() * 4;
    }
}
