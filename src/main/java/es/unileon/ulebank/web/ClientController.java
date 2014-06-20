package es.unileon.ulebank.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.client.Person;
import es.unileon.ulebank.service.ClientManager;

@Controller
public class ClientController {
	
	@Autowired
	private ClientManager clientManager;
	
	@RequestMapping (value = "/client.htm", method = RequestMethod.GET)
	public ModelAndView handleRequest (HttpServletRequest request, HttpServletResponse response) {
		Person person = (Person) clientManager.searchClient(request.getParameter("dni"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dni", person.getId());
		map.put("name", person.getName());
		map.put("surnames", person.getSurnames());
		map.put("phoneNumber1", person.getPhoneNumber1());
		map.put("phoneNumber2", person.getPhoneNumber2());
		map.put("civilState", person.getCivilState());
		map.put("profession", person.getProfession());
		map.put("birthDate", person.getBirthDate());
		map.put("address", person.getAddress());
		return new ModelAndView("client", "client", map);
	}
}
