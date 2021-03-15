package br.com.projetofinanceiro.reflexao_controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import br.com.projetofinanceiro.reflexao.Reflexao;

public class ReflexaoEmailController {

	public void EmailControllerReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeEmailController = Class.forName("br.com.projetofinanceiro.controller.EmailController");
		Object objectEmailController = classeEmailController.getConstructor().newInstance();

		Reflexao.refletirObjeto(objectEmailController, objectEmailController.getClass());

	}

}
