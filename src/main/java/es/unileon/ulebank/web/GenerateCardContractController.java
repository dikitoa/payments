package es.unileon.ulebank.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import es.unileon.ulebank.domain.CardBean;
import es.unileon.ulebank.payments.CardType;
import es.unileon.ulebank.validator.GenerateCardContractValidator;

/**
 * @author Israel
 * Controlador de la vista para generar un contrato en funcion del tipo de tarjeta que se seleccione
 */
@Controller
@RequestMapping({"/generatecontract.htm"})
public class GenerateCardContractController
{
	/**
	 * Tamagno de Buffer
	 */
	private static final int BUFFER_SIZE = 4096;
	/**
	 * Validador para la clase que comprueba que no se deje sin seleccionar el tipo de contrato
	 */
	@Autowired
	private GenerateCardContractValidator generateContractValidator;

	/**
	 * Gestiona las peticiones POST recibiendo los datos modificados del formulario
	 * y generando un contrato en funcion de los datos recibidos
	 * @param bean
	 * @param result
	 * @param status
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String downloadPDF(@ModelAttribute("Contract") CardBean bean, BindingResult result, 
			SessionStatus status, HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletRequestBindingException {
		this.generateContractValidator.validate(bean, result);

		if (result.hasErrors()) {
			return "generatecontract";
		}
		String fileName = bean.getCardType().toLowerCase() + "contract.pdf";
		String path = "/contracts/" + fileName;

		@SuppressWarnings("deprecation")
		String appPath = request.getRealPath("");
		String fullPath = appPath + path;

		File pdf = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(pdf);

		response.setContentType("application/pdf");
		response.setContentLength((int)pdf.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		ServletOutputStream output = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		output.close();

		return null;
	}

	/**
	 * Recibe las peticiones GET de la vista y devuelve la vista con los atributos correspondientes
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String init(ModelMap model)
	{
		CardBean bean = new CardBean();

		model.addAttribute("Contract", bean);

		return "generatecontract";
	}

	/**
	 * Devuelve un Hash Map con los tipos de tarjeta que hay
	 * @return
	 */
	@ModelAttribute("cardType")
	public Map<String, String> populateCardType()
	{
		Map<String, String> types = new LinkedHashMap<String, String>();
		types.put("Debit", CardType.DEBIT.toString());
		types.put("Credit", CardType.CREDIT.toString());
		return types;
	}
}