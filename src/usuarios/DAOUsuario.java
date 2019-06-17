package usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao;
import estilos.EstiloNaoCadastrado;
import exceptions.ValorInvalido;

public class DAOUsuario {
	public void inserir(Usuario u) throws UsuarioJaCadastrado, ValorInvalido {
		usuarioIsValid(u);
		try {
			try {
				pesquisar(u.getIdUsuario());
				throw new UsuarioJaCadastrado();
			} catch (Exception e) {

			}
			if (u instanceof Artista) {
				Connection con = Conexao.getConexao();
				String sql = "insert into usuarios (identificador, nome, email, senha, tipo) "
						+ "values (?, ?, ?, ?, ?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, u.getIdUsuario());
				pst.setString(2, u.getNome());
				pst.setString(3, u.getEmail());
				pst.setString(4, u.getSenha());
				pst.setInt(5, 2);
				pst.executeUpdate();
				pst.close();
			} else if (u instanceof Assinante) {
				Connection con = Conexao.getConexao();
				String sql = "insert into usuarios (identificador, nome, email, senha, tipo) "
						+ "values (?, ?, ?, ?, ?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, u.getIdUsuario());
				pst.setString(2, u.getNome());
				pst.setString(3, u.getEmail());
				pst.setString(4, u.getSenha());
				pst.setInt(5, 1);
				pst.executeUpdate();
				pst.close();
			} else if (u instanceof Banda) {
				Connection con = Conexao.getConexao();
				PreparedStatement pst;
				if (((Banda) u).getComponentes() == null || ((Banda) u).getComponentes().isEmpty()) {
					throw new ValorInvalido();
				}
				for (Usuario a : ((Banda) u).getComponentes()) {
					usuarioIsValid(a);
					String sql = "insert into usuarios (identificador, nome, email, senha, idbanda, tipo) "
							+ "values (?, ?, ?, ?, ?)";
					pst = con.prepareStatement(sql);
					pst.setString(1, a.getIdUsuario());
					pst.setString(2, a.getNome());
					pst.setString(3, a.getEmail());
					pst.setString(4, a.getSenha());
					pst.setString(5, u.getIdUsuario());
					pst.setInt(6, 2);
					pst.executeUpdate();
				}

				String sql = "insert into usuarios (identificador, nome, email, senha, tipo) "
						+ "values (?, ?, ?, ?, ?)";
				pst = con.prepareStatement(sql);
				pst.setString(1, u.getIdUsuario());
				pst.setString(2, u.getNome());
				pst.setString(3, u.getEmail());
				pst.setString(4, u.getSenha());
				pst.setInt(5, 3);
				pst.executeUpdate();
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Usuario pesquisar(String idUsu) throws UsuarioNaoCadastrado {
		try {
			Connection con = Conexao.getConexao();
			String sql = "select * from usuarios where nome = ?";

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, idUsu);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getInt("tipo") == 1) {
					// assinante
					Usuario u = new Assinante(rs.getString("identificador"), rs.getString("nome"), rs.getString("email"),
							rs.getString("senha"));
					pst.close();
					return u;
				} else if (rs.getInt("tipo") == 2) {
					// artista
					Usuario u = new Artista(rs.getString("identificador"), rs.getString("nome"), rs.getString("email"),
							rs.getString("senha"));
					pst.close();
					return u;
					
				} else if (rs.getInt("tipo") == 3) {
					// banda
					ArrayList<Artista> artistas = new ArrayList<Artista>();
					sql = "select * from usuarios where idbanda = ?";
					pst = con.prepareStatement(sql);
					pst.setString(1, rs.getString("identificador"));
					ResultSet rs2 = pst.executeQuery();
					while (rs2.next()) {
						Artista a = new Artista(rs2.getString("identificador"), rs2.getString("nome"), rs2.getString("email"),
								rs2.getString("senha"));
						artistas.add(a);
					}
					Usuario u = new Banda(rs.getString("identificador"), rs.getString("nome"), rs.getString("email"),
							rs.getString("senha"), artistas);
					pst.close();
					return u;
					
				}
			} else {
				pst.close();
				throw new EstiloNaoCadastrado();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new UsuarioNaoCadastrado();
	}
	
	public void remover (String idUsu) throws UsuarioNaoCadastrado{
		pesquisar(idUsu);
		try {
			Connection con = Conexao.getConexao();
			String sql = "delete from usuarios where identificador = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, idUsu);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerTodos() {
		try {
			Connection con = Conexao.getConexao();
			String sql = "delete from usuarios";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Boolean usuarioIsValid(Usuario u) throws ValorInvalido {
		if (u.getIdUsuario() == null || u.getIdUsuario().equals("")) {
			throw new ValorInvalido();
		} else if (u.getNome() == null || u.getNome().equals("")) {
			throw new ValorInvalido();
		} else if (u.getSenha() == null || u.getSenha().equals("")) {
			throw new ValorInvalido();
		} else if (u.getEmail() == null || u.getEmail().equals("")) {
			throw new ValorInvalido();
		}
		return true;
	}
}
