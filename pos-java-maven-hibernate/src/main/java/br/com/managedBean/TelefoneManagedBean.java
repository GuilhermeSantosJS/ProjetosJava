package br.com.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dao.DaoTelefones;
import br.com.dao.DaoUsuario;
import br.com.model.TelefoneUser;
import br.com.model.UsuarioPessoa;

@ManagedBean(name = "telefoneManagedBean")
@ViewScoped
public class TelefoneManagedBean {
  
	private UsuarioPessoa user = new UsuarioPessoa();
	private DaoUsuario<UsuarioPessoa> daoUser = new DaoUsuario<UsuarioPessoa>();
	private DaoTelefones<TelefoneUser> telefoneUser = new DaoTelefones<TelefoneUser>();
	
	
	private TelefoneUser telefone = new TelefoneUser();
	
	@PostConstruct
	public void init() {
		
		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigouser");
		user = daoUser.pesquisar(Long.parseLong(coduser), UsuarioPessoa.class);
	}
	
	
	public String salvar() {
		telefone.setUsuarioPessoa(user);
	telefoneUser.salvar(telefone);
	telefone = new TelefoneUser();
	user = daoUser.pesquisar(user.getId(), UsuarioPessoa.class);
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Salvo com sucesso!"));
	return "";
	}
	
	public String removeTelefone() throws Exception {
		telefoneUser.deletarPorId(telefone);
		user = daoUser.pesquisar(user.getId(), UsuarioPessoa.class);
		telefone = new TelefoneUser();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Telefone removido com sucesso!"));
		return "";
	}
	
	
	public void setTelefone(TelefoneUser telefone) {
		this.telefone = telefone;
	}
	
	public TelefoneUser getTelefone() {
		return telefone;
	}
	
	
	public void setUser(UsuarioPessoa user) {
		this.user = user;
	}
	
	public UsuarioPessoa getUser() {
		return user;
	}



	public DaoTelefones<TelefoneUser> getTelefoneUser() {
		return telefoneUser;
	}



	public void setTelefoneUser(DaoTelefones<TelefoneUser> telefoneUser) {
		this.telefoneUser = telefoneUser;
	}
	
	
	
}

