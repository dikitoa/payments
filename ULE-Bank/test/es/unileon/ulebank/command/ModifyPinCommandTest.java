package es.unileon.ulebank.command;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.payments.Account;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.Client;
import es.unileon.ulebank.payments.DebitCard;
import es.unileon.ulebank.strategy.StrategyCommission;
import es.unileon.ulebank.strategy.StrategyCommissionDebitEmission;
import es.unileon.ulebank.strategy.StrategyCommissionDebitMaintenance;
import es.unileon.ulebank.strategy.StrategyCommissionDebitRenovate;

public class ModifyPinCommandTest {
	private CardHandler handler;
	private Client client;
	private Account account;
	private Card card;
	private ModifyPinCommand test;
	private String newPin;
	
	@Before
	public void setUp() {
		handler = new CardHandler();
		client = new Client();
		account = new Account();
		StrategyCommission commissionEmission = new StrategyCommissionDebitEmission(client, card, 25);
		StrategyCommission commissionMaintenance = new StrategyCommissionDebitMaintenance(client, card, 0);
		StrategyCommission commissionRenovate = new StrategyCommissionDebitRenovate(client, card, 0);
		this.card = new DebitCard(handler, client, account, 400.0, 1000.0, 400.0, 1000.0, commissionEmission, commissionMaintenance, commissionRenovate, 0);
		this.account.addCard(card);
		try {
			this.card.setPin("1234");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.newPin = "9876";
	}
	
	@Test (expected = NullPointerException.class)
	public void testCommandNull() {
		this.test = null;
		this.test.getId();
	}
	
	@Test
	public void testModifyPinOk() {
		test = new ModifyPinCommand(handler, account, newPin);
		assertEquals("1234", card.getPin());
		test.execute();
		assertEquals("9876", card.getPin());
	}
	
	@Test
	public void testModifyPinFail() {
		test = new ModifyPinCommand(handler, account, "4s22");
		test.execute();
		assertEquals("1234", card.getPin());
		test = new ModifyPinCommand(handler, account, "34433");
		test.execute();
		assertEquals("1234", card.getPin());
	}
	
	@Test
	public void testUndoMofifyPinOk() {
		test = new ModifyPinCommand(handler, account, newPin);
		assertEquals("1234", card.getPin());
		test.execute();
		assertEquals("9876", card.getPin());
		test.undo();
		assertEquals("1234", card.getPin());
	}
	
	@Test
	public void testRedoModifyPinOk() {
		test = new ModifyPinCommand(handler, account, newPin);
		assertEquals("1234", card.getPin());
		test.execute();
		assertEquals("9876", card.getPin());
		test.undo();
		assertEquals("1234", card.getPin());
		test.redo();
		assertEquals("9876", card.getPin());
	}
}
