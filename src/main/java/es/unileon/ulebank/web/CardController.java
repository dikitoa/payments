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

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.service.CardManager;

/**
 * Controlador de las tarjetas
 * @author Isra
 * @date 16/06/2014
 * @brief Clase que controla las peticiones que llegan a la direccion hello.htm
 * vista que muestra los datos de la tarjeta.
 */
@Controller
public class CardController {
	/**
	 * Logger de la clase
	 */
    protected final Log logger = LogFactory.getLog(getClass());
    /**
     * Objeto tarjeta del que se modificaran los datos
     */
    private Card card;
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
        myModel.put("card", this.cardManager.getCard());

        return new ModelAndView("hello", "model", myModel);
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
    public void setCard(Card card) {
        this.card = card;
    }
    
    /**
     * Metodo que obtiene la tarjeta del controlador
     * @return tarjeta
     */
    public Card getCard(){
    	return this.card;
    }
}
