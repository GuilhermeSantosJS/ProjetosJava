package br.com.enviando_mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;




public class ObjetoEnviaEmail {

	
	private String userName = "doemais123@gmail.com"; 		
	private String senha = "doemais.123";
	
	
	private String listaDestinatario = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";
	
	
	public ObjetoEnviaEmail(String listaDestinatario, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatario = listaDestinatario;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}
	

	public void enviarEmail(boolean envioHtml) throws Exception{
		
		
		
		
		
		
		 Properties properties = new Properties();
	     properties.put("mail.smtp.ssl.trust", "*");
	     properties.put("mail.smtp.auth", "true"); /*Autorização*/    
	     properties.put("mail.smtp.starttls", "true");  /*Autenticação*/
	     properties.put("mail.smtp.host", "smtp.gmail.com");/*Servidor gmail Google*/
	     properties.put("mail.smtp.port", "465"); /*Porta do servidor*/
	     properties.put("mail.smtp.socketFactory.port", "465");/*Expecifica a porta  a ser conectada pelo socket */
	     properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe Socket de conexão ao SMTP*/
	     
	     
	     Session session = Session.getInstance(properties, new Authenticator() {
	    	 @Override
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		
	    		return new PasswordAuthentication(userName, senha);
	    		
	    		
	    	}
	    	 
	    	
		});
	    
	     
	     Address[] toUser = InternetAddress.parse(listaDestinatario);
	     
	     Message message = new MimeMessage(session);
	     message.setFrom(new InternetAddress(userName, nomeRemetente)); /*Quem esta enviando */
	     message.setRecipients(Message.RecipientType.TO, toUser); /*Email de destino*/
	     message.setSubject(assuntoEmail); /*Assunto do email*/
	     
	     
	     if(envioHtml) {
	    	 message.setContent(textoEmail, "text/html; charset=utf-8"); 
	     }else {
	     message.setText(textoEmail);
	     }
	      
	     Transport.send(message);
	     
	    
	}
	
	
	public void enviarEmailAnexo(boolean envioHtml) throws Exception{
		
		
		
		 Properties properties = new Properties();
	     properties.put("mail.smtp.ssl.trust", "*");
	     properties.put("mail.smtp.auth", "true"); /*Autorização*/    
	     properties.put("mail.smtp.starttls", "true");  /*Autenticação*/
	     properties.put("mail.smtp.host", "smtp.gmail.com");/*Servidor gmail Google*/
	     properties.put("mail.smtp.port", "465"); /*Porta do servidor*/
	     properties.put("mail.smtp.socketFactory.port", "465");/*Expecifica a porta  a ser conectada pelo socket */
	     properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe Socket de conexão ao SMTP*/
	     
	     Session session = Session.getInstance(properties, new Authenticator() {
	    	 @Override
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		
	    		return new PasswordAuthentication(userName, senha);
	    		
	    		
	    	}
	    	 
	    	
		});
	    
	     
	     Address[] toUser = InternetAddress.parse(listaDestinatario);
	     
	     Message message = new MimeMessage(session);
	     message.setFrom(new InternetAddress(userName, nomeRemetente)); /* Quem esta enviando */
	     message.setRecipients(Message.RecipientType.TO, toUser); /* Email de destino*/
	     message.setSubject(assuntoEmail); /*Assunto do email*/
	     
	     
	     /*Parte 1 do e-mail que é o texto e a descrição do email*/
	     MimeBodyPart corpoEmail = new MimeBodyPart();
	     
	     
	     if(envioHtml) {
	    	 message.setContent(textoEmail, "text/html; charset=utf-8"); 
	     }else {
	     message.setText(textoEmail);
	     }
	      
	     
	     List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
	     arquivos.add(simuladorDePDF());
	     arquivos.add(simuladorDePDF());
	     arquivos.add(simuladorDePDF());
	     arquivos.add(simuladorDePDF());
	     
	     
	     Multipart multipart = new MimeMultipart(); 
	     multipart.addBodyPart(corpoEmail);
	     
	     
	     int index = 0;
	     
	     for(FileInputStream fileInputStream : arquivos) {
	    	 
     /*Parte 2 do Email que são os anexos em pdf*/
	     MimeBodyPart anexoEmail = new MimeBodyPart();
	     
	     
	     anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
	     anexoEmail.setFileName("anexoemail" + index + ".pdf");
	     
	   
	     multipart.addBodyPart(anexoEmail); 
	     
	     index++;
	     
	     
	       }
	    
	     
	     message.setContent(multipart);
   	  
	     Transport.send(message);
	     }
	     
	    
	
	private FileInputStream simuladorDePDF() throws Exception{
		
		Document document = new Document();	
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com Java Mail"));
		document.close();
		
		
		return new FileInputStream(file);
		
	}
	
	
	
}
