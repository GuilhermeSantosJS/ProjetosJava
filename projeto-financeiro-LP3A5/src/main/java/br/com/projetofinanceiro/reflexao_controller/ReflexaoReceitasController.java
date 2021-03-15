package br.com.projetofinanceiro.reflexao_controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import br.com.projetofinanceiro.reflexao.Reflexao;

public class ReflexaoReceitasController {

	public void ReceitasControllerReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeReceitasController = Class.forName("br.com.projetofinanceiro.controller.ReceitasController");
		Object objectReceitasController = classeReceitasController.getConstructor().newInstance();

		Reflexao.refletirObjeto(objectReceitasController, objectReceitasController.getClass());

	}

}
