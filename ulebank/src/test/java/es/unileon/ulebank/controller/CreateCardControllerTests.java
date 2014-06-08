package es.unileon.ulebank.controller;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import es.unileon.ulebank.domain.CardBean;
import es.unileon.ulebank.exceptions.ClientNotFoundException;

public class CreateCardControllerTests {

	private CreateCardController controller;
	
	@Before
	public void setUp() throws Exception {
		this.controller = new CreateCardController();
	}

	@Test
	public void testInitForm() {
		ModelMap map = new ModelMap();
		String result = controller.initForm(map);
		assertEquals("createcard", result);
		
		CardBean bean = (CardBean) map.get("newCard");
		assertNotNull(bean);
	}
	
	@Test
	public void testPopulateCardType() {
		Map<String, String> map = controller.populateCardType();
		assertTrue(map.containsKey("Credit"));
		assertTrue(map.containsValue("CREDIT"));
		assertTrue(map.containsKey("Debit"));
		assertTrue(map.containsValue("DEBIT"));
	}
	
	@Test
	public void testControllerNotNull() throws ClientNotFoundException {
		assertNotNull(controller);
	}
}
