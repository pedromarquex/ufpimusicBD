package playlists;

@SuppressWarnings("serial")
public class PlaylistExistente extends Exception {
	public PlaylistExistente() {
		super("Playlist existente");
	}
}
