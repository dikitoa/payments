/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.bank;

import java.util.ArrayList;
import java.util.List;

import es.unileon.ulebank.domain.Offices;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.MalformedHandlerException;

/**
 *
 * @author runix
 */
public class Bank {

    private final List<Offices> offices;

    private final Handler bankID;

    /**
     *
     * @param bankID
     * @throws MalformedHandlerException
     */
    public Bank(Handler bankID) throws MalformedHandlerException {
        this.bankID = new BankHandler(bankID.toString());
        this.offices = new ArrayList<Offices>();
    }

    /**
     *
     * @return
     */
    public Handler getID() {
        return this.bankID;
    }

    /**
     *
     * @param office
     * @return
     */
    public boolean addOffice(Offices office) {
        if ((office != null)
                && (this.searchOffice(office.getOfficeId()) == null)) {
            return this.offices.add(office);
        }
        return false;
    }

    /**
     *
     * @param office
     * @return
     */
    public boolean removeOffice(String office) {
        boolean removed = false;
        if (office != null) {
            int i = -1;
            while ((++i < this.offices.size()) && !removed) {
                if (this.offices.get(i).getOfficeId().compareTo(office) == 0) {
                    this.offices.remove(i);
                    removed = true;
                }
            }
        }
        return removed;
    }

    public Offices searchOffice(String id) {
        Offices result = null;
        int i = -1;
        while ((++i < this.offices.size()) && (result == null)) {
            if (this.offices.get(i).getOfficeId().compareTo(id) == 0) {
                result = this.offices.get(i);
            }
        }

        return result;
    }
}
