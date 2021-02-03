package Exercicios;

import java.util.Scanner;

public class OpTernarios {

	private static Scanner c;

	public static void main(String[] args) {
		 c = new Scanner(System.in);
		 
		    int num = 6; 
		    int A[] = new int[num]; 
		    int i; 
		 
		
		    for (i=0; i<num; i++) {
		      System.out.printf("Digite os numeros: ",(i+1), num);
		      A[i] = c.nextInt();
		    }
		    int maior = A[0];
		    for (i=0; i<num; i++) {
		     
		      if (A[i] > maior)
		         maior = A[i];
		    }
		 System.out.printf("\n");
		    for (i=0; i<num; i++) {
		       
		      if (A[i] == maior)
		              System.out.printf("v[%d] = %2d <--- maior valor\n", i, A[i]);
		    }
		 
		   
		  }
		 

		
	    
		 
	    
	}


