package br.com.projetofinanceiro.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;

import lombok.AllArgsConstructor;

	@AllArgsConstructor
	public class ErrorResponse {

	    private final String message = "";
	    
	    private final int code = 0;
	    
	    private final String status = "";
	    
	    private final String objectName = "";
	    
	    private final List<ObjectError> errors = null;

		public ErrorResponse(String string, int value, String reasonPhrase, String objectName2,
				List<br.com.projetofinanceiro.exceptions.ObjectError> errors2) {
		}

		public String getMessage() {
			return message;
		}

		public int getCode() {
			return code;
		}

		public String getStatus() {
			return status;
		}

		public String getObjectName() {
			return objectName;
		}

		public List<ObjectError> getErrors() {
			return errors;
		}
	    
	    
	    
	}
	

