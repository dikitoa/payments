package es.unileon.ulebank.googlefinances;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.json.simple.parser.ParseException;

/**
 * EnterpriseData allows you to retrieves information giving a specific key.
 *
 * @author furiios
 */
public class EnterpriseData<T> {

    private final ArrayList<EnterpriseDataListener> listeners;
    private Map map;

    public EnterpriseData(Map map) {
        this.listeners = new ArrayList<>();
        this.map = map;
    }

    /**
     * Retrieves the specific data.
     *
     * @param key The key to find.<br />
     * <br />(Integer)<br />id ()<br />
     * <br />(Double)<br />l_fix ()<br />c_fix ()<br />cp_fix ()<br />pcls_fix
     * ()<br />el_fix ()<br />ec_fix ()<br />ecp_fix ()<br />
     * <br />(String)<br />t ()<br />e ()<br />l ()<br />l_cur ()<br />s ()<br
     * />ltt ()<br />lt ()<br />c ()<br />cp ()<br />ccol ()<br />
     * el ()<br />el_cur ()<br />elt ()<br />ec ()<br />ecp ()<br />eccol ()<br
     * />div ()<br />yld ()<br />eo ()<br />delay ()<br />
     * op ()<br />hi ()<br />lo ()<br />vo ()<br />avvo ()<br />hi52 ()<br
     * />lo52 ()<br />mc ()<br />pe ()<br />fwpe ()<br />beta ()<br />
     * eps ()<br />shares ()<br />inst_own ()<br />name ()<br />type ()<br />
     * @return T (Generic Type: Integer || Double || String)
     * @throws ElementNotFoundException
     */
    public T getValue(String key) throws ElementNotFoundException {
        T data;
        if (!map.containsKey(key) || map.get(key).equals("")) {
            throw new ElementNotFoundException(key);
        }

        String value = map.get(key).toString();
        if (key.contains("_fix")) {
            data = (T) new Double(Double.parseDouble(value));
        } else if (key.equals("id")) {
            data = (T) new Long(Long.parseLong(value));
        } else {
            data = (T) value;
        }
        return data;
    }

    public void refresh() throws IOException, ParseException, ElementNotFoundException {
        this.map = GoogleFinancesApi.getInstance().searchToParsedMap((String) this.getValue("t"));
        callListeners();
    }

    public void addListener(EnterpriseDataListener listener) {
        GoogleFinancesApi.getInstance().addEnterprise(listener.getEnterpriseData());
        listener.start();
        this.listeners.add(listener);
    }

    public void removeListener(EnterpriseDataListener listener) throws ElementNotFoundException {
        listener.pause();
        this.listeners.remove(listener);

        if (this.listeners.isEmpty()) {
            GoogleFinancesApi.getInstance().removeEnterprise(this);
        }
    }

    private void callListeners() {
        for (EnterpriseDataListener listener : this.listeners) {
            listener.exec();
        }
    }

    @Override
    public boolean equals(Object object) {
        try {
            return object != null && ((Long) this.getValue("id")).equals((Long) (((EnterpriseData) object).getValue("id")));
        } catch (ElementNotFoundException ex) {
            System.out.println(ex);
            return false;
        }
    }
}
