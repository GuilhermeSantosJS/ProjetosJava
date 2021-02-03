package Exercicios;

import java.util.Scanner;


public class Aluno {
    private static Scanner calc;
    public static void main(String[] args) {
         calc = new Scanner (System.in);
         double N1, N2, N3, frequencia, media = 0;
         
         
         System.out.println("Entre com sua frequencia:");
         frequencia = calc.nextDouble();
         System.out.println("Entre com a primeira nota:");
         N1 = calc.nextDouble();
         System.out.println("Entre com a segunda nota");
         N2 = calc.nextDouble();
         System.out.println("Entre com a terceira nota");
         N3 = calc.nextDouble();
         
         
         
        
         
        if(N1 >= N2 && N1 >= N3) {
            if(N2>=N3){
              media = (N1 + N2) / 2;
             }else{
              media = (N1 + N3) / 2;
            }
        }
        if(N2 >= N1 && N2 >= N3){
                if(N1 >= N3){
                media =(N1 + N2) / 2;
                }else{
                 media = (N2 + N3) / 2; 
                }
            }
        
        if (N3 >= N1 && N3 >= N2){
            if(N1 > N2){
                media = (N1 + N3) / 2;
             }else{
                media = (N3 + N2) / 2;
            }
        }
             System.out.println(media);
             
         if(media >= 6 && frequencia >= 85){
            System.out.println("ALUNO APROVADO");
            }
        else if (media <= 6 && frequencia <= 85){
            System.out.println("ALUNO REPROVADO");
         }
        else if (media < 6 && frequencia > 85){
            System.out.println("ALUNO REPROVADO");
        }
        else if (media > 6 && frequencia < 85){
            System.out.println("ALUNO REPROVADO");
        }
        
        else if (media > 10){
           System.out.println("ERRO");
        }
        
      
         
         
        
        
        
    
    }
}

