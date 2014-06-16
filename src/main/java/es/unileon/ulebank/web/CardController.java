package es.unileon.ulebank.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.domain.Cards;
import es.unileon.ulebank.service.CardManager;

@Controller
public class CardController {
	/**
	 * Logger de la clase
	 */
    protected final Log logger = LogFactory.getLog(getClass());
    /**
     * Objeto tarjeta del que se modificaran los datos
     */
    private Cards card;
    /**
     * Manejador de las tarjetas
     */
    @Autowired
    private CardManager cardManager;

    /**
     * Metodo que crea el modelo y vista para hello.htm
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value="/hello.htm", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	card = cardManager.getCard();

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("id", this.card.getId());
        myModel.put("pin", this.card.getPin());
        myModel.put("expirationDate", this.card.getExpirationDate());
        myModel.put("buyLimitDiary",this.card.getBuyLimitDiary());

        return new ModelAndView("hello", "card", myModel);
    }
    
    /**
     * Metodo que asigna el cardManager
     * @param cardManager
     */
    public void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }
    
    /**
     * Metodo que asigna a nuestra tarjeta una tarjeta pasada por parametro
     * @param card
     */
    public void setCard(Cards card) {
        this.card = card;
    }
    
    /**
     * Metodo que obtiene la tarjeta del controlador
     * @return tarjeta
     */
    public Cards getCard(){
    	return this.card;
    }
}
