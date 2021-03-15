package br.com.projetofinanceiro.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflexaoPessoa {

	
	public void PessoaReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classePessoa = Class.forName("br.com.projetofinanceiro.model.Pessoa");
		Object objectPessoa = classePessoa.getConstructor().newInstance();

		Field field = objectPessoa.getClass().getDeclaredField("nome");
		field.setAccessible(true);
		field.set(objectPessoa, "Guilherme");

		field = objectPessoa.getClass().getDeclaredField("sobrenome");
		field.setAccessible(true);
		field.set(objectPessoa, "dos santos");

		Reflexao.refletirObjeto(objectPessoa, objectPessoa.getClass());

	}
	
	
	
	
}
