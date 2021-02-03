package br.com.managedBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.behavior.Behavior;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.google.gson.Gson;

import br.com.dao.DaoEmail;
import br.com.dao.DaoGeneric;
import br.com.dao.DaoUsuario;
import br.com.datatablelazy.LazyDataTableModelUserPessoa;
import br.com.model.UserEmail;
import br.com.model.UsuarioPessoa;

@ManagedBean(name = "usuarioPessoaManagedBean")
@ViewScoped
public class UsuarioPessoaManagedBean {

	private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
	private LazyDataTableModelUserPessoa<UsuarioPessoa> list = new LazyDataTableModelUserPessoa<UsuarioPessoa>();
	//private List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();
	private DaoUsuario<UsuarioPessoa> daoGeneric = new DaoUsuario<UsuarioPessoa>();
	private BarChartModel barCharModel = new BarChartModel();
	private UserEmail emailUser = new UserEmail();
	private DaoEmail<UserEmail> daoEmail = new DaoEmail<UserEmail>();
	private String campoPesquisa;
	
	@PostConstruct
	public void init() {
	list.load(0, 5, null, null, null);
     montarGrafico();
	}



	private void montarGrafico() {
		barCharModel = new BarChartModel();
		ChartSeries userSalario = new ChartSeries(); /* Grupo de funcionarios */
		
			for (UsuarioPessoa usuarioPessoa : list.list) { /* add salario para o grupo */
				userSalario.set(usuarioPessoa.getNome(), usuarioPessoa.getSalario());
			}
			barCharModel.addSeries(userSalario); // adiciona o grupo 
			barCharModel.setTitle("Gráfico de salários");
	}
	
	
	
	public String getCampoPesquisa() {
		return campoPesquisa;
	}



	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}



	public DaoEmail<UserEmail> getDaoEmail() {
		return daoEmail;
	}



	public void setDaoEmail(DaoEmail<UserEmail> daoEmail) {
		this.daoEmail = daoEmail;
	}



	public void setList(LazyDataTableModelUserPessoa<UsuarioPessoa> list) {
		this.list = list;
	}



	public void setDaoGeneric(DaoUsuario<UsuarioPessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}



	public void setBarCharModel(BarChartModel barCharModel) {
		this.barCharModel = barCharModel;
	}



	public UserEmail getEmailUser() {
		return emailUser;
	}


	public void setEmailUser(UserEmail emailUser) {
		this.emailUser = emailUser;
	}


	public BarChartModel getBarCharModel() {
		return barCharModel;
	}
	
	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}

	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}

	public DaoGeneric<UsuarioPessoa> getDaoGeneric() {
		return daoGeneric;
	}

	
	
	public String salvar()
	{
		daoGeneric.salvar(usuarioPessoa);
		list.list.add(usuarioPessoa);
		usuarioPessoa = new UsuarioPessoa();
		init();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Salvo com sucesso!"));
		return "";
		
	}
	
	public String novo() {
		
		usuarioPessoa = new  UsuarioPessoa();
		return "";
	}
	
	public String remover() {
		try {
		daoGeneric.removerUsuario(usuarioPessoa);
		list.list.remove(usuarioPessoa);
		usuarioPessoa = new UsuarioPessoa();
		
		init();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Removido com sucesso!"));
		
		}catch(Exception e) {
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Existem telefones para o usuário!"));
				
				
			}else {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public void pesquisaCep(AjaxBehaviorEvent event) {
		try {
				
			URL url = new URL("https://viacep.com.br/ws/"+ usuarioPessoa.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			String cep = "";
			StringBuilder jsonCep = new StringBuilder();
			
			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}
			
			
			UsuarioPessoa userCepPessoa = new Gson().fromJson(jsonCep.toString(), UsuarioPessoa.class);
			
			usuarioPessoa.setCep(userCepPessoa.getCep());
			usuarioPessoa.setLogradouro(userCepPessoa.getLogradouro());
			usuarioPessoa.setComplemento(userCepPessoa.getComplemento());
			usuarioPessoa.setBairro(userCepPessoa.getBairro());
			usuarioPessoa.setLocalidade(userCepPessoa.getLocalidade());
			usuarioPessoa.setUf(userCepPessoa.getUf());
			usuarioPessoa.setUnidade(userCepPessoa.getUnidade());
			usuarioPessoa.setIbge(userCepPessoa.getIbge());
			usuarioPessoa.setGia(userCepPessoa.getGia());
			
			
			
			
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public LazyDataTableModelUserPessoa<UsuarioPessoa> getList(){
		montarGrafico();
		return list;
	}
	
	
	public void addEmail() {		
		emailUser.setUsuarioPessoa(usuarioPessoa);
		emailUser = daoEmail.updateMerge(emailUser);
		usuarioPessoa.getUserEmail().add(emailUser);
		emailUser = new UserEmail();
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado:", "Email Salvo com Sucesso!"));
		
	}
	
	public void removerEmail() throws Exception {
		String codigoemail = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigoemail");
		UserEmail remover  = new UserEmail();
		remover.setId(Long.parseLong(codigoemail));
		daoEmail.deletarPorId(remover);
		usuarioPessoa.getUserEmail().remove(remover);
		
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado:", "Email Removido com Sucesso!"));
		
	}
	
	
	public void pesquisar() {
		list.pesquisar(campoPesquisa);
		montarGrafico();
		
	}
	
	public void upload(FileUploadEvent image) {
		String imagem = "data:image/png;base64," + DatatypeConverter.printBase64Binary(image.getFile().getContents());
		
		usuarioPessoa.setImagem(imagem);
	}
	
	public void download() throws IOException {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		
		String fileDownloadId = params.get("fileDownloadId");
		UsuarioPessoa pessoa = daoGeneric.pesquisar(Long.parseLong(fileDownloadId), UsuarioPessoa.class);
		byte[] imagem = new Base64().decodeBase64(pessoa.getImagem().split("\\,")[1]);
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		response.addHeader("Content-Disposition", "attachment; filename=download.png");
		response.setContentType("application/octet-stream");
		response.setContentLength(imagem.length);
		response.getOutputStream().write(imagem);
		FacesContext.getCurrentInstance().responseComplete();
		
	}
	

}
