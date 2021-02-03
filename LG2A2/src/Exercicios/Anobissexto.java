package Exercicios;

import java.util.Scanner;



public class Anobissexto {
	
	 private static Scanner input;

	public static void main(String[]args) {
		 input = new Scanner(System.in);
		   
		 System.out.println("Digite o ano desejado :");
		 int ano = input.nextInt();
				 
         if (((ano % 4 == 0) && (ano % 100 > 0)) || (ano % 400 == 0)) {
        	 System.out.println(" O Ano é Bissexto");
         }
             
         else {
        	 System.out.println("O Ano não é Bissexto");
         }
       
            
}
	 
       
}