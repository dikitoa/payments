package es.unileon.ulebank.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import es.unileon.ulebank.command.Command;
import es.unileon.ulebank.command.RenovateCardCommand;
import es.unileon.ulebank.domain.CardBean;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.service.CardManager;

/**
 * 
 * @author David Gomez Riesgo
 *
 */
@Controller
@RequestMapping ("/renovatecard.htm")
public class RenovateCardController {
	
	@Autowired
	private CardManager cardManager;
	
	private String id;
	
	@RequestMapping (method = RequestMethod.POST)
	public ModelAndView submit(@Valid CardBean bean, BindingResult result) throws Exception  {
		if (result.hasErrors()) {
			return new ModelAndView("renovatecard", "model", bean);
		}
		Cards card = this.cardManager.findCard(id);
		
		Command renovateCard = new RenovateCardCommand(card);
		renovateCard.execute();
		this.cardManager.saveCard(card);
		
		return new ModelAndView("success");
	}
	
	@RequestMapping (method = RequestMethod.GET)
	public CardBean initForm(HttpServletRequest request) {
		CardBean bean = new CardBean();
		this.id = request.getParameter("id");
		bean.setCardNumber(id);
		
		return bean;
	}
}
