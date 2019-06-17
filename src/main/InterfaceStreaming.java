package main;

import java.util.ArrayList;
import java.util.Date;

import estilos.EstiloJaCadastrado;
import estilos.EstiloNaoCadastrado;
import musicas.Musica;
import musicas.MusicaJaCadastrada;
import musicas.MusicaNaoCadastrada;
import playlists.Playlist;
import playlists.PlaylistExistente;
import playlists.PlaylistNaoExistente;
import usuarios.Usuario;
import usuarios.UsuarioJaCadastrado;
import usuarios.UsuarioNaoCadastrado;
import exceptions.*;

public interface InterfaceStreaming {

	public void cadastrarEstilo(String nome) throws ValorInvalido, EstiloJaCadastrado;

	// Pesquisa musicas com determinado estilo, independente do artista
	public ArrayList<Musica> pesquisarPorEstilo(String nome);

	// Pesquisas musicas com data de lancamento maior que a data informada como
	// parametro
	public ArrayList<Musica> pesquisarPorData(Date inicial);

	// Retorna todas as musicas de um artista
	public ArrayList<Musica> pesquisarPorArtista(String nome);

	// Permite cadastrar tanto usuarios (assinantes) como artistas (individual ou
	// bandas).
	// O identificador do usuario deve ser unico.
	public void cadastrarUsuario(Usuario usuario) throws ValorInvalido, UsuarioJaCadastrado;

	// Permite o cadastro de uma musica no arcervo, tornando-a disponivel para
	// inclusao nas playlists dos
	// usuarios do UFPI Music. Uma musica so e igual a outra se for do mesmo artista
	// (nome do usuario ou nome da banda) e contiver o mesmo nome. So artistas podem
	// adicionar musicas ao acervo do UFPIMusic. Tentativa de cadastro de musicas
	// por usuarios nao permitidos devem gerar excecao UsuarioNaoCadastrado.
	public void adicionarMusica(String idUsu, String nomeMusica, String estilo, String link, int duracao,
			Date lancamento) throws ValorInvalido, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado;

	// Permite a criacao de uma playlist para um usuario. Uma playlist so e
	// considerada igual a outra se tiver
	// o mesmo nome para um determinado usuario. Playlists com mesmo nome para
	// usu�rios diferentes sao consideradas diferentes!
	public void criarPlaylist(String idUsu, String nomeLista)
			throws ValorInvalido, UsuarioNaoCadastrado, PlaylistExistente;

	// Adiciona musica a uma playlist de um usuario.
	public void adicionarMusicaPlaylist(String idUsu, String nomeLista, String nomeAutor, String nomeMusica)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente, MusicaNaoCadastrada, MusicaJaCadastrada;

	// Retorna uma playlist de um usuario. Se nao houver, gera excecao.
	public Playlist pesquisaPlaylistUsuario(String idUsu, String nomeLista)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente;

	// Retorna todas as playlists de um usuario com um dado estilo.
	// Se nao houver nenhuma com aquele estilo, retorna excecao.
	public ArrayList<Playlist> pesquisaPlaylistEstilo(String idUsu, String estilo)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente;
	
	//O primeiro deles é um método para excluir um estilo. Porém, a exclusão de um estilo não 
	// significa apenas excluir o estilo da tabela de estilos. Todas as músicas ligadas a um estilo 
	// precisam ser excluídas também. Assim, as músicas de um estilo a ser excluído, que estejam na 
	// biblioteca de músicas do UFPIMusic, precisam ser excluídas também. 
	public void removerEstilo(String nomeEstilo) throws EstiloNaoCadastrado;
	
	// Outro método de apoio é a junção de 2 usuários em um só. Para isso, todas as playlists dos 
	// dois usuários devem ser associadas ao usuário novo. Por exemplo, se tivermos o usuário Pedro e 
	// outro Pasn, posso desejar unir os dois e gerar um novo usuário “Pedro Santos Neto”, contendo as
	// playlists existentes nos dois usuários e deixando apenas o usuário novo ao final da operação. 
	// Para efeito de simplificação, ignore o fato de haver 2 playlists com nomes iguais neste momento.
	public void juntarUsuarios (String idUsu1, String idUsu2, String idUsuNovo) throws UsuarioNaoCadastrado;

}
