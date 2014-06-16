package es.unileon.ulebank.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.service.ClientManager;
import es.unileon.ulebank.service.SearchClient;

@Controller
public class SearchClientController {
	
	 /** Logger for this class and subclasses */
    private static final Log LOGGER = LogFactory
            .getLog(SearchClientController.class.getName());
    @Autowired
    private ClientManager clientManager;

    @RequestMapping(value = "/searchclient.htm", method = RequestMethod.POST)
    public ModelAndView onSubmit(@Valid SearchClient searcher,
            BindingResult result) {

        String id = searcher.getId();

        if (id.equals("") || id == null) {

            String now = (new Date()).toString();
            LOGGER.info("Returning officeslist view with " + now);

            Map<String, Object> myModel = new HashMap<String, Object>();
            myModel.put("now", now);
            myModel.put("clients", this.clientManager.getClients());

            return new ModelAndView("officeslist", "model", myModel);
        } else {
            LOGGER.info("Search office with id " + id + ".");

            Client client = this.clientManager.searchClient(id);
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("client", client);

            if (client != null) {
                return new ModelAndView("client", "model", model);
            } else {

                String now = (new Date()).toString();
                LOGGER.info("Returning officeslist view with " + now);

                Map<String, Object> myModel = new HashMap<String, Object>();
                myModel.put("now", now);
                myModel.put("clients", this.clientManager.getClients());

                return new ModelAndView("clientlist", "model", myModel);
            }

        }
    }

    @RequestMapping(value = "/searchclient.htm", method = RequestMethod.GET)
    public SearchClient formBackingObject(HttpServletRequest request)
            throws ServletException {
        SearchClient searcher = new SearchClient();
        searcher.setId("Enter client id");
        return searcher;
    }
}
