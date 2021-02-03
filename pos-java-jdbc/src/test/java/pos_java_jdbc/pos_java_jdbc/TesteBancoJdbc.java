package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import Dao.UserPosDao;
import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;


public class TesteBancoJdbc {

	@Test
	public void  initBanco() {
		UserPosDao userPosDAO = new UserPosDao();
		Userposjava userposjava = new Userposjava();
		
		userposjava.setNome(" matheus");
	    userposjava.setEmail("matheus@gmail.com");
		
		
		userPosDAO.Salvar(userposjava);
	}
	
	@Test
	public void initListar() {
		UserPosDao dao = new UserPosDao();
		try {
			List<Userposjava> list = dao.listar();
			
			for (Userposjava userposjava : list) {
				System.out.println(userposjava);
				System.out.println("-----------------------------------");
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	@Test
	public void initBuscar() {
		UserPosDao dao = new UserPosDao();
		try {
			Userposjava userposjava =  dao.buscar(6L);
			
			System.out.println(userposjava);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initAtualizar() {
		
		try {
		UserPosDao dao =  new UserPosDao();
		
		Userposjava objetoBanco = dao.buscar(5L);
		
		objetoBanco.setNome("Nome mudado com metodo atualizar");
		
		dao.atualizar(objetoBanco);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initDeletar() {
		try {
			UserPosDao dao =  new UserPosDao();
			dao.deletar(5L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeInsertTelefone() {
		Telefone telefone = new Telefone();
		telefone.setNumero("(87) 445-4485");
		telefone.setTipo("Corporativo");
		telefone.setUsuario(20L);
		
		UserPosDao dao = new UserPosDao();
		dao.salvarTelefone(telefone);
	}
	
	@Test
	public void testeCarregaFonesUser() {
		UserPosDao dao = new UserPosDao();
		List<BeanUserFone> beanUserFones = dao.listaUserFone(11L);
		
		for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("-------------------------------------");
		}
	}
	
	@Test
	public void testeDeleteUserFone() {
		UserPosDao dao = new UserPosDao();
		dao.deleteFonesPorUser(11L);
	}
}
