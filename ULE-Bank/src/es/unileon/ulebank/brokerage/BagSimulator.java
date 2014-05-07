package es.unileon.ulebank.brokerage;


import es.unileon.ulebank.brokerage.buyable.Enterprise;
import es.unileon.ulebank.brokerage.buyable.InvalidBuyableException;
import es.unileon.ulebank.exceptions.MalformedHandlerException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.ShareHandler;

import java.util.ArrayList;
import java.util.Iterator;

public class BagSimulator {
    
    private static ArrayList<Enterprise> hardcodedEnterprises;
    private static BagSimulator instance = null;
    
    private BagSimulator() throws InvalidBuyableException {
        BagSimulator.hardcodedEnterprises = new ArrayList<>();
        try {
            BagSimulator.hardcodedEnterprises.add(
                    new Enterprise(new ShareHandler("GOOG", "Google, Inc.", "Bag"), 300, 10000)
            );
            
            BagSimulator.hardcodedEnterprises.add(
                    new Enterprise(new ShareHandler("APPL", "Apple, Inc.", "Bag"), 1, 10000)
            );
            
            BagSimulator.hardcodedEnterprises.add(
                    new Enterprise(new ShareHandler("FURY", "Fury Bird, Inc.", "Bag"), 1000, 2)
            );
        } catch (MalformedHandlerException ex) {
            // TODO something
        }
    }
    
    public BagSimulator getInstance() throws InvalidBuyableException {
        if (BagSimulator.instance == null) {
            BagSimulator.instance = new BagSimulator();
        }
        
        return BagSimulator.instance;
    }
    
    /**
     * Returns a enterprise matching the given ID. If its not found, a default one will be created with that ID.
     * @param enterpriseID
     * @return a enterprise matching the given ID. If its not found, a default one will be created with that ID.
     * @throws es.unileon.ulebank.brokerage.buyable.InvalidBuyableException
     */
    public Enterprise getEnterpriseHandler(Handler enterpriseID) throws InvalidBuyableException {
        Enterprise wanted = new Enterprise(enterpriseID, 0, 0);
        
        if (!BagSimulator.hardcodedEnterprises.isEmpty()) {
            Iterator<Enterprise> it = BagSimulator.hardcodedEnterprises.iterator();
            Enterprise e;
            boolean found = false;
            
            do {
                e = it.next();
                if (e.getId().compareTo(enterpriseID) != 0) {
                    found = true;
                    wanted = e;
                }
            } while (it.hasNext() && !found);
        }
        
        return wanted;
    }
}
