package es.unileon.ulebank.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import es.unileon.ulebank.domain.CardBean;

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
		assertEquals(400.0, bean.getBuyLimitDiary(), 0.0001);
		assertEquals(1000.0, bean.getBuyLimitMonthly(), 0.0001);
		assertEquals(400.0, bean.getCashLimitDiary(), 0.0001);
		assertEquals(1000.0, bean.getCashLimitMonthly(), 0.0001);
	}
	
	@Test
	public void testPopulateCardType() {
		Map<String, String> map = controller.populateCardType();
		assertTrue(map.containsKey("Credit"));
		assertTrue(map.containsValue("CREDIT"));
		assertTrue(map.containsKey("Debit"));
		assertTrue(map.containsValue("DEBIT"));
	}
}
