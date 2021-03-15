package br.com.projetofinanceiro.reflexao_controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import br.com.projetofinanceiro.reflexao.Reflexao;

public class ReflexaoTransacaoController {

	public void TransacaoControllerReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeTransacaoController = Class.forName("br.com.projetofinanceiro.controller.TransacaoController");
		Object objectTransacaoController = classeTransacaoController.getConstructor().newInstance();

		Reflexao.refletirObjeto(objectTransacaoController, objectTransacaoController.getClass());

	}

}
