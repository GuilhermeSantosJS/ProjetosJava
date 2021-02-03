package Exercicios;

import javax.swing.JOptionPane;

public class IMC {
	   
            
	public static void main(String[]args) {

		float peso, altura, imc;

		String A = new String();
		
		

		 

		A = JOptionPane.showInputDialog("Digite o peso");

		peso = Float.parseFloat(A);

		 

		A = JOptionPane.showInputDialog("Digita a altura");

		altura = Float.parseFloat(A);

		imc = (peso)/(altura*altura);

		 

		if (imc < 18.5)

		{

		JOptionPane.showMessageDialog(null, "Abaixo do peso " +imc);

		}

		if (imc >=18 && imc <=25)

		{

		JOptionPane.showMessageDialog(null, "Peso Normal " +imc);

		}

		if (imc >=25 && imc <=30)

		{

		JOptionPane.showMessageDialog(null, "Sobrepeso" +imc);

		}

		if (imc >=30 && imc <=35)

		{

		JOptionPane.showMessageDialog(null, "Obesidade Grau 1" +imc);

		}

		if (imc >=35 && imc <=40)

		{

		JOptionPane.showMessageDialog(null, "Obesidade Grau 2 " +imc);

		}

		if (imc > 40)

		{

		JOptionPane.showMessageDialog(null, "Obesidade Grau 3" +imc);

		}

		}
}
			
	    
	    
	   

	   



		

	
