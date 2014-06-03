package es.unileon.ulebank.validator;

import es.unileon.ulebank.domain.CardBean;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Israel
 * Clase que realiza la validacion del formulario de seleccion del tipo de contrato a generar
 */
public class GenerateCardContractValidator implements Validator {


	public boolean supports(Class<?> someClass) {
		return CardBean.class.isAssignableFrom(someClass);
	}

	/**
	 * Comprueba que se seleccione un tipo de tarjeta y sino envia un error
	 */
	public void validate(Object object, Errors errors) {
		CardBean bean = (CardBean)object;
		if (bean.getCardType().equalsIgnoreCase("NONE")) {
			errors.rejectValue("cardType", "required.cardType");
		}
	}
}