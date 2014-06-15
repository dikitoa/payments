package es.unileon.ulebank.validator;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class Mock implements BindingResult {

	public void addAllErrors(Errors arg0) {
		// TODO Auto-generated method stub
		
	}

	public List<ObjectError> getAllErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getErrorCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public FieldError getFieldError() {
		// TODO Auto-generated method stub
		return null;
	}

	public FieldError getFieldError(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getFieldErrorCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getFieldErrorCount(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<FieldError> getFieldErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FieldError> getFieldErrors(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<?> getFieldType(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getFieldValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectError getGlobalError() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getGlobalErrorCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ObjectError> getGlobalErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNestedPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getObjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasErrors() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasFieldErrors() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasFieldErrors(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasGlobalErrors() {
		// TODO Auto-generated method stub
		return false;
	}

	public void popNestedPath() throws IllegalStateException {
		// TODO Auto-generated method stub
		
	}

	public void pushNestedPath(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void reject(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void reject(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	public void reject(String arg0, Object[] arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	public void rejectValue(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	public void rejectValue(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	public void rejectValue(String arg0, String arg1, Object[] arg2, String arg3) {
		// TODO Auto-generated method stub
		
	}

	public void setNestedPath(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void addError(ObjectError arg0) {
		// TODO Auto-generated method stub
		
	}

	public PropertyEditor findEditor(String arg0, Class<?> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public PropertyEditorRegistry getPropertyEditorRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getRawFieldValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getSuppressedFields() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	public void recordSuppressedField(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public String[] resolveMessageCodes(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] resolveMessageCodes(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
