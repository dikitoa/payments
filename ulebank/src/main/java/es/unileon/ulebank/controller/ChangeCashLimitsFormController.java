package es.unileon.ulebank.controller;

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

import es.unileon.ulebank.service.CardManager;
import es.unileon.ulebank.service.ChangeLimit;

/**
 * Class Controller of the page cashLimits.jsp
 * @author Rober dCR
 * @date 10/5/2014
 * @brief Concrete controller of cashLimits.jsp which change the cash limits of the card in.
 */
@Controller
@RequestMapping(value="/cashLimits.htm")
public class ChangeCashLimitsFormController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Card which change the limits in
     */
    @Autowired
    private CardManager productManager;

    /**
     * Method that obtains the data of the form in cashLimits.jsp and save the changes in the card
     * @param changeLimit
     * @param result
     * @return
     * @throws Exception 
     */
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid ChangeLimit changeLimit, BindingResult result) throws Exception
    {
        if (result.hasErrors()) {
            return "cashLimits";
        }
		
        int diaryLimit = (int) changeLimit.getDiaryLimit();
        int monthlyLimit = (int) changeLimit.getMonthlyLimit();
        logger.info("Modified diary limit: " + diaryLimit + "�.");
        logger.info("Modified monthly limit: " + monthlyLimit + "�.");
      //TODO agnadir cliente
//        productManager.changeCashLimits(diaryLimit, monthlyLimit);

        return "redirect:/cards.htm";
    }

    /**
     * Method that sends the data of the card's cash limits to the form in cashLimits.jsp
     * @param request
     * @return
     * @throws ServletException
     */
    @RequestMapping(method = RequestMethod.GET)
    protected ChangeLimit formBackingObject(HttpServletRequest request) throws ServletException {
        ChangeLimit changeLimit = new ChangeLimit();
        //TODO buscar tarjetas por el duegno
//        changeLimit.setDiaryLimit((int) this.productManager.getCards().get(0).getCashLimitDiary());
//        changeLimit.setMonthlyLimit((int) this.productManager.getCards().get(0).getCashLimitMonthly());
        return changeLimit;
    }

    /**
     * Setter of the card
     * @param productManager
     */
    public void setProductManager(CardManager productManager) {
        this.productManager = productManager;
    }

    /**
     * Getter of the card
     * @return
     */
    public CardManager getProductManager() {
        return productManager;
    }

}