package br.com.projetofinanceiro.reflexao_controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import br.com.projetofinanceiro.reflexao.Reflexao;

public class ReflexaoIndexController {

	public void IndexControllerReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeIndexController = Class.forName("br.com.projetofinanceiro.controller.IndexController");
		Object objectIndexController = classeIndexController.getConstructor().newInstance();

		Reflexao.refletirObjeto(objectIndexController, objectIndexController.getClass());

	}

}
