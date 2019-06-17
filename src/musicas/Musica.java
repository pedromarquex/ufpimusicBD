package musicas;

import java.util.Date;

import usuarios.Artista;
import usuarios.UsuarioNaoCadastrado;

public class Musica {
	private String nome;
	private Artista artista;
	private Date lancamento;
	private int duracao;
	private String estilo;
	private String link;

	public Musica(Artista artista, String nome, String estilo, String link, int duracao, Date lancamento) throws UsuarioNaoCadastrado{
		this.setArtista(artista);
		this.setNome(nome);
		this.setEstilo(estilo);
		this.setLink(link);
		this.setDuracao(duracao);
		this.setLancamento(lancamento);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public Date getLancamento() {
		return lancamento;
	}

	public void setLancamento(Date lancamento) {
		this.lancamento = lancamento;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
