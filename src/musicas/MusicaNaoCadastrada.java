package musicas;

@SuppressWarnings("serial")
public class MusicaNaoCadastrada extends Exception {
	public MusicaNaoCadastrada() {
		super("Musica não cadastrada");
	}
}
