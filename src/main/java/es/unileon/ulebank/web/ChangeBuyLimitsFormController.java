package es.unileon.ulebank.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.unileon.ulebank.command.Command;
import es.unileon.ulebank.command.ModifyBuyLimitCommand;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.service.CardManager;
import es.unileon.ulebank.service.ChangeLimit;

/**
 * Class Controller of the page buyLimits.jsp
 * @author Rober dCR
 * @date 10/5/2014
 * @brief Concrete controller of buyLimits.jsp which change the buy limits of the card in.
 */
@Controller
@RequestMapping(value="/buyLimits.htm")
public class ChangeBuyLimitsFormController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Manejador de las tarjetas
     */
    @Autowired
    private CardManager cardManager;
    
    private Handler cardId;

    /**
     * Method that obtains the data of the form in buyLimits.jsp and save the changes in the card
     * @param changeLimit
     * @param result
     * @return
     * @throws Exception 
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid ChangeLimit changeLimit, BindingResult result) throws Exception
    {
        if (result.hasErrors()) {
            return "buyLimits";
        }
		
        int diaryLimit = (int) changeLimit.getDiaryLimit();
        int monthlyLimit = (int) changeLimit.getMonthlyLimit();
        logger.info("Modified diary limit: " + diaryLimit + "Euros.");
        logger.info("Modified monthly limit: " + monthlyLimit + "Euros.");
        
        Command buyLimitsDiary = new ModifyBuyLimitCommand(cardId, this.cardManager.findCard(cardId.toString()), diaryLimit, "diary");
		Command buyLimitsMonthly = new ModifyBuyLimitCommand(cardId, this.cardManager.findCard(cardId.toString()), monthlyLimit, "monthly");
		buyLimitsMonthly.execute();
		buyLimitsDiary.execute();

        return "redirect:/changeLimits.htm";
    }

    /**
     * Method that sends the data of the card's buy limits to the form in buyLimits.jsp
     * @param request
     * @return
     * @throws ServletException
     */
    @RequestMapping(method = RequestMethod.GET)
    protected ChangeLimit formBackingObject(HttpServletRequest request) throws ServletException {
    	request.getAttribute("cardId");
        ChangeLimit changeLimit = new ChangeLimit();
        changeLimit.setDiaryLimit((int) this.cardManager.findCard(cardId.toString()).getBuyLimitDiary());
        changeLimit.setMonthlyLimit((int) this.cardManager.findCard(cardId.toString()).getBuyLimitMonthly());
        return changeLimit;
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