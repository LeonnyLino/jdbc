package br.com.caelum.jdbc;

import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;

import br.com.caelum.jdbc.dao.ProdutosDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class teste {
	public static void main(String[] args) throws SQLException {
		Produto mesa = new Produto("Mesa Azul", "Mesa com 4 pés");
		
		try(Connection con = (Connection) new ConnectionPool().getConnection()){
			ProdutosDAO dao = new ProdutosDAO(con);
			dao.salva(mesa);
			
			List<Produto> produtosList = dao.lista();
			
            for (Produto listProduto : produtosList) {
                System.out.println("Existe o produto: " + listProduto);
            }
		}
	}
}
