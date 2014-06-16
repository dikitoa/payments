package es.unileon.ulebank.web;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.command.NewCardCommand;
import es.unileon.ulebank.domain.CardBean;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.domain.Offices;
import es.unileon.ulebank.exceptions.CommandException;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.service.CardManager;
import es.unileon.ulebank.utils.CardProperties;

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
	public ModelAndView submit(@Valid CardBean bean, BindingResult result, SessionStatus status) throws CommandException {

		if (result.hasErrors()) {
			return new ModelAndView("createcard", "newCard", bean);
		}
		
		Offices office = bean.getOffice();
		Cards card = null;
		NewCardCommand command = new NewCardCommand(office, bean.getDni(), bean.getAccountNumber(), bean.getCardType(), bean.getCardNumber(), card);
		try {
			command.execute();
		} catch (NumberFormatException e) {
			LOG.info(e.getMessage());
		} 

		return new ModelAndView("result", "card", card);
	}

	/**
	 * Gestiona las peticiones GET del formulario de creacion de la tarjeta
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		CardBean bean = new CardBean();

		bean.setBuyLimitDiary(CardProperties.getBuyLimitDiary());
		bean.setBuyLimitMonthly(CardProperties.getBuyLimitMonthly());
		bean.setCashLimitDiary(CardProperties.getCashLimitDiary());
		bean.setCashLimitMonthly(CardProperties.getCashLimitMonthly());
		model.addAttribute("newCard", bean);

		return "createcard";
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
