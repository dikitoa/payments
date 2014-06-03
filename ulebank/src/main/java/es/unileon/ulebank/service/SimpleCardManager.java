package es.unileon.ulebank.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.command.Command;
import es.unileon.ulebank.command.ModifyBuyLimitCommand;
import es.unileon.ulebank.command.ModifyCashLimitCommand;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.repository.CardDao;

/**
 * @author Israel
 * Funciona como mediador entre el controlador y la clase que gestiona el almacenamiento
 */
@Component
public class SimpleCardManager implements CardManager {

	private static final long serialVersionUID = 1L;
	/**
	 * Atributo para realizar las operaciones de acceso a la base de datos
	 */
	@Autowired
	private CardDao cardDao;
	/**
	 * Guarda la ultima tarjeta agnadida a la base de datos
	 */
	private Card lastCard;
	
	/**
	 * Obtiene la lista de tarjetas de la base de datos
	 */
	@Override
	public List<Card> getCards() {
		return cardDao.getCardList();
	}

	/**
	 * Guarda la tarjeta recibida en la base de datos
	 */
	public void saveNewCard(Card card) {
		this.lastCard = card;
		this.cardDao.addCard(card);
	}
	
	/**
	 * Cambia el atributo de acceso al almacenamiento por el recibido
	 * @param cardDao
	 */
	public void setCardDao(CardDao cardDao) {
		this.cardDao = cardDao;
	}
	
	/**
	 * Devuelve el cliente que obtiene de la memoria o de la base de datos
	 */
	@Override
	public Client getClient(String dni) {
		return this.cardDao.getClient(dni);
	}
	
	/**
	 * Devuelve la cuenta que obtiene de la memoria o de la base de datos
	 */
	@Override
	public Account getAccount(String accountNumber) {
		return this.cardDao.getAccount(accountNumber);
	}

	/**
	 * Devuelve la ultima tarjeta almacenada en la memoria o base de datos
	 * @return
	 */
	public Card getLastCard() {
		return lastCard;
	}

	@Override
	public void changeBuyLimits(double diary, double monthly)
			throws Exception {
		Card card = this.cardDao.getCardList().get(0);
		Command buyLimitsDiary = new ModifyBuyLimitCommand(card.getId(), card, diary, "diary");
		Command buyLimitsMonthly = new ModifyBuyLimitCommand(card.getId(), card, monthly, "monthly");
		buyLimitsMonthly.execute();
		buyLimitsDiary.execute();
		this.cardDao.saveCard(card);
	}

	@Override
	public void changeCashLimits(double diary, double monthly)
			throws Exception {
		Card card = this.cardDao.getCardList().get(0);
		Command cashLimitsDiary = new ModifyCashLimitCommand(card.getId(), card, diary, "diary");
		Command cashLimitsMonthly = new ModifyCashLimitCommand(card.getId(), card, monthly, "monthly");
		cashLimitsMonthly.execute();
		cashLimitsDiary.execute();
		this.cardDao.saveCard(card);
	}

	public void setCard(Card card) {
		this.lastCard = card;
	}

}
