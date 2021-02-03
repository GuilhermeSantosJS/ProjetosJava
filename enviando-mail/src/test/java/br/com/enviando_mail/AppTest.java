package br.com.enviando_mail;


public class AppTest {
	   

	
 @org.junit.Test
 public void testeEmail() throws Exception{
	


	 
	 	StringBuilder stringBuilderTextoEmail = new StringBuilder();
	 	
	 	stringBuilderTextoEmail.append("Olá, <br/><br/>");
	 	stringBuilderTextoEmail.append("<h2>Você está recebendo um e-mail automatico da Doe+</h2> <br/><br/>");
	 	stringBuilderTextoEmail.append("<h2>Solicitante interassado na doação </h2><br/><br/>");
	 	stringBuilderTextoEmail.append("Para ter acesso clique no botão abaixo. <br/><br/>");
	 	
	 	stringBuilderTextoEmail.append("<a target=\"_blank\" href=\"http://www..com.br\" style=\"color:#2525a7; padding: 14px 25px; text-align:center; text-decoration:none; display:inline-block; border-radius:30px; font-size:20px; font-family:courier; border: 3px solid green; background-color:#99DA39;\"> Acessar o Site</a>");
	 
	     
	    ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("mikeiasenik@gmail.com", 
			                                           "Teste", 
			                                           "Testando email com o java", 
			                                           stringBuilderTextoEmail.toString());
	
	enviaEmail.enviarEmailAnexo(true);
	
	
	Thread.sleep(5000);
	

 }
}
	



