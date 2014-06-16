package es.unileon.ulebank.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.command.NewCardCommand;
import es.unileon.ulebank.domain.CardBean;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CardType;
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
	 * Validador del formulario de creacion de la tarjeta
	 */
	private CreateCardValidator cardValidator;
	/**
	 * Cuenta a la que esta asociada la nueva tarjeta
	 */
	@Autowired
	private Account account;
	/**
	 * Oficina actual en la que actuamos
	 */
	@Autowired
	private Office office;
	/**
	 * Cliente duegno de la tarjeta
	 */
	@Autowired
	private Client client;

	/**
	 * Crea un nuevo controlador recibiendo por parametros el validador de la clase y las propiedades
	 * de la tarjeta
	 * @param cardValidator
	 * @param properties
	 */
	@Autowired
	public CreateCardController(CreateCardValidator cardValidator) {
		this.cardValidator = cardValidator;
	}

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
	public ModelAndView submit(@ModelAttribute("newCard") CardBean bean, BindingResult result, SessionStatus status) throws CommandException
	{
		cardValidator.validate(bean, result);
		if (result.hasErrors()) {
			return new ModelAndView("createcard", "newCard", bean);
		}
		this.office.addClient(this.client);
		this.client.add(this.account);
		List<Card> cards = new ArrayList<Card>();
		NewCardCommand command = new NewCardCommand(this.office, this.client.getId().toString(), this.account.getID().toString(), bean.getCardType(), bean, cards);
		try {
			command.execute();
		} catch (CommandException e) {
			throw new CommandException(e.getMessage());
		}
		
		
		return new ModelAndView("result", "card", cards.get(0));
	}

	/**
	 * Gestiona las peticiones GET del formulario de creacion de la tarjeta
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public CardBean initForm(ModelMap model) {
		CardBean bean = new CardBean();

		bean.setBuyLimitDiary(CardProperties.getBuyLimitDiary());
		bean.setBuyLimitMonthly(CardProperties.getBuyLimitMonthly());
		bean.setCashLimitDiary(CardProperties.getCashLimitDiary());
		bean.setCashLimitMonthly(CardProperties.getCashLimitMonthly());
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
}