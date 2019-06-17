package musicas;

@SuppressWarnings("serial")
public class MusicaJaCadastrada extends Exception {
	public MusicaJaCadastrada() {
		super("Musica já cadastrada");
	}

}
