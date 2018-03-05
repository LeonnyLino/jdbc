package br.com.caelum.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class ProdutosDAO {
	
	private Connection con;
	
	public ProdutosDAO(Connection con) {
		this.con = con;	
	}
	
	public void salva(Produto produto) throws SQLException {
		String sql = "insert into produtos (nome, descricao) value (?, ?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getDescricao());
			ps.execute();
			
			try(ResultSet rs = ps.getGeneratedKeys()){
				if(rs.next()) {
					int id = rs.getInt("id");
					produto.setId(id);
				}
			}
		}
	}
	
	public List<Produto> lista() throws SQLException {
		String sql = "select * from produtos";
		
		List<Produto> produtosList = new ArrayList<>();
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.execute();
			transformaResultadoEmProdutos(produtosList, ps);
		}
		
		return produtosList;
	}

	private List<Produto> transformaResultadoEmProdutos(List<Produto> produtosList, PreparedStatement ps)
			throws SQLException {
		try(ResultSet rs = ps.getResultSet()){
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				Produto produto = new Produto(nome, descricao);
				produto.setId(id);
				produtosList.add(produto);
			}
			
			return produtosList;
		}
	}
	
	private List<Produto> busca(Categoria categoria) throws SQLException{
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from produtos where categoria_id = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.execute();
			transformaResultadoEmProdutos(produtos, ps);
		}
		
		return produtos;
		
		
	}
}
