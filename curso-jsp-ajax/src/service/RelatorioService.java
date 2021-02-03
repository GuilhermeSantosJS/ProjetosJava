package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private String SERATOR = File.separator;
	private String caminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubRepot_Dir = "";
	private File arquivoGerado = null;
	
	public  String gerarRelatorio(List<?> listaDataBeanCollection, HashMap parametrosRelatorio, String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext, String tipoExportar) throws Exception  {
		
		
		/*Cria a lista de collection data source de beans que carregam os dados para o relatorio*/
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanCollection);
		
		
		/*Fornece o caminho fisico até a pasta que contem os relatorios .jasper*/
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		File file = new File(caminhoRelatorio + SERATOR + nomeRelatorioJasper + ".jasper");
		
		if(caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SERATOR = "";
		}
		
		/*Caminho para imagens*/
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		
		/*caminho completo até o relatorio compilado indicado*/
		String caminhoArquivosJasper = caminhoRelatorio + SERATOR + nomeRelatorioJasper + ".jasper";
		
		/*Faz o carregamento do relatorio*/
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);
		
		/* sera parametros SUBREPORT_DIR com o caminho fisico para subreport*/
		caminhoSubRepot_Dir = caminhoRelatorio + SERATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubRepot_Dir);
		
		/*cARRGEA O ARQUIVO*/
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
		
		if(tipoExportar.equalsIgnoreCase("pdf")) {
			exporter = new JRPdfExporter();	
		}else if(tipoExportar.equalsIgnoreCase("xls")) {
			exporter = new JRXlsExporter();
		}
	      
	    
		
	    /*caminho relatorio exportado*/
	    caminhoArquivoRelatorio = caminhoRelatorio + SERATOR + nomeRelatorioSaida + "." + tipoExportar;
	    
	    /*Criar novo arquivos exportado*/
	    
	    arquivoGerado = new File(caminhoArquivoRelatorio);
	    
	    /*prepara a impressão*/
	    exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
	    
	    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
	    
	    /*Executa a exportacao*/
	    exporter.exportReport();
	    
	    /*Remove o arquivo do servidor apos ser feito o download*/
	    arquivoGerado.deleteOnExit();
	    
	    
		return caminhoArquivoRelatorio;
		
		
		
		
	}
	
	
	
	
}
