package es.unileon.ulebank.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.client.Person;
import es.unileon.ulebank.service.ClientManager;
import es.unileon.ulebank.service.Searcher;

@Controller
@RequestMapping("/searchclient.htm")
public class SearchClientController {
	
	@Autowired
	private ClientManager clientManager;
	
	private String office;
	
	@RequestMapping (method = RequestMethod.POST)
	public ModelAndView submit(@Valid Searcher searcher, BindingResult result) {
		String id = searcher.getId();
		
		if (id.equals("") || id.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("clients", this.clientManager.getClients(office));
			
			return new ModelAndView("clientlist", "model", map);
		} else {
			Person person = (Person) this.clientManager.searchClient(id);
			
			if (person != null) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("client", person);
				return new ModelAndView("client", "model", model);
			} else {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("clients", this.clientManager.getClients(office));
				return new ModelAndView("clientlist", "model", model);
			}
		}
	}
	
	@RequestMapping (method = RequestMethod.GET)
	public Searcher handleRequest (HttpServletRequest request, HttpServletResponse response) {
		this.office = request.getParameter("office");
		Searcher searcher = new Searcher();
		searcher.setId("Enter client NIF");
		return searcher;
	}
}
