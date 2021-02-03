package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBase;
import entidades.Usuario;
import entidades.eventos;

public class DaoEventos {
	
private static Connection connection;
	
	public DaoEventos() {
		connection = ConnectionDataBase.getConnection();	
	}
	
	public List<eventos> getEventos() throws Exception{
		   List<eventos> eventos = new ArrayList<eventos>();
			String sql = "select * from usuario";
			
			PreparedStatement statement  = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				eventos event = new eventos();
				event.setId(resultSet.getString("id"));
				event.setDescricao(resultSet.getString("descricao"));
				event.setDataevento(resultSet.getString("dataevento"));
				
				eventos.add((entidades.eventos) eventos);
			}
			
			return eventos;
	}

}
