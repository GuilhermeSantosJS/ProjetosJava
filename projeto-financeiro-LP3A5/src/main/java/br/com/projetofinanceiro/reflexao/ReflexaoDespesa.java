package br.com.projetofinanceiro.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflexaoDespesa {

	public void DespesaReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeDespesa = Class.forName("br.com.projetofinanceiro.model.Despesas");
		Object objectDespesa = classeDespesa.getConstructor().newInstance();

		Field field = objectDespesa.getClass().getDeclaredField("contaLuz");
		field.setAccessible(true);
		field.set(objectDespesa, 250.21);

		field = objectDespesa.getClass().getDeclaredField("contaAgua");
		field.setAccessible(true);
		field.set(objectDespesa, 300.50);

		Reflexao.refletirObjeto(objectDespesa, objectDespesa.getClass());

	}

}
