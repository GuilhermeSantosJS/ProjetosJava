package br.com.projetofinanceiro.reflexao_controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import br.com.projetofinanceiro.reflexao.Reflexao;

public class ReflexaoDespesaController {

	public void DespesaControllerReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeDespesaController = Class.forName("br.com.projetofinanceiro.controller.DespesasController");
		Object objectDespesaController = classeDespesaController.getConstructor().newInstance();

		Reflexao.refletirObjeto(objectDespesaController, objectDespesaController.getClass());

	}

}
