package br.com.projetofinanceiro.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ObjectError {

	    private final String message = "";
	    private final String field = "";
	    private final Object parameter = new Object();
	    
		public ObjectError(String defaultMessage, String field2, Object rejectedValue) {
			
		}
		public String getMessage() {
			return message;
		}
		public String getField() {
			return field;
		}
		public Object getParameter() {
			return parameter;
		}
	    
   
	    
	}
	
	

