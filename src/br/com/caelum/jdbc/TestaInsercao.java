package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		
		try(Connection connection = new ConnectionPool().getConnection()){
			String sql = "insert into produtos (nome, descricao) values(?, ?)";
			connection.setAutoCommit(false);
			try(PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				adiciona("TV LCD", "32 polegadas", ps);
				adiciona("Bluray", "Full HDMI", ps);
				connection.commit();
			}catch(Exception e) {
				e.printStackTrace();
				connection.rollback();
				System.out.println("Rollback foi executado!");
			}
			
			
		}
		
		
	}

	private static void adiciona(String nome, String descricao, 
			PreparedStatement ps) throws SQLException {
		if(nome.equals("Bluray")) {
			throw new IllegalArgumentException("Problema ocorrido");
		}
		ps.setString(1, nome);
		ps.setString(2, descricao);
		ps.execute();
		
		
		try(ResultSet rs = ps.getGeneratedKeys()){
			while(rs.next()) {
				String id = rs.getString("GENERATED_KEY");
				System.out.println(id + " Gerado");
			}
			
		}
		
		
	}
}
