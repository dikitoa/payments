package es.unileon.ulebank.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SearchClient {
	/** Logger for this class and subclasses */
    private static final Log LOGGER = LogFactory.getLog(SearchClient.class
            .getName());

    /**
     * The id
     */
    private String id;

    /**
     * Returns the id
     * 
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the id
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
        LOGGER.info("Id set to " + id);
    }
}
