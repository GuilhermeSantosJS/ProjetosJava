package br.com.projetofinanceiro.reflexao_controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import br.com.projetofinanceiro.reflexao.Reflexao;

public class ReflexaoPessoaController {

	public void PessoaControllerReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classePessoaController = Class.forName("br.com.projetofinanceiro.controller.PessoaController");
		Object objectPessoaController = classePessoaController.getConstructor().newInstance();

		Reflexao.refletirObjeto(objectPessoaController, objectPessoaController.getClass());

	}

}
