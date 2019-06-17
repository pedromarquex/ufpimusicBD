package playlists;

@SuppressWarnings("serial")
public class PlaylistNaoExistente extends Exception {
	public PlaylistNaoExistente() {
		super("Playlist não existente");
	}
}
