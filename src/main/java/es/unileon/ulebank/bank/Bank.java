/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.bank;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.unileon.ulebank.exceptions.MalformedHandlerException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.transactionManager.TransactionManager;

/**
 *
 * @author runix
 */
public class Bank {

    private static final Logger LOG = Logger.getLogger(Bank.class.getName());

    private final List<Office> offices;

    private final String bankID;

    private final TransactionManager manager;

    /**
     *
     * @param manager
     * @param bankID
     * @throws MalformedHandlerException
     */
    public Bank(TransactionManager manager, String bankID) throws MalformedHandlerException {
        this.bankID = bankID;
        this.offices = new ArrayList<>();
        this.manager = manager;
    }

    /**
     *
     * @return
     */
    public String getID() {
        return this.bankID;
    }

    /**
     *
     * @param office
     * @return
     */
    public boolean addOffice(Office office) {
        if (office != null) {
            for (int i = 0; i < offices.size(); ++i) {
                if (offices.get(i).getIdOffice().compareTo(office.getIdOffice()) == 0) {
                    return false;
                }
            }
            return this.offices.add(office);
        }
        return false;
    }

    /**
     *
     * @param office
     * @return
     */
    public boolean removeOffice(Handler office) {
        boolean removed = false;
        if (office != null) {
            for (int i = 0; i < offices.size() && !removed; ++i) {
                if (offices.get(i).getIdOffice().compareTo(office) == 0) {
                    this.offices.remove(i);
                    removed = true;
                }
            }
        }
        return removed;
    }

//    /**
//     * Forward transaction to the correct account or throw a exception
//     * otherwise.
//     *
//     * @param t ( Transaction to forward )
//     * @param destine ( The transaction destine )
//     *
//     * @throws es.unileon.ulebank.handler.MalformedHandlerException( If the
//     * destine isn't well-formed )
//     * @throws TransactionException ( If the transaction cannot be deliver )
//     */
//    public void doTransaction(Transaction t, Handler destine) throws MalformedHandlerException, TransactionException {
//        StringBuilder error = new StringBuilder();
//        if (t != null && destine != null) {
//            AccountHandler handler = new AccountHandler(destine);
//            Handler bank = handler.getBankHandler();
//            if (this.bankID.compareTo(bank) == 0) {
//                Handler office = handler.getOfficeHandler();
//                boolean found = false;
//                for (int i = 0; i < this.offices.size() && !found; i++) {
//                    if (this.offices.get(i).getIdOffice().compareTo(office) == 0) {
//                        found = true;
//                        this.offices.get(i).doTransaction(t, destine);
//                    }
//                }
//                if (!found) {
//                    error.append("Error, office not found\n");
//                }
//            } else {
//                this.manager.doTransaction(t, destine);
//            }
//        } else {
//            error.append(("The transaction cannot be null or destination be null"));
//        }
//
//        if (error.length() > 0) {
//            LOG.error("Bank id " + this.bankID + " error : " + error.toString());
//            throw new TransactionException(error.toString());
//        }
//    }
}
