package es.unileon.ulebank.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.unileon.ulebank.command.ModifyPinCommand;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.exceptions.PaymentException;
import es.unileon.ulebank.service.CardManager;
import es.unileon.ulebank.service.ModifyPin;
import es.unileon.ulebank.validator.PinValidator;

/**
 * 
 * @author Alvaro
 * @brief Clase ue controla las peticiones que recibe priceincrease.htm 
 * vista que tiene el formaulario para cambiar el pin de la tarjeta
 *
 */
@Controller
@RequestMapping(value="/priceincrease.htm")
public class ModifyPinFormController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    /**
     * Validador para el formilario del cambio de PIN
     */
    /*@Autowired*/
    private PinValidator pinValidator;
    
    /**
     * Tarjeta a la que le cambiamos el pin
     */
    private Card card;

    /**
     * Manejador de las tarjetas
     */
    @Autowired
    private CardManager cardManager;

    /**
     * Metodo que obtiene los datos del formulario del cambio de pin y ejecuta el comando necesario
     * @param modifyPin
     * @param result
     * @return
     * @throws IOException
     * @throws PaymentException
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("card") ModifyPin modifyPin, BindingResult result) throws IOException, PaymentException
    {
//    	modifyPin.setNewPin(card.getPin());
//    	System.out.println("PIN QUE QUIERO VER ANTES COMMAND->> "+card.getPin());
    	this.pinValidator.validate(modifyPin, result);
        if (result.hasErrors()) {
//        	return "priceincrease";
        	return new ModelAndView("priceincrease", "card", modifyPin);
        }
  
        ModifyPinCommand command = new ModifyPinCommand(card, modifyPin.getNewPin());
    	try {
			command.execute();
//			System.out.println("PIN QUE QUIERO VER DESPUES COMMAND ->> "+card.getPin());
			cardManager.modifyPin(card);
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
//        String newPin = modifyPin.getNewPin();
//        cardManager.modifyPin(newPin);
//        logger.info("Modificando el pin a " + newPin);
//        Map<String, Object> myModel = new HashMap<String, Object>();
//        myModel.put("cardNumber", this.card.getCardNumber());
//        myModel.put("pin", newPin);
//        myModel.put("expirationDate", this.card.getExpirationDate());
//        myModel.put("buyLimitDiary",this.card.getBuyLimitDiary());

        return new ModelAndView("hello", "card", card);
//        return "redirect:/hello.htm";
//        return new ModelAndView("hello");
    }

    /**
     * Metodo que devuelve el modelo y la vista del formulario que modifca el pin
     * @param request
     * @return
     * @throws ServletException
     */
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView formBackingObject(HttpServletRequest request) throws ServletException {
        ModifyPin modifyPin = new ModifyPin();
        Card card = cardManager.getCard();
 
        modifyPin.setNewPin(card.getPin());
        return new ModelAndView("priceincrease", "card", modifyPin );
    }

    /**
     * Metodo que establece el manejador de las tarjetas
     * @param cardManager
     */
    public void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    /**
     * Metodo que devuelve el manager de las tarjetas
     * @return
     */
    public CardManager getCardManager() {
        return cardManager;
    }
    
    /**
     * Setter de la tarjeta
     * @param card
     */
    public void setCard(Card card){
    	this.card = card;
    }
    
    /**
     * Método que establece el validador del pin
     * @param pinValidator
     */
    public void setPinValidator(PinValidator pinValidator){
    	this.pinValidator = pinValidator;
    }

}
