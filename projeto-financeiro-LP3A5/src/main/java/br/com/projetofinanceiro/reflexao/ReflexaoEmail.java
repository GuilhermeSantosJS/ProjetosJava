package br.com.projetofinanceiro.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflexaoEmail {

	
	public void EmailReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeEmail = Class.forName("br.com.projetofinanceiro.model.Emails");
		Object objectEmail = classeEmail.getConstructor().newInstance();

		Field field = objectEmail.getClass().getDeclaredField("nome");
		field.setAccessible(true);
		field.set(objectEmail, "Guilherme");

		field = objectEmail.getClass().getDeclaredField("email");
		field.setAccessible(true);
		field.set(objectEmail, "santos.g@aluno.ifsp.edu.br");

		Reflexao.refletirObjeto(objectEmail, objectEmail.getClass());

	}
	
	
}
