package Exercicios;

import java.util.Scanner;




public class Triangulo {
	
	 private static Scanner tri;

	public static void main(String[] args) {
		 
		 double a, b, c, ang = 0;
		 int op = 1;
		 while (op == 1) {
			 tri = new Scanner(System.in);
			 System.out.println("Entre com o Primeiro lado:");
	            a = tri.nextDouble();
	            System.out.println("------------------------------------------------");
	            System.out.println("Entre com  o Segundo lado:");
	            b = tri.nextDouble();
	            System.out.println("------------------------------------------------");
	            System.out.println("Entre com o Terceiro lado:");
	            c = tri.nextDouble();
	            System.out.println("------------------------------------------------");
	            if ((a < b + c) && (b < a + c) && (c < a + b)) {
	                if (a == b && a == c) {
	                    System.out.println("Triangulo Equilatero");
	                } else if ((a == b) || (a == c)) {
	                    System.out.println("Triangulo Isosceles");
	                }
	    			if(ang>90||ang<180){
	    				ang=((b*b) + (c*c) - (a*a))/2*b*c;
	    				System.out.println("O triangulo Possui lado obtuso");
	    			}
	            } else {
	                System.out.println("Não é um triangulo!");
	            }
	            System.out.println("------------------------------------------------");
	            System.out.println("Deseja continuar? 1: SIM ,  2 = NÃO");
	            op = tri.nextInt();
		 }
		 
		 
		 
	 }
		
		
		 
         
		
		
				

	}


