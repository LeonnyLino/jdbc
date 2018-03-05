package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Categoria;

public class CategoriasDAO {
	private final Connection con;

	public CategoriasDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public List<Categoria> lista() throws SQLException{
		System.out.println("Executando query");
		
		List<Categoria> categorias = new ArrayList<>();
		
		String sql = "select * from categoria";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.execute();
			
			try(ResultSet rs = ps.getResultSet()){
				while(rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					Categoria categoria = new Categoria(id, nome);
					categorias.add(categoria);		
				}
			}
		}
		return categorias;
	}
	
	
}
