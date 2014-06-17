package es.unileon.ulebank.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.client.Person;
import es.unileon.ulebank.command.NewCardCommand;
import es.unileon.ulebank.domain.Accounts;
import es.unileon.ulebank.domain.CardBean;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.handler.MalformedHandlerException;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.service.AccountManager;
import es.unileon.ulebank.service.CardManager;
import es.unileon.ulebank.service.ClientManager;
import es.unileon.ulebank.utils.CardProperties;
import es.unileon.ulebank.validator.CreateCardValidator;

/**
 * @author Israel
 * Controlador del formulario de creacion de la tarjeta que gestiona las peticiones GET y POST
 */
@Controller
@RequestMapping("/createcard.htm")
public class CreateCardController {
	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(CreateCardController.class.getName());
	/**
	 * Manejador de las operaciones de la tarjeta
	 */
	@Autowired
	private CardManager cardManager;
	
	@Autowired
	private ClientManager clientManager;
	
	@Autowired
	private AccountManager accountManager;
	
	@Autowired
	private CreateCardValidator validator;

	/**
	 * Gestiona las peticiones POST del formulario de creacion de la tarjeta y recibe
	 * los datos introducidos en un objeto CardBean
	 * @param bean
	 * @param result
	 * @param status
	 * @return
	 * @throws CommandException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(@ModelAttribute("newCard") CardBean bean, BindingResult result, SessionStatus status) throws CommandException {
		this.validator.validate(bean, result);
		List<Cards> cards = new ArrayList<Cards>();
		
		if (result.hasErrors()) {
			return new ModelAndView("createcard", "newCard", bean);
		}
		
		try {
			//We need a client to create card
			Person client = new Person(71557005, 'A');
			client.setName("Pepito");
			client.setSurnames("Garcia Martinez");
			Accounts account = this.accountManager.search(bean.getAccountNumber());
			
			NewCardCommand command = new NewCardCommand(client, account, bean, cards);
			command.execute();
			cardManager.saveCard(cards.get(0));
		} catch (MalformedHandlerException e1) {
			LOG.log(Level.SEVERE, e1.getMessage());
		}
		
		return new ModelAndView("result", "card", cards.get(0));
	}

	/**
	 * Gestiona las peticiones GET del formulario de creacion de la tarjeta
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public CardBean initForm(ModelMap model, HttpServletRequest request) {
		CardBean bean = new CardBean();
		bean.setAccountNumber("1234567890");
		bean.setBuyLimitDiary(CardProperties.getBuyLimitDiary());
		bean.setBuyLimitMonthly(CardProperties.getBuyLimitMonthly());
		bean.setCashLimitDiary(CardProperties.getCashLimitDiary());
		bean.setCashLimitMonthly(CardProperties.getCashLimitMonthly());
		System.out.println(request.getParameter("dni"));
		bean.setDni(request.getParameter("dni"));
		model.addAttribute("newCard", bean);
		return bean;
	}

	/**
	 * Devuelve un HashMap con los tipos de tarjeta disponibles
	 * @return
	 */
	@ModelAttribute("cardType")
	public Map<String, String> populateCardType() {
		Map<String, String> types = new LinkedHashMap<String, String>();
		types.put("Debit", CardType.DEBIT.toString());
		types.put("Credit", CardType.CREDIT.toString());
		return types;
	}

	/**
	 * Cambia el card manager por el recibido
	 * @param cardManager
	 */
	public void setCardManager(CardManager cardManager) {
		this.cardManager = cardManager;
	}
}
