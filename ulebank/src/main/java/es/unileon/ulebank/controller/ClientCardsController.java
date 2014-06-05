package es.unileon.ulebank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.service.CardManager;

/**
 * @author Israel
 * Controlador de la vista principal de Metodos de Pago en la que se muestran las tarjetas 
 * del cliente que se ha dado previamente de alta y desde la cual puede realizar modificaciones
 * en los datos de sus tarjetas o crear una nueva tarjeta
 */
@Controller
public class ClientCardsController {
	@Autowired
	private CardManager cardManager;
	
	/**
	 * Envia los datos a la vista para mostrar la lista de tarjetas del cliente actual
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping (value = "/cards.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		//TODO pasar un cliente para la busqueda de tarjetas
//		List<Card> cards = cardManager.getCards();
		
//		map.put("cards", cards);
		return new ModelAndView("cards", "model", map);
	}
	
	/**
	 * Cambia el manager de la tarjeta por el que se indica
	 * @param cardManager
	 */
	public void setCardManager(CardManager cardManager) {
		this.cardManager = cardManager;
	}
}
