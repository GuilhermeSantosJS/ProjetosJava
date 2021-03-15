package br.com.projetofinanceiro.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflexaoTelefone {

	public void TelefoneReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeTelefone = Class.forName("br.com.projetofinanceiro.model.Telefone");
		Object objectTelefone = classeTelefone.getConstructor().newInstance();

		Field field = objectTelefone.getClass().getDeclaredField("numero");
		field.setAccessible(true);
		field.set(objectTelefone, "111111111");

		field = objectTelefone.getClass().getDeclaredField("tipo");
		field.setAccessible(true);
		field.set(objectTelefone, "celular");

		Reflexao.refletirObjeto(objectTelefone, objectTelefone.getClass());

	}

	
	
	
	
	
}
