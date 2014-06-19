package es.unileon.ulebank.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.domain.CardBean;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.service.CardManager;
import es.unileon.ulebank.service.GenericHandlerManager;

@Controller
@RequestMapping ("/deletecard.htm")
public class DeleteCardController {
	
	@Autowired
	private CardManager cardManager;
	@Autowired
	private GenericHandlerManager handlerManager;
	
	private String id;
	
	@RequestMapping (method = RequestMethod.POST)
	public ModelAndView submit(@Valid CardBean bean, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("deletecard", "model", bean);
		}
		Cards card = this.cardManager.findCard(id);
		Handler handler = card.getGenericHandler();
		this.cardManager.removeCard(card);
		this.handlerManager.remove(handler);
		
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
