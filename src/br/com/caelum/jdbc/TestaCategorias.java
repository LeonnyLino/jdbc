package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.caelum.jdbc.dao.CategoriasDAO;

public class TestaCategorias {
	
	public static void main(String[] args) {
		
		try(Connection con = new ConnectionPool().getConnection()){
			List<Categoria> Categorias= new CategoriasDAO(con).lista();
			
			try(PreparedStatement ps = con.prepareStatement(sql)){
				
			}
		}
	}
}
