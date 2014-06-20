package es.unileon.ulebank.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.service.ClientManager;

@Controller
public class ClientListController {
	@Autowired
	private ClientManager clientManager;
	
	@RequestMapping (value = "/clientlist.htm")
	public ModelAndView handleRequest (HttpServletRequest request, HttpServletResponse response) {
		String office = request.getParameter("office");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clients", this.clientManager.getClients(office));
		return new ModelAndView("clientlist", "model", map);
	}
}
