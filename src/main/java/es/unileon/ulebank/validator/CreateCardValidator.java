package es.unileon.ulebank.validator;

import es.unileon.ulebank.domain.CardBean;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Israel
 * Clase que realiza la validacion del formulario de creacion de la tarjeta comprobando que no se deje
 * ningun campo en blanco
 */
public class CreateCardValidator implements Validator {

	public boolean supports(Class<?> someClass) {
		return CardBean.class.isAssignableFrom(someClass);
	}

	/**
	 * Valida los campos del formulario enviando un mensaje de error en caso de que este alguno vacio
	 */
	public void validate(Object object, Errors errors) {
		CardBean bean = (CardBean)object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNumber", "required.cardNumber", "Card ID field is necesary");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buyLimitDiary", "required.buyLimitDiary", "Buy Limit Diary field is necesary.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buyLimitMonthly", "required.buyLimitMonthly", "Buy Limit Monthly field is necesary.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cashLimitDiary", "required.cashLimitDiary", "Cash Limit Diary field is necesary.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cashLimitMonthly", "required.cashLimitMonthly", "Cash Limit Monthly field is necesary.");
		if (bean.getCardType().toString().equalsIgnoreCase("NONE")) {
			errors.rejectValue("cardType", "required.cardType");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fee", "required.commissionEmission", "Commission Emission field is necesary.");
		validateCardNumber(bean.getCardNumber(), errors);
		validateBuyLimitDiary(bean.getBuyLimitDiary(), errors);
		validateBuyLimitMonthly(bean.getBuyLimitMonthly(), errors);
		validateCashLimitDiary(bean.getCashLimitDiary(), errors);
		validateCashLimitMonthly(bean.getCashLimitMonthly(), errors);
		validateCommissionEmission(bean.getFee(), errors);
		validateContract(bean.isContract(), errors);
	}

	private void validateCardNumber(String cardNumber, Errors errors) {
		if (!errors.hasFieldErrors("cardNumber")) {
			if (cardNumber.length() < 15) {
				errors.rejectValue("cardNumber", "size.cardNumber");
			}
			if (!cardNumber.trim().matches("^[0-9]*$")) {
				errors.rejectValue("cardNumber", "format.cardNumber");
			}
		}
	}

	private void validateBuyLimitDiary(double buyLimitDiary, Errors errors) {
		if (!errors.hasFieldErrors("buyLimitDiary")) {
			if (buyLimitDiary < 200.0) {
				errors.rejectValue("buyLimitDiary", "min.buyLimitDiary");
			} else if (buyLimitDiary >= Double.MAX_VALUE) {
				errors.rejectValue("buyLimitDiary", "max.buyLimitDiary");
			}
		}
	}

	private void validateBuyLimitMonthly(double buyLimitMonthly, Errors errors) {
		if (!errors.hasFieldErrors("buyLimitMonthly")) {
			if (buyLimitMonthly < 200.0) {
				errors.rejectValue("buyLimitMonthly", "min.buyLimitMonthly");
			} else if (buyLimitMonthly >= Double.MAX_VALUE) {
				errors.rejectValue("buyLimitMonthly", "max.buyLimitMonthly");
			}
		}
	}

	private void validateCashLimitDiary(double cashLimitDiary, Errors errors) {
		if (!errors.hasFieldErrors("cashLimitDiary")) {
			if (cashLimitDiary < 200.0) {
				errors.rejectValue("cashLimitDiary", "min.cashLimitDiary");
			} else if (cashLimitDiary >= Double.MAX_VALUE) {
				errors.rejectValue("cashLimitDiary", "max.cashLimitDiary");
			} 
		}
	}

	private void validateCashLimitMonthly(double cashLimitMonthly, Errors errors) {
		if (!errors.hasFieldErrors("cashLimitMonthly")) {
			if (cashLimitMonthly < 200.0) {
				errors.rejectValue("cashLimitMonthly", "min.cashLimitMonthly");
			} else if (cashLimitMonthly >= Double.MAX_VALUE) {
				errors.rejectValue("cashLimitMonthly", "max.cashLimitMonthly");
			} 
		}
	}

	private void validateCommissionEmission(double commissionEmission, Errors errors) {
		if (!errors.hasFieldErrors("fee")) {
			if (commissionEmission >= Double.MAX_VALUE) {
				errors.rejectValue("fee", "max.fee");
			} 
		}
	}

	private void validateContract(boolean contract, Errors errors) {
		if (!contract) {
			errors.rejectValue("contract", "required.contract");
		}
	}
}