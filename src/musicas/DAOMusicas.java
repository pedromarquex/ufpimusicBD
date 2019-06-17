package musicas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import conexao.Conexao;
import estilos.DAOEstilo;
import estilos.EstiloNaoCadastrado;
import exceptions.ValorInvalido;
import usuarios.Artista;
import usuarios.DAOUsuario;
import usuarios.UsuarioNaoCadastrado;

public class DAOMusicas {
	public void inserir(Musica m) throws ValorInvalido, MusicaJaCadastrada, UsuarioNaoCadastrado, EstiloNaoCadastrado {
		try {
			try {
				pesquisar(m.getNome(), m.getArtista().getIdUsuario());
				throw new MusicaJaCadastrada();
			} catch (MusicaNaoCadastrada e) {

			}
			DAOEstilo daoe = new DAOEstilo();
			daoe.pesquisar(m.getEstilo());
			DAOUsuario daou = new DAOUsuario();
			daou.pesquisar(m.getArtista().getIdUsuario());
			validaMusica(m);
			Connection con = Conexao.getConexao();
			String sql = "insert into musicas values (?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, m.getNome());
			pst.setString(2, m.getArtista().getNome());
			pst.setInt(3, m.getLancamento().getDate());
			pst.setInt(4, m.getDuracao());
			pst.setString(4, m.getLink());
			pst.setString(6, m.getEstilo());

			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Musica pesquisar(String nomeMusica, String idArtista) throws MusicaNaoCadastrada, UsuarioNaoCadastrado {
		try {
			Connection con = Conexao.getConexao();
			String sql = "select from musicas where nome = ? and artista = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nomeMusica);
			pst.setString(2, idArtista);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				DAOUsuario daou = new DAOUsuario();
				Artista artista = (Artista) daou.pesquisar(idArtista);
				Date lanc = new Date(rs.getLong("lancamento"));
				Musica mus = new Musica(artista, rs.getString("nome"), rs.getString("estilo"), rs.getString("link"),
						rs.getInt("duracao"), lanc);
				return mus;
			} else {
				pst.close();
				throw new MusicaNaoCadastrada();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new MusicaNaoCadastrada();
	}

	public ArrayList<Musica> pesquisarPorEstilo(String nome) {
		ArrayList<Musica> musicas = new ArrayList<Musica>();

		try {
			Connection con = Conexao.getConexao();
			DAOUsuario daou = new DAOUsuario();
			String sql = "select * from musicas where estilo = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nome);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Date lancamento = new Date(rs.getInt("lancamento"));
				Artista artista;
				try {
					artista = (Artista) daou.pesquisar(rs.getString("artista"));
					Musica mus = new Musica(artista, rs.getString("nome"), rs.getString("estilo"), rs.getString("link"),
							rs.getInt("duracao"), lancamento);
					musicas.add(mus);
				} catch (UsuarioNaoCadastrado e) {

				}
			}
		} catch (SQLException e) {

		}
		return musicas;
	}

	public ArrayList<Musica> pesquisarPorData(Date inicial) {
		ArrayList<Musica> musicas = new ArrayList<Musica>();

		try {
			Connection con = Conexao.getConexao();
			DAOUsuario daou = new DAOUsuario();
			String sql = "select * from musicas where lancamento > ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, inicial.getDate());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Date lancamento = new Date(rs.getInt("lancamento"));
				Artista artista;
				try {
					artista = (Artista) daou.pesquisar(rs.getString("artista"));
					Musica mus = new Musica(artista, rs.getString("nome"), rs.getString("estilo"), rs.getString("link"),
							rs.getInt("duracao"), lancamento);
					musicas.add(mus);
				} catch (UsuarioNaoCadastrado e) {

				}
			}
		} catch (SQLException e) {

		}
		return musicas;
	}

	public ArrayList<Musica> pesquisarPorArtista(String nome) {
		ArrayList<Musica> musicas = new ArrayList<Musica>();

		try {
			Connection con = Conexao.getConexao();
			DAOUsuario daou = new DAOUsuario();
			String sql = "select * from musicas where artista = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nome);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Date lancamento = new Date(rs.getInt("lancamento"));
				Artista artista;
				try {
					artista = (Artista) daou.pesquisar(rs.getString("artista"));
					Musica mus = new Musica(artista, rs.getString("nome"), rs.getString("estilo"), rs.getString("link"),
							rs.getInt("duracao"), lancamento);
					musicas.add(mus);
				} catch (UsuarioNaoCadastrado e) {

				}
			}
		} catch (SQLException e) {

		}
		return musicas;
	}

	public void remover(String nomeMusica, String idArtista) throws MusicaNaoCadastrada, UsuarioNaoCadastrado {
		pesquisar(nomeMusica, idArtista);
		try {
			Connection con = Conexao.getConexao();
			String sql = "delete from musicas where nome = ? and artista = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nomeMusica);
			pst.setString(2, idArtista);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerMusicaEstilo(String estilo){
		try {
			Connection con = Conexao.getConexao();
			String sql = "delete from musicas where estilo = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, estilo);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerTudo() {
		try {
			Connection con = Conexao.getConexao();
			String sql = "delete from musicas";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void validaMusica(Musica m) throws ValorInvalido {
		if (m.getNome() == null || m.getNome().equals("")) {
			throw new ValorInvalido();
		} else if (m.getArtista() == null || m.getArtista().getNome() == null || m.getArtista().getNome().equals("")) {
			throw new ValorInvalido();
		} else if (m.getEstilo() == null || m.getEstilo().equals("")) {
			throw new ValorInvalido();
		} else if (m.getLink() == null || m.getLink().equals("")) {
			throw new ValorInvalido();
		} else if (m.getDuracao() < 1) {
			throw new ValorInvalido();
		} else if (m.getLancamento().equals(new Date(0)) || m.getLancamento().after(new Date())) {
			throw new ValorInvalido();
		}
	}

}
