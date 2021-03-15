package br.com.projetofinanceiro.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflexaoUsuario {

	public void UsuarioReflexao()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class classeUsuario = Class.forName("br.com.projetofinanceiro.model.Usuario");
		Object objectUsuario = classeUsuario.getConstructor().newInstance();

		Field field = objectUsuario.getClass().getDeclaredField("login");
		field.setAccessible(true);
		field.set(objectUsuario, "guilherme");

		field = objectUsuario.getClass().getDeclaredField("senha");
		field.setAccessible(true);
		field.set(objectUsuario, "123");

		Reflexao.refletirObjeto(objectUsuario, objectUsuario.getClass());

	}

}
