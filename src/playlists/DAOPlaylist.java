package playlists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao;
import exceptions.ValorInvalido;
import usuarios.DAOUsuario;
import usuarios.UsuarioNaoCadastrado;

public class DAOPlaylist {
	public void inserir(String nome, String idUsu) throws UsuarioNaoCadastrado, ValorInvalido, PlaylistExistente {
		try {
			try {
				pesquisar(nome, idUsu);
				throw new PlaylistExistente();
			} catch (PlaylistNaoExistente e) {

			}
			DAOUsuario daou = new DAOUsuario();
			daou.pesquisar(idUsu);
			Connection con = Conexao.getConexao();
			String sql = "insert into playlists values (?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, idUsu);
			pst.setString(2, nome);
			pst.executeUpdate();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Playlist pesquisar(String nome, String idUsu) throws PlaylistNaoExistente {
		try {
			Connection con = Conexao.getConexao();
			String sql = "select * from playlists where nome = ? and usuario = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nome);
			pst.setString(2, idUsu);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Playlist p = new Playlist(rs.getString("usuario"), rs.getString("nome"));
				return p;
			} else {
				throw new PlaylistNaoExistente();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new PlaylistNaoExistente();
	}

	public ArrayList<Playlist> pesquisaPlaylistEstilo(String idUsu, String estilo)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente {
		return null;
	}

	public void remover(String nome, String idUsu) throws PlaylistNaoExistente {
		pesquisar(nome, idUsu);
		try {
			Connection con = Conexao.getConexao();
			String sql = "delete from playlists where nome = ? and usuario = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nome);
			pst.setString(2, idUsu);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void mudaUsuarioPlaylist(String idUsu1, String idUsu2, String idUsuNovo) {
		try {
			Connection con = Conexao.getConexao();
			String sql = "update playlists set usuario=? where usuario=? or usuario=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, idUsuNovo);
			pst.setString(2, idUsu1);
			pst.setString(3, idUsu2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerTodos() {
		try {
			Connection con = Conexao.getConexao();
			String sql = "delete from playlists";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
