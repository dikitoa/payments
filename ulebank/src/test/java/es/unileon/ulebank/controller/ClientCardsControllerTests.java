package es.unileon.ulebank.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.command.RenovateCardCommand;
import es.unileon.ulebank.exceptions.ClientNotFoundException;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.service.SimpleCardManager;

public class ClientCardsControllerTests {

	private ClientCardsController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = new ClientCardsController();
	}

	@Test
	public void testHandleRequest() {
		SimpleCardManager manager = new SimpleCardManager();
		//manager.setCardDao(new InMemoryCardDao(new ArrayList<Card>()));
		controller.setCardManager(manager);
		ModelAndView model = controller.handleRequest(null, null);
		assertEquals("cards", model.getViewName());
		assertNotNull(model.getModel());
	}
	
	@Test
	public void testControllerNotNull() throws ClientNotFoundException {
		assertNotNull(controller);
	}
}
