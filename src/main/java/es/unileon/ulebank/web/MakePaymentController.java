package es.unileon.ulebank.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.command.Command;
import es.unileon.ulebank.command.PaymentCommand;
import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.service.CardManager;
import es.unileon.ulebank.service.PaymentBean;

/**
 * 
 * @author Rober dCR
 *
 */
@Controller
@RequestMapping (value="/cardpayment.htm")
public class MakePaymentController {
	
    /**
     * Manejador de las tarjetas
     */
    @Autowired
    private CardManager cardManager;
    
    private Cards card;
    
    /**
     * Method that obtains the data of the form in cardpayment.jsp and save the changes in the card
     * @param form
     * @param result
     * @return
     * @throws Exception 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(@Valid PaymentBean form, BindingResult result) throws Exception
    {
        if (result.hasErrors()) {
            return new ModelAndView("cardpayments");
        }
        
        Command makePayment = new PaymentCommand(card, form.getAmount(), form.getConcept());
		makePayment.execute();
		
		this.cardManager.saveCard(card);
		
        return new ModelAndView("card", "card", card);
    }

    /**
     * Method that sends the data of the card's buy limits to the form in cardpayment.jsp
     * @param request
     * @return
     * @throws ServletException
     */
    @RequestMapping(method = RequestMethod.GET)
    protected PaymentBean formBackingObject(HttpServletRequest request) throws ServletException {
    	this.card = this.cardManager.findCard(request.getParameter("id"));
        PaymentBean form = new PaymentBean();
        form.setConcept("Insert concept here");
        form.setAmount(0.00);
        return form;
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
