package playlists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.Conexao;
import musicas.DAOMusicas;
import musicas.MusicaJaCadastrada;
import musicas.MusicaNaoCadastrada;
import usuarios.DAOUsuario;
import usuarios.UsuarioNaoCadastrado;

public class DAOMusicaPlaylist {
	public void inserir(String idUsu, String playlist, String musica, String artista)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente, MusicaNaoCadastrada, MusicaJaCadastrada {
		pesquisar(idUsu, playlist, musica, artista);
		try {
			Connection con = Conexao.getConexao();
			String sql = "insert into musicasplaylists values (?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, idUsu);
			pst.setString(2, playlist);
			pst.setString(3, musica);
			pst.setString(4, artista);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void pesquisar(String idUsu, String playlist, String musica, String artista)
			throws UsuarioNaoCadastrado, MusicaNaoCadastrada, PlaylistNaoExistente, MusicaJaCadastrada {
		DAOUsuario daou = new DAOUsuario();
		DAOPlaylist daop = new DAOPlaylist();
		DAOMusicas daom = new DAOMusicas();
		daou.pesquisar(idUsu);
		daou.pesquisar(artista);
		daom.pesquisar(musica, artista);
		daop.pesquisar(playlist, idUsu);

		try {
			Connection con = Conexao.getConexao();
			String sql = "select * from musicasplaylists where usuario = ? and playlist = ? and "
					+ "musica = ? and artista = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, idUsu);
			pst.setString(2, playlist);
			pst.setString(3, musica);
			pst.setString(4, artista);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				pst.close();
			} else {
				pst.close();
				throw new MusicaJaCadastrada();
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}

	public void removerTodos() {
		try {
			Connection con = Conexao.getConexao();
			String sql = "delete from musicasplaylists";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
