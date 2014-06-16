package es.unileon.ulebank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.unileon.ulebank.command.ChangeFeeCommand;
import es.unileon.ulebank.command.Command;
import es.unileon.ulebank.service.CardManager;
import es.unileon.ulebank.service.FeeChange;

/**
 * 
 * @author David Gomez Riesgo
 * @brief Clase que controla las peticiones que recibe la direccion feechange.htm
 */
@Controller
@RequestMapping(value="/feechange.htm")	
public class ChangeFeesFormController {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Card which change the limits in
	 */
	@Autowired
	private CardManager cardManager;

	/**
	 * Method that obtains the data of the form in buyLimits.jsp and save the changes in the card
	 * @param feeChange
	 * @param result
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@Valid FeeChange feeChange, BindingResult result) throws Exception
	{
		if (result.hasErrors()) {
			return "feechange";
		}

		int change = (int) feeChange.getFeeChange();
		logger.info("Modified fee Change: " + feeChange + "Euros.");

		Command changeFee = new ChangeFeeCommand(this.cardManager.getCard().getId(), this.cardManager.getCard(), change, "change");
		changeFee.execute();

		return "redirect:/hello.htm";
	}

	/**
	 * Method that sends the data of the card's buy limits to the form in buyLimits.jsp
	 * @param request
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected FeeChange formBackingObject(HttpServletRequest request) throws ServletException {
		FeeChange feeChange = new FeeChange();
		// feeChange.setFeeChange(this.cardManager.getCard().getFee());
		return feeChange;
	}

	/**
	 * Setter of the card
	 * @param productManager
	 */
	public void setCardManager(CardManager productManager) {
		this.cardManager = productManager;
	}

	/**
	 * Getter of the card
	 * @return card
	 */
	public CardManager getCardManager() {
		return cardManager;
	}

}

