package es.unileon.ulebank.service;

import java.io.Serializable;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.exceptions.CardNotFoundException;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.history.TransactionException;
import es.unileon.ulebank.payments.Card;

/**
 * @author Israel
 * Gestiona las operaciones que se realizan con la tarjeta
 */
public interface CardManager extends Serializable {
	/**
	 * Devuelve la lista de tarjetas
	 * @return
	 */
	public List<Card> getCards();
	/**
	 * Guarda la tarjeta creada
	 */
	public void saveNewCard(Card card);
	/**
	 * Obtiene el cliente de la memoria o la base de datos
	 * @return
	 */
	public Client getClient(String dni);
	/**
	 * Obtiene la cuenta de la memoria o la base de datos
	 * @return
	 */
	public Account getAccount(String accountNumber);
	
	/**
	 * Method that changes the buy limits using the command with the limits from buyLimits.jsp
	 * @param diary
	 * @param monthly
	 * @throws IncorrectLimitException
	 * @throws AccountNotFoundException
	 * @throws PaymentException
	 * @throws TransactionException
	 * @throws CardNotFoundException
	 * @throws Exception 
	 */
    public void changeBuyLimits(double diary, double monthly) throws IncorrectLimitException, AccountNotFoundException, PaymentException, TransactionException, CardNotFoundException, Exception;
    
    /**
     * Method that changes the cash limits using the command with the limits from cashLimits.jsp
     * @param diary
     * @param monthly
     * @throws IncorrectLimitException
     * @throws AccountNotFoundException
     * @throws PaymentException
     * @throws TransactionException
     * @throws CardNotFoundException
     * @throws Exception 
     */
    public void changeCashLimits(double diary, double monthly) throws IncorrectLimitException, AccountNotFoundException, PaymentException, TransactionException, CardNotFoundException, Exception;
    
    public Card getLastCard();
}
