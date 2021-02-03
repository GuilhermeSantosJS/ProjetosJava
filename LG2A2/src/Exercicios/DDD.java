package Exercicios;

import java.util.Scanner;

public class DDD {
	private static Scanner B;
	
	public static void main(String[] args) {
		B = new Scanner (System.in);
		int DDD;
		
		 System.out.println("DDDs das Cidades");
		 System.out.println("Digite o DDD : ");

		 DDD = B.nextInt();
       
		 switch(DDD){

		 case 11:

		 System.out.println("O DDD é de São paulo (SP)");
		 System.out.println();
		 break;

		 case 21:

		 System.out.println("O DDD é do Rio de Janeiro(RJ)");
		 System.out.println();
		 break;

		 case 27:

		 System.out.println("O DDD é de Vitória(ES)");
		 System.out.println();
		 break;

		 case 31:

		 System.out.println("O DDD é de Belo Horizonte (MG)");
		 System.out.println();
		 break;

		 case 41:

		 System.out.println("O DDD é de Curitiba(PR)");
		 System.out.println();
		 break;
		 
		 case 48:
			 System.out.println("O DDD é de Florianopolis(SC)");
			 System.out.println();
			 break;
			 
		 case 51:
			 System.out.println("O DDD Porto Alegre(RS)");
			 System.out.println();
			 break;
			 
		 case 61:
			 System.out.println("O DDD é de Brasilia(DF)");
			 System.out.println();
			 break;

		 default:

		 System.out.println("DDD não cadastrado");

		 }
		 
		B.close();

		 }
		
	}

	

