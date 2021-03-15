package br.com.projetofinanceiro.reflexao_controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import br.com.projetofinanceiro.reflexao.Reflexao;

public class ReflexaoReportUtil {

	public void ReportUtilReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeReportUtil = Class.forName("br.com.projetofinanceiro.controller.ReportUtil");
		Object objectReportUtil = classeReportUtil.getConstructor().newInstance();

		Reflexao.refletirObjeto(objectReportUtil, objectReportUtil.getClass());

	}

}
