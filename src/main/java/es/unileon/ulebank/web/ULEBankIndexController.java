package es.unileon.ulebank.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/startmainpage.htm")
public class ULEBankIndexController {

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Returning index view");
		return new ModelAndView("bankindex");
	}
}