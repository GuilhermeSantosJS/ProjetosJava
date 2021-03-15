package br.com.projetofinanceiro.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflexaoTransacao {

	
	public void TransacaoReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeTransacao = Class.forName("br.com.projetofinanceiro.model.Transacoes");
		Object objectTransacao = classeTransacao.getConstructor().newInstance();

		Field field = objectTransacao.getClass().getDeclaredField("valorTransacao");
		field.setAccessible(true);
		field.set(objectTransacao, 150.20);

		field = objectTransacao.getClass().getDeclaredField("contaTransacao");
		field.setAccessible(true);
		field.set(objectTransacao, "095468759");

		Reflexao.refletirObjeto(objectTransacao, objectTransacao.getClass());

	}
	
	
	
}
