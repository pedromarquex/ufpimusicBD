package musicas;

import java.util.ArrayList;

import exceptions.ValorInvalido;

public class ListaMusicas {
	private ArrayList<Musica> musicas = new ArrayList<Musica>();

	public void adiciona(Musica musica) throws MusicaJaCadastrada, ValorInvalido {
		if (musica.getNome() == null || musica.getNome().equals("")) {
			throw new ValorInvalido();
		}
		try {
			this.procura(musica.getNome(), musica.getArtista().getNome());
			throw new MusicaJaCadastrada();
		} catch (MusicaNaoCadastrada e) {
			musicas.add(musica);
		}
	}

	public Musica procura(String nomeMusica, String idAutor) throws MusicaNaoCadastrada {
		if (musicas.isEmpty()) {
			throw new MusicaNaoCadastrada();
		} else {
			for (Musica musica : musicas) {
				if (musica.getNome().equals(nomeMusica) && musica.getArtista().getIdUsuario().equals(idAutor)) {
					return musica;
				}
			}
			throw new MusicaNaoCadastrada();
		}
	}

	public ArrayList<Musica> getMusicas() {
		return musicas;
	}
}
