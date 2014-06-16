package es.unileon.ulebank.service;

import java.util.List;

import es.unileon.ulebank.domain.Cards;

/**
 * Interface that provides Cards data access
 * @author isra
 */
public interface CardManager {
    /**
     * Save received Cards
     * @param Cards
     * @return
     */
    public boolean saveCard(Cards Cards);
    /**
     * Removes Cards with received String
     * @param cardId
     * @return
     */
    public boolean removeCard(Cards Cards);
    /**
     * Searches Cards with received ID
     * @param cardId
     * @return
     */
    public Cards findCard(String cardId);
    /**
     * Return Cards client list
     * @param dni
     * @return
     */
    public List<Cards> getCardClientList(String dni);
    /**
     * Return Cards account list
     * @param accountNumber
     * @return
     */
    public List<Cards> getCardAccountList(String accountNumber);
}
