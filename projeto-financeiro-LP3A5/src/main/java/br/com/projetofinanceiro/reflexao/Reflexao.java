package br.com.projetofinanceiro.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflexao {

	public static void refletirObjeto(Object objeto, Class classe)
			throws IllegalArgumentException, IllegalAccessException {
		System.out.println("Nome da classe: " + classe.getName());
		System.out.println("Nome da classe (simples): " + classe.getSimpleName());
		System.out.println("Nome canonical: " + classe.getResource(classe.getName()));
		System.out.println("Tipo: " + classe.getTypeName());

		System.out.println("====================================");
		System.out.println("============ Atributos =============");

		Field[] atributos = classe.getDeclaredFields();
		for (Field field : atributos) {
			field.setAccessible(true);
            
			field.getType().isPrimitive();
			

			System.out.println(field.getName() + ": " + field.getType().getTypeName() + " (primitivo: "
					+ field.getType().isPrimitive() + ")" + " => valor: " + field.get(objeto));
		}

		System.out.println(" ======================================");
		System.out.println("================= Metodos ==================");
		
		Method[] metodos = classe.getDeclaredMethods();
        for (Method metodo : metodos) {
			System.out.println(metodo.getName() + " - parametros: "
        + Arrays.toString(metodo.getParameterTypes()) +  " - retorno: " + metodo.getReturnType().getSimpleName());
		}
		
		
	}
	
	
	
	
}
