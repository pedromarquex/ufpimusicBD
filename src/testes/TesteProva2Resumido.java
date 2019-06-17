package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import main.*;
import estilos.*;
import exceptions.*;
import playlists.*;
import usuarios.*;
import musicas.*;

public class TesteProva2Resumido {

	@Test

	public void testeCadastroUsuario() {

		InterfaceStreaming ufpi = new UFPIMusic();

		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "testando");

		try { // Cadastrando assinante

			ufpi.cadastrarUsuario(assinante);

		} catch (ValorInvalido | UsuarioJaCadastrado e) {

			fail("N?o era esperada nenhuma exce??o.");

		}

		Usuario artista = new Artista("MarquinhosSantana", "Marquinhos Santana", "contato@ms.com", "mark14");

		try { // Cadastrando artista

			ufpi.cadastrarUsuario(artista);

		} catch (ValorInvalido | UsuarioJaCadastrado e) {

			fail("N?o era esperada nenhuma exce??o.");

		}

		Artista artista2 = new Artista("JoseSoares", "Jos? Soares", "contato@js.com", "jj90");

		ArrayList<Artista> artistas = new ArrayList<Artista>();

		artistas.add(artista2);

		Usuario banda = new Banda("ForroBoys", "Forr? Boys", "contato@fb.com", "forrozinho", artistas);

		try { // Cadastrando banda

			ufpi.cadastrarUsuario(banda);

		} catch (ValorInvalido | UsuarioJaCadastrado e) {

			fail("N?o era esperada nenhuma exce??o.");

		}

	}

	@Test

	public void testeCadastroUsuarioJaCadastrado() throws ValorInvalido, UsuarioJaCadastrado {

		InterfaceStreaming ufpi = new UFPIMusic();

		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "testando");

		ufpi.cadastrarUsuario(assinante);

		try {

			ufpi.cadastrarUsuario(assinante);

			fail("Exce??o UsuarioJaCadastrado esperada.");

		} catch (UsuarioJaCadastrado e) {

// Ok, era pra dar erro mesmo.

		}

	}

	@Test

	public void testeCadastroUsuarioStringIdentificadorInvalido() throws UsuarioJaCadastrado {

		InterfaceStreaming ufpi = new UFPIMusic();

		Usuario assinante = new Assinante(null, "Francisco", "francisco12@gmail.com", "testando");

		try { // Testando para identificador nulo

			ufpi.cadastrarUsuario(assinante);

			fail("Exce??o ValorInvalido esperada.");

		} catch (ValorInvalido e) {

// Ok, era pra dar erro mesmo.

		}

		Usuario assinante2 = new Assinante("", "Francisco", "francisco12@gmail.com", "testando");

		try { // Testando para identificador vazio

			ufpi.cadastrarUsuario(assinante2);

			fail("Exce??o ValorInvalido esperada.");

		} catch (ValorInvalido e) {

// Ok, era pra dar erro mesmo.

		}

	}

	@Test

	public void testeCadastroBandaComArtistaInvalido() {

		InterfaceStreaming ufpi = new UFPIMusic();

		ArrayList<Artista> artistas = new ArrayList<Artista>();

		Usuario banda = new Banda("ForroBoys", "Forr? Boys", "contato@fb.com", "forrozinho", null);

		Usuario banda2 = new Banda("OsTrakinas", "Os trakinas", "contato@otks.com", "trakinas55", artistas);

		try { // Cadastrando banda com o arraylist nulo

			ufpi.cadastrarUsuario(banda);

			fail("Era esperada a exce??o ValorInvalido.");

		} catch (UsuarioJaCadastrado e) {

			fail("Era esperada a exce??o ValorInvalido.");

		} catch (ValorInvalido e) {

//Ok, era pra dar erro mesmo.

		}

		try { // Cadastrando banda com o arraylist sem artistas

			ufpi.cadastrarUsuario(banda2);

			fail("Era esperada a exce??o ValorInvalido.");

		} catch (UsuarioJaCadastrado e) {

			fail("Era esperada a exce??o ValorInvalido.");

		} catch (ValorInvalido e) {

//Ok, era pra dar erro mesmo.

		}

	}

	@Test

	public void testeCadastroEstilo() {

		InterfaceStreaming ufpi = new UFPIMusic();

		String estilo = "Rock";

		try {

			ufpi.cadastrarEstilo(estilo);

		} catch (ValorInvalido | EstiloJaCadastrado e) {

			fail("Nenhuma exce??o era esperada.");

		}

	}

	@Test

	public void testeCadastroPlayList() throws ValorInvalido, UsuarioJaCadastrado {

		InterfaceStreaming ufpi = new UFPIMusic();

		String nomePlayList = "Os desesperados";

		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "testando");

		ufpi.cadastrarUsuario(assinante);

		try {

			ufpi.criarPlaylist("Chiquinho12", nomePlayList);

		} catch (UsuarioNaoCadastrado | PlaylistExistente e) {

			fail("N?o era esperada nenhuma exce??o");

		}

	}

	@Test

	public void testeCadastroPlayListNomeInvalido() throws ValorInvalido, UsuarioJaCadastrado {

		InterfaceStreaming ufpi = new UFPIMusic();

		String nomePlayList = null;

		String nomePlayList2 = "";

		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "testando");

		ufpi.cadastrarUsuario(assinante);

		try { // Testando para string nula

			ufpi.criarPlaylist("Chiquinho12", nomePlayList);

			fail("Era esperada a exce??o ValorInvalido");

		} catch (UsuarioNaoCadastrado | PlaylistExistente e) {

			fail("Era esperada a exce??o ValorInvalido");

		} catch (ValorInvalido e) {

// Ok,era pra dar erro mesmo.

		}

		try { // Testando para string vazia

			ufpi.criarPlaylist("Chiquinho12", nomePlayList2);

			fail("Era esperada a exce??o ValorInvalido");

		} catch (UsuarioNaoCadastrado | PlaylistExistente e) {

			fail("Era esperada a exce??o ValorInvalido");

		} catch (ValorInvalido e) {

// Ok,era pra dar erro mesmo.

		}

	}

	@Test

	public void testeCadastroPlayListJaExistente()

			throws ValorInvalido, UsuarioJaCadastrado, UsuarioNaoCadastrado, PlaylistExistente {

		InterfaceStreaming ufpi = new UFPIMusic();

		String nomePlayList = "Os desesperados";

		Usuario assinante = new Assinante("Chiquinho12", "Francisco", "francisco12@gmail.com", "testando");

		ufpi.cadastrarUsuario(assinante);

		ufpi.criarPlaylist("Chiquinho12", nomePlayList);

		try {

			ufpi.criarPlaylist("Chiquinho12", nomePlayList);

			fail("Exce??o PlayListExistente esperada");

		} catch (UsuarioNaoCadastrado | ValorInvalido e) {

			fail("Exce??o PlayListExistente esperada");

		} catch (PlaylistExistente e) {

// Ok,era pra dar erro mesmo.

		}

	}

	@Test

	public void testeAdicionarMusicaAssinante()
			throws ValorInvalido, MusicaJaCadastrada, EstiloJaCadastrado, EstiloNaoCadastrado, UsuarioJaCadastrado {

		InterfaceStreaming ufpiMusic = new UFPIMusic();

		Usuario assinante = new Assinante("antonio", "Antonio", "antionio@mail.com", "senha1234");

		Usuario artista = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");

		Artista artista2 = new Artista("JoseSoares", "Jos? Soares", "contato@js.com", "jj90");

		ArrayList<Artista> artistas = new ArrayList<Artista>();

		artistas.add(artista2);

		Usuario banda = new Banda("ForroBoys", "Forr? Boys", "contato@fb.com", "forrozinho", artistas);

		ufpiMusic.cadastrarUsuario(banda);

		ufpiMusic.cadastrarUsuario(assinante);

		ufpiMusic.cadastrarUsuario(artista);

		ufpiMusic.cadastrarEstilo("MPB");

		GregorianCalendar gregorianCalendar = new GregorianCalendar();

		gregorianCalendar.set(2019, 02, 20);

		long milisegundos = gregorianCalendar.getTimeInMillis();

		Date lancamento = new Date(milisegundos);

		try {

			ufpiMusic.adicionarMusica("antonio", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426,
					lancamento);

			fail("Erro: era esperada a exce??o UsuarioNaoCadastrado.");

		}

		catch (UsuarioNaoCadastrado e) {

//A exce??o UsuarioNaoCadastrado realmente era esperada, um assinante n?o pode adicionar m?sicas ao UFPI Music.

		}

		try {

			ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426,
					lancamento);

		}

		catch (UsuarioNaoCadastrado e) {

			fail("Nenhuma exce??o era esperada.");

		}

		try {

			ufpiMusic.adicionarMusica("ForroBoys", "Revanche", "MPB", "https://www.ufpimusic/ForroBoys/revanche.mp3",
					426, lancamento);

		}

		catch (UsuarioNaoCadastrado e) {

			fail("Nenhuma exce??o era esperada.");

		}

	}

	@Test

	public void testePesquisarPorEstilo() throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado,
			UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado {

		InterfaceStreaming ufpiMusic = new UFPIMusic();

		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");

		Usuario artista2 = new Artista("alceu", "Alceu Valen?a", "alceu@mail.com", "seualceu");

		ufpiMusic.cadastrarUsuario(artista1);

		ufpiMusic.cadastrarUsuario(artista2);

		ufpiMusic.cadastrarEstilo("MPB");

		GregorianCalendar gregorianCalendar = new GregorianCalendar();

		gregorianCalendar.set(2019, 02, 20);

		long milisegundos = gregorianCalendar.getTimeInMillis();

		Date lancamento = new Date(milisegundos);

		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426,
				lancamento);

		ufpiMusic.adicionarMusica("alceu", "Cora??o Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412,
				lancamento);

		ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);

		ArrayList<Musica> musicas = new ArrayList<Musica>();

		musicas = ufpiMusic.pesquisarPorEstilo("");

		if (musicas.size() > 0) {

			fail("N?o esperada nenhuma m?sica ao buscar um estilo com nome vazio.");

		}

		musicas = ufpiMusic.pesquisarPorEstilo("MPB");

		if (musicas.size() != 3) {

			fail("Deveria retornar exatamente 3 m?sicas.");

		}

	}

	@Test

	public void testePesquisarPorData() throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado,
			UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado {

		InterfaceStreaming ufpiMusic = new UFPIMusic();

		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");

		Usuario artista2 = new Artista("alceu", "Alceu Valen?a", "alceu@mail.com", "seualceu");

		ufpiMusic.cadastrarUsuario(artista1);

		ufpiMusic.cadastrarUsuario(artista2);

		ufpiMusic.cadastrarEstilo("MPB");

		GregorianCalendar gregorianCalendar = new GregorianCalendar();

		gregorianCalendar.set(2019, 02, 20);

		long milisegundos = gregorianCalendar.getTimeInMillis();

		Date lancamento1 = new Date(milisegundos);

		gregorianCalendar.set(2018, 06, 20);

		milisegundos = gregorianCalendar.getTimeInMillis();

		Date lancamento2 = new Date(milisegundos);

		gregorianCalendar.set(2017, 06, 20);

		milisegundos = gregorianCalendar.getTimeInMillis();

		Date lancamento3 = new Date(milisegundos);

		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426,
				lancamento1);

		ufpiMusic.adicionarMusica("alceu", "Cora??o Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412,
				lancamento1);

		ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento2);

		ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3",
				430, lancamento3);

		gregorianCalendar.set(2016, 06, 20);

		milisegundos = gregorianCalendar.getTimeInMillis();

		Date lancamento = new Date(milisegundos);

		ArrayList<Musica> musicas = new ArrayList<Musica>();

		musicas = ufpiMusic.pesquisarPorData(lancamento);

		if (musicas.size() != 4) {

			fail("Deveria retornar exatamente 4 m?sicas, j? que todas foram lan?adas depois dessa data.");

		}

		musicas = ufpiMusic.pesquisarPorData(lancamento3);

		if (musicas.size() != 3) {

			fail("Deveria retornar exatamente 3 m?sicas.");

		}

		musicas = ufpiMusic.pesquisarPorData(lancamento2);

		if (musicas.size() != 2) {

			fail("Deveria retornar exatamente 2 m?sicas.");

		}

		musicas = ufpiMusic.pesquisarPorData(lancamento1);

		if (musicas.size() != 0) {

			fail("Deveria retornar exatamente 0 m?sicas, j? que nenhuma m?sica foi lan?ada ap?s essa data.");

		}

	}

	@Test

	public void testePesquisarPorArtista() throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado,
			UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado {

		InterfaceStreaming ufpiMusic = new UFPIMusic();

		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");

		Usuario artista2 = new Artista("alceu", "Alceu Valen?a", "alceu@mail.com", "seualceu");

		ufpiMusic.cadastrarUsuario(artista1);

		ufpiMusic.cadastrarUsuario(artista2);

		ufpiMusic.cadastrarEstilo("MPB");

		GregorianCalendar gregorianCalendar = new GregorianCalendar();

		gregorianCalendar.set(2019, 02, 20);

		long milisegundos = gregorianCalendar.getTimeInMillis();

		Date lancamento = new Date(milisegundos);

		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426,
				lancamento);

		ufpiMusic.adicionarMusica("alceu", "Cora??o Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412,
				lancamento);

		ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);

		ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3",
				430, lancamento);

		ArrayList<Musica> musicas = new ArrayList<Musica>();

		musicas = ufpiMusic.pesquisarPorArtista("");

		if (musicas.size() > 0) {

			fail("N?o deveria retornar nenhuma m?sica para artista com nome vazio.");

		}

		musicas = ufpiMusic.pesquisarPorArtista("zeca");

		if (musicas.size() != 2) {

			fail("Deveria retornar exatamente 2 m?sicas para zeca.");

		}

		musicas = ufpiMusic.pesquisarPorArtista("alceu");

		if (musicas.size() != 2) {

			fail("Deveria retornar exatamente 2 m?sicas para alceu.");

		}

	}

	@Test

	public void testeAdicionarMusicaPlaylist()
			throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, UsuarioNaoCadastrado, EstiloNaoCadastrado,
			PlaylistExistente, PlaylistNaoExistente, MusicaNaoCadastrada, MusicaJaCadastrada {

		InterfaceStreaming ufpiMusic = new UFPIMusic();

		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");

		Usuario artista2 = new Artista("alceu", "Alceu Valen?a", "alceu@mail.com", "seualceu");

		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");

		ufpiMusic.cadastrarUsuario(artista1);

		ufpiMusic.cadastrarUsuario(artista2);

		ufpiMusic.cadastrarUsuario(assinante);

		ufpiMusic.cadastrarEstilo("MPB");

		GregorianCalendar gregorianCalendar = new GregorianCalendar();

		gregorianCalendar.set(2019, 02, 20);

		long milisegundos = gregorianCalendar.getTimeInMillis();

		Date lancamento = new Date(milisegundos);

		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426,
				lancamento);

		ufpiMusic.adicionarMusica("alceu", "Cora??o Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412,
				lancamento);

		ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);

		ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3",
				430, lancamento);

		ufpiMusic.criarPlaylist("carlos", "Classicos");

		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Telegrama");

		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "La Belle de Jour");

		Playlist playlist = ufpiMusic.pesquisaPlaylistUsuario("carlos", "Classicos");

		assertEquals(2, playlist.getMusicas().size());

//if(playlist.getMusicas().getMusicas().size() != 2) {

//fail("A quantidade de m?sicas na playlist deveria ser exatamente 2.");

//}

		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Babylon");

		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "Cora??o Bobo");

		playlist = ufpiMusic.pesquisaPlaylistUsuario("carlos", "Classicos");

		assertEquals(4, playlist.getMusicas().size());

	}

	@Test

	public void testeTempoTotalPlaylist()
			throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, UsuarioNaoCadastrado, MusicaJaCadastrada,
			EstiloNaoCadastrado, PlaylistExistente, PlaylistNaoExistente, MusicaNaoCadastrada {

		InterfaceStreaming ufpiMusic = new UFPIMusic();

		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");

		Usuario artista2 = new Artista("alceu", "Alceu Valen?a", "alceu@mail.com", "seualceu");

		Usuario assinante = new Assinante("carlos", "Carlos Campelo", "carlos@mail.com", "Carlitos");

		ufpiMusic.cadastrarUsuario(artista1);

		ufpiMusic.cadastrarUsuario(artista2);

		ufpiMusic.cadastrarUsuario(assinante);

		ufpiMusic.cadastrarEstilo("MPB");

		GregorianCalendar gregorianCalendar = new GregorianCalendar();

		gregorianCalendar.set(2019, 02, 20);

		long milisegundos = gregorianCalendar.getTimeInMillis();

		Date lancamento = new Date(milisegundos);

		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426,
				lancamento);

		ufpiMusic.adicionarMusica("alceu", "Cora??o Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412,
				lancamento);

		ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);

		ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3",
				430, lancamento);

		ufpiMusic.criarPlaylist("carlos", "Classicos");

		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Telegrama");

		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "La Belle de Jour");

		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "alceu", "Cora??o Bobo");

		ufpiMusic.adicionarMusicaPlaylist("carlos", "Classicos", "zeca", "Babylon");

		Playlist playlist = ufpiMusic.pesquisaPlaylistUsuario("carlos", "Classicos");

		assertEquals(1708, playlist.getDuracaoTotal());

	}

	@Test

	public void testeRemoverEstilo()
			throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, UsuarioNaoCadastrado, MusicaJaCadastrada,
			EstiloNaoCadastrado, PlaylistExistente, PlaylistNaoExistente, MusicaNaoCadastrada {

		UFPIMusic ufpiMusic = new UFPIMusic();

		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");

		Usuario artista2 = new Artista("alceu", "Alceu Valen?a", "alceu@mail.com", "seualceu");

		Usuario artista3 = new Artista("mabel", "Mabel", "mabel@mail.com", "cookies");

		Usuario artista4 = new Artista("anne", "Anne Marie", "annemarie@mail.com", "marieanne");

		ufpiMusic.cadastrarUsuario(artista1);

		ufpiMusic.cadastrarUsuario(artista2);

		ufpiMusic.cadastrarUsuario(artista3);

		ufpiMusic.cadastrarUsuario(artista4);

		ufpiMusic.cadastrarEstilo("MPB");

		ufpiMusic.cadastrarEstilo("Pop");

		GregorianCalendar gregorianCalendar = new GregorianCalendar();

		gregorianCalendar.set(2019, 02, 20);

		long milisegundos = gregorianCalendar.getTimeInMillis();

		Date lancamento = new Date(milisegundos);

		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426,
				lancamento);

		ufpiMusic.adicionarMusica("alceu", "Cora??o Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412,
				lancamento);

		ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);

		ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3",
				430, lancamento);

		ufpiMusic.adicionarMusica("mabel", "Dont Call Me Up", "Pop", "https://www.ufpimusic/mabel/dontCallMeUp.mp3",
				240, lancamento);

		ufpiMusic.adicionarMusica("anne", "2002", "Pop", "https://www.ufpimusic/anne/2002.mp3", 320, lancamento);

		ufpiMusic.adicionarMusica("mabel", "Ring Ring", "Pop", "https://www.ufpimusic/mabel/ringRing.mp3", 400,
				lancamento);

		ufpiMusic.adicionarMusica("anne", "Friends", "Pop", "https://www.ufpimusic/anne/friends.mp3", 320, lancamento);

		ufpiMusic.removerEstilo("MPB");

		assertEquals(0, ufpiMusic.pesquisarPorEstilo("MPB").size());

		assertEquals(4, ufpiMusic.pesquisarPorEstilo("Pop").size());

		ufpiMusic.removerEstilo("Pop");

		assertEquals(0, ufpiMusic.pesquisarPorEstilo("Pop").size());

	}

	@Test

	public void testeJuntarUsuarios()
			throws ValorInvalido, UsuarioJaCadastrado, EstiloJaCadastrado, UsuarioNaoCadastrado, MusicaJaCadastrada,
			EstiloNaoCadastrado, PlaylistExistente, PlaylistNaoExistente, MusicaNaoCadastrada {

		UFPIMusic ufpiMusic = new UFPIMusic();

		Usuario artista1 = new Artista("zeca", "Zeca Baleiro", "zecabaleiro@mail.com", "z3qu1nh4");

		Usuario artista2 = new Artista("alceu", "Alceu Valen?a", "alceu@mail.com", "seualceu");

		Usuario assinante1 = new Assinante("carlosdc", "Carlos Campelo", "carlos@mail.com", "Carlitos");

		Usuario assinante2 = new Assinante("carlosd", "Carlos", "carlosd@mail.com", "CarlitosD");

		Usuario assinante3 = new Assinante("carlos", "Carlos", "carlos@mail.com", "Carlitos");

		ufpiMusic.cadastrarUsuario(artista1);

		ufpiMusic.cadastrarUsuario(artista2);

		ufpiMusic.cadastrarUsuario(assinante1);

		ufpiMusic.cadastrarUsuario(assinante2);

		ufpiMusic.cadastrarUsuario(assinante3);

		ufpiMusic.cadastrarEstilo("MPB");

		GregorianCalendar gregorianCalendar = new GregorianCalendar();

		gregorianCalendar.set(2019, 02, 20);

		long milisegundos = gregorianCalendar.getTimeInMillis();

		Date lancamento = new Date(milisegundos);

		ufpiMusic.adicionarMusica("zeca", "Telegrama", "MPB", "https://www.ufpimusic/zeca/telegrama.mp3", 426,
				lancamento);

		ufpiMusic.adicionarMusica("alceu", "Cora??o Bobo", "MPB", "https://www.ufpimusic/alceu/telegrama.mp3", 412,
				lancamento);

		ufpiMusic.adicionarMusica("zeca", "Babylon", "MPB", "https://www.ufpimusic/zeca/babylon.mp3", 440, lancamento);

		ufpiMusic.adicionarMusica("alceu", "La Belle de Jour", "MPB", "https://www.ufpimusic/alceu/laBelleDeJour.mp3",
				430, lancamento);

		ufpiMusic.criarPlaylist("carlosdc", "Classicos");

		ufpiMusic.adicionarMusicaPlaylist("carlosdc", "Classicos", "zeca", "Telegrama");

		ufpiMusic.adicionarMusicaPlaylist("carlosdc", "Classicos", "alceu", "La Belle de Jour");

		ufpiMusic.adicionarMusicaPlaylist("carlosdc", "Classicos", "alceu", "Cora??o Bobo");

		ufpiMusic.adicionarMusicaPlaylist("carlosdc", "Classicos", "zeca", "Babylon");

		ufpiMusic.criarPlaylist("carlosd", "Zeca Perfil");

		ufpiMusic.adicionarMusicaPlaylist("carlosd", "Zeca Perfil", "zeca", "Telegrama");

		ufpiMusic.adicionarMusicaPlaylist("carlosd", "Zeca Perfil", "zeca", "Babylon");

		ufpiMusic.juntarUsuarios("carlosdc", "carlosd", "carlos");

		try {

			Playlist playlist = ufpiMusic.pesquisaPlaylistUsuario("carlos", "Classicos");

			assertEquals(4, playlist.getMusicas().size());

			playlist = ufpiMusic.pesquisaPlaylistUsuario("carlos", "Zeca Perfil");

			assertEquals(2, playlist.getMusicas().size());

		}

		catch (PlaylistNaoExistente e) {

			fail("A playlist deveria ter sido movida para o novo usu?rio.");

		}

	}

}
