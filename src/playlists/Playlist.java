package playlists;

import java.util.ArrayList;

import estilos.ListaEstilos;
import musicas.ListaMusicas;
import musicas.Musica;

public class Playlist {
	private String idUsuario;
	private ListaMusicas musicas = new ListaMusicas();
	private String nome;
	private ListaEstilos estilos = new ListaEstilos();
	private int duracaoTotal;
	
	public Playlist(String idUsuario, String nome) {
		this.setIdUsuario(idUsuario);
		this.setNome(nome);
		this.duracaoTotal = 0;
	}
	
	public ListaMusicas getMusicas() {
		return musicas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ListaEstilos getEstilos() {
		return estilos;
	}

	public int getDuracaoTotal() {
		this.duracaoTotal = 0;
		ArrayList<Musica> mus = musicas.getMusicas();
		if (mus.isEmpty()) {
			return 0;
		} else {
			for(Musica musi: mus) {
				duracaoTotal += musi.getDuracao();
			}
		}
		return duracaoTotal;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
}
