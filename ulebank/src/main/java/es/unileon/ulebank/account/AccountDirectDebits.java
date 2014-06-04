/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.account;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author runix
 */
public class AccountDirectDebits {

	private List<DirectDebit> directDebits;

	public AccountDirectDebits() {
		this.directDebits = new ArrayList<DirectDebit>();
	}

	public boolean addDirectDebit(DirectDebit d) {
		int i = -1;
		boolean duplicated = false;
		while (++i < directDebits.size() && !duplicated) {
			if (directDebits.get(i).getDirectDebitID()
					.compareTo(d.getDirectDebitID()) == 0) {
				duplicated = true;
			}
		}
		if (!duplicated) {
			return this.directDebits.add(d);
		}
		return false;
	}
}