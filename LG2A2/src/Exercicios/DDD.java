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

		 System.out.println("O DDD � de S�o paulo (SP)");
		 System.out.println();
		 break;

		 case 21:

		 System.out.println("O DDD � do Rio de Janeiro(RJ)");
		 System.out.println();
		 break;

		 case 27:

		 System.out.println("O DDD � de Vit�ria(ES)");
		 System.out.println();
		 break;

		 case 31:

		 System.out.println("O DDD � de Belo Horizonte (MG)");
		 System.out.println();
		 break;

		 case 41:

		 System.out.println("O DDD � de Curitiba(PR)");
		 System.out.println();
		 break;
		 
		 case 48:
			 System.out.println("O DDD � de Florianopolis(SC)");
			 System.out.println();
			 break;
			 
		 case 51:
			 System.out.println("O DDD Porto Alegre(RS)");
			 System.out.println();
			 break;
			 
		 case 61:
			 System.out.println("O DDD � de Brasilia(DF)");
			 System.out.println();
			 break;

		 default:

		 System.out.println("DDD n�o cadastrado");

		 }
		 
		B.close();

		 }
		
	}

	

