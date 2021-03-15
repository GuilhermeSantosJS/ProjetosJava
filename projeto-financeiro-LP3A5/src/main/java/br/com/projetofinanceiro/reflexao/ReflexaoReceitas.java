package br.com.projetofinanceiro.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflexaoReceitas {

	public void ReceitasReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeReceita = Class.forName("br.com.projetofinanceiro.model.Receitas");
		Object objectReceita = classeReceita.getConstructor().newInstance();

		Field field = objectReceita.getClass().getDeclaredField("salarioMensal");
		field.setAccessible(true);
		field.set(objectReceita, 600.17);

		field = objectReceita.getClass().getDeclaredField("transporteMensal");
		field.setAccessible(true);
		field.set(objectReceita, 500.70);

		Reflexao.refletirObjeto(objectReceita, objectReceita.getClass());

	}

	
	
	
}
