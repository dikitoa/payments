package es.unileon.ulebank.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

public class GenerateCardContractTests {

	private GenerateCardContractController controller;
	
	@Before
	public void setUp() throws Exception {
		this.controller = new GenerateCardContractController();
	}

	@Test
	public void testInit() {
		ModelMap map = new ModelMap();
		String result = controller.init(map);
		assertEquals("generatecontract", result);
		assertTrue(map.containsAttribute("Contract"));
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
