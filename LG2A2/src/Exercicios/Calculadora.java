package Exercicios;

import java.util.Scanner;

public class Calculadora {

	 private static Scanner calc;

	public static void main(String[] args) {
	       calc = new Scanner (System.in);
	        int Opção = 0;
	        double num1, num2, Resultado;
	        while (Opção <= 4 ){
	            switch (Opção) {
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
	            	System.out.println("Subtração");
	            	System.out.println();
	            	System.out.println("Entre com o primeiro:");
	            	num1 = calc.nextDouble();
	            	System.out.println();
	            	System.out.println("Entre com o segundo numero:");
	            	num2 = calc.nextDouble();
	            	Resultado = num1 - num2;
	            	System.out.println("Resultado da Subtração:" + Resultado);
	            	System.out.println();
	            	
	            	break;
	            case 3 :
	            	System.out.println();
	            	System.out.println("Multiplicação");
	            	System.out.println();
	            	System.out.println("Entre com o primeiro numero:");
	            	num1 = calc.nextDouble();
	            	System.out.println();
	            	System.out.println("Entre com o segundo numero:");
	            	num2 = calc.nextDouble();
	            	Resultado = num1 * num2;
	            	System.out.println("Resultado da Multiplicação:" + Resultado);
	            	System.out.println();
	            
	            	break;
	            case 4 :
	            	System.out.println();
	            	System.out.println("Divisão");
	            	System.out.println();
	            	System.out.println("Entre com o primeiro numero:");
	            	num1 = calc.nextDouble();
	            	System.out.println();
	            	System.out.println("Entre com o segundo numero:");
	            	num2 = calc.nextDouble();
	            	Resultado = num1 / num2;
	            	System.out.println("Resultado da Divisão:" + Resultado);
	            	System.out.println();
	            	break;  
	            	
	   }
	            System.out.println("Calculadora em Java");
	            System.out.println("-----------------------------------");
	            System.out.println("1 : SOMA");
	            System.out.println("2 : Subtração");
	            System.out.println("3 : Multiplicação");
	            System.out.println("4 : Divisão");
	            System.out.println("------------------------------------");
	            System.out.println("Selecione a Opção desejada:");
	             Opção = calc.nextInt();
	            	
	            	
	        }
	 }
}

	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	   