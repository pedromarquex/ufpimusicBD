package main;

import java.util.ArrayList;
import java.util.Date;

import estilos.DAOEstilo;
import estilos.EstiloJaCadastrado;
import estilos.EstiloNaoCadastrado;
import musicas.DAOMusicas;
import musicas.Musica;
import musicas.MusicaJaCadastrada;
import musicas.MusicaNaoCadastrada;
import playlists.DAOMusicaPlaylist;
import playlists.DAOPlaylist;
import playlists.Playlist;
import playlists.PlaylistExistente;
import playlists.PlaylistNaoExistente;
import usuarios.Artista;
import usuarios.Assinante;
import usuarios.DAOUsuario;
import usuarios.Usuario;
import usuarios.UsuarioJaCadastrado;
import usuarios.UsuarioNaoCadastrado;
import exceptions.*;

public class UFPIMusic implements InterfaceStreaming {

	@Override
	public void cadastrarEstilo(String nome) throws ValorInvalido, EstiloJaCadastrado {
		try {
			DAOEstilo daoe = new DAOEstilo();
			daoe.inserir(nome);
		} catch (ValorInvalido | EstiloJaCadastrado e) {
			throw e;
		}

	}

	@Override
	public ArrayList<Musica> pesquisarPorEstilo(String nome) {
		DAOMusicas daom = new DAOMusicas();
		return daom.pesquisarPorEstilo(nome);
	}

	@Override
	public ArrayList<Musica> pesquisarPorData(Date inicial) {
		DAOMusicas daom = new DAOMusicas();
		return daom.pesquisarPorData(inicial);
	}

	@Override
	public ArrayList<Musica> pesquisarPorArtista(String nome) {
		DAOMusicas daom = new DAOMusicas();
		return daom.pesquisarPorArtista(nome);
	}

	@Override
	public void cadastrarUsuario(Usuario usuario) throws ValorInvalido, UsuarioJaCadastrado {
		DAOUsuario daou = new DAOUsuario();
		daou.inserir(usuario);
	}

	@Override
	public void adicionarMusica(String idUsu, String nomeMusica, String estilo, String link, int duracao,
			Date lancamento) throws ValorInvalido, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado {
		DAOMusicas daom = new DAOMusicas();
		DAOUsuario daou = new DAOUsuario();
		Artista a = (Artista)daou.pesquisar(idUsu);
		Musica mus = new Musica(a, nomeMusica, estilo, link, duracao, lancamento);
		daom.inserir(mus);
	}

	@Override
	public void criarPlaylist(String idUsu, String nomeLista) throws ValorInvalido, UsuarioNaoCadastrado, PlaylistExistente{
		DAOPlaylist daop = new DAOPlaylist();
		daop.inserir(nomeLista, idUsu);
	}

	@Override
	public void adicionarMusicaPlaylist(String idUsu, String nomeLista, String idAutor, String nomeMusica)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente, MusicaNaoCadastrada, MusicaJaCadastrada {
		DAOMusicaPlaylist daomp = new DAOMusicaPlaylist();
		daomp.inserir(idUsu, nomeLista, nomeMusica, idAutor);
	}

	@Override
	public Playlist pesquisaPlaylistUsuario(String idUsu, String nomeLista)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente {
		DAOPlaylist daop = new DAOPlaylist();
		return daop.pesquisar(nomeLista, idUsu);
	}

	@Override
	public ArrayList<Playlist> pesquisaPlaylistEstilo(String idUsu, String estilo)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente {
		return null;
	}

	@Override
	public void removerEstilo(String nomeEstilo) throws EstiloNaoCadastrado {
		DAOEstilo daoe = new DAOEstilo();
		daoe.remover(nomeEstilo);
		
		DAOMusicas daom = new DAOMusicas();
		daom.removerMusicaEstilo(nomeEstilo);
	}

	@Override
	public void juntarUsuarios(String idUsu1, String idUsu2, String idUsuNovo) throws UsuarioNaoCadastrado {
		DAOUsuario daou = new DAOUsuario();
		Usuario usu1 = daou.pesquisar(idUsu1);
		daou.pesquisar(idUsu2);
		
		Usuario novoAssinante = new Assinante(idUsuNovo, usu1.getNome(), usu1.getEmail(), usu1.getSenha());
		
		try {
			daou.inserir(novoAssinante);
		} catch (UsuarioJaCadastrado | ValorInvalido e) {
			e.printStackTrace();
		}
		
		DAOPlaylist daop = new DAOPlaylist();
		daop.mudaUsuarioPlaylist(idUsu1, idUsu2, idUsuNovo);
		
	}

}
