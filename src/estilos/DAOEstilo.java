package estilos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.Conexao;
import exceptions.ValorInvalido;

public class DAOEstilo {
	public void inserir(String nome) throws EstiloJaCadastrado, ValorInvalido {
		if (nome == null || nome.equals("")) {
			throw new ValorInvalido();
		}
		try {
			try {
				pesquisar(nome);
				throw new EstiloJaCadastrado();
			} catch (EstiloNaoCadastrado e) {
				
			}
			Connection con = Conexao.getConexao();
			String sql = "insert into estilos values (?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nome);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String pesquisar(String nome) throws EstiloNaoCadastrado {
		try {
			
			Connection con = Conexao.getConexao();
			String sql = "select * from estilos where nome = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nome);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String estilo = rs.getString("nome");
				pst.close();
				return estilo;
			} else {
				pst.close();
				throw new EstiloNaoCadastrado();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new EstiloNaoCadastrado();
	}

	public void remover(String nome) throws EstiloNaoCadastrado {
		try {
			try {
				pesquisar(nome);
			} catch (EstiloNaoCadastrado e) {
				throw new EstiloNaoCadastrado();
			}
			Connection con = Conexao.getConexao();
			String sql = "delete from estilos where nome = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nome);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerTodos() {
		try {
			Connection con = Conexao.getConexao();
			String sql = "delete from estilos";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
