package Exercicios;

import java.util.Scanner;

public class Calculadora {

	 private static Scanner calc;

	public static void main(String[] args) {
	       calc = new Scanner (System.in);
	        int Op��o = 0;
	        double num1, num2, Resultado;
	        while (Op��o <= 4 ){
	            switch (Op��o) {
	            case 1 :
	            	System.out.println();
	            	System.out.println("SOMA");
	            	System.out.println();
	            	System.out.println("Entre com o primeiro numero : ");
	            	num1 = calc.nextDouble();
	            	System.out.println();
	            	System.out.println("Entre com o segundo numero : ");
	            	num2 = calc.nextDouble();
	            	Resultado = num1 + num2;
	            	System.out.println("Resultado da Soma:" + Resultado);
	            	System.out.println();
	            	
	            	break;
	            case 2:
	            	System.out.println();
	            	System.out.println("Subtra��o");
	            	System.out.println();
	            	System.out.println("Entre com o primeiro:");
	            	num1 = calc.nextDouble();
	            	System.out.println();
	            	System.out.println("Entre com o segundo numero:");
	            	num2 = calc.nextDouble();
	            	Resultado = num1 - num2;
	            	System.out.println("Resultado da Subtra��o:" + Resultado);
	            	System.out.println();
	            	
	            	break;
	            case 3 :
	            	System.out.println();
	            	System.out.println("Multiplica��o");
	            	System.out.println();
	            	System.out.println("Entre com o primeiro numero:");
	            	num1 = calc.nextDouble();
	            	System.out.println();
	            	System.out.println("Entre com o segundo numero:");
	            	num2 = calc.nextDouble();
	            	Resultado = num1 * num2;
	            	System.out.println("Resultado da Multiplica��o:" + Resultado);
	            	System.out.println();
	            
	            	break;
	            case 4 :
	            	System.out.println();
	            	System.out.println("Divis�o");
	            	System.out.println();
	            	System.out.println("Entre com o primeiro numero:");
	            	num1 = calc.nextDouble();
	            	System.out.println();
	            	System.out.println("Entre com o segundo numero:");
	            	num2 = calc.nextDouble();
	            	Resultado = num1 / num2;
	            	System.out.println("Resultado da Divis�o:" + Resultado);
	            	System.out.println();
	            	break;  
	            	
	   }
	            System.out.println("Calculadora em Java");
	            System.out.println("-----------------------------------");
	            System.out.println("1 : SOMA");
	            System.out.println("2 : Subtra��o");
	            System.out.println("3 : Multiplica��o");
	            System.out.println("4 : Divis�o");
	            System.out.println("------------------------------------");
	            System.out.println("Selecione a Op��o desejada:");
	             Op��o = calc.nextInt();
	            	
	            	
	        }
	 }
}

	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	   