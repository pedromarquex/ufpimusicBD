package usuarios;

import java.util.ArrayList;

public class Banda extends Usuario {
	ArrayList<Artista> componentes;
	
	public Banda(String idUsuario, String nome, String email, String senha, ArrayList<Artista> artistas) {
		super(idUsuario, nome, email, senha);
		this.setComponentes(artistas);
	}

	public ArrayList<Artista> getComponentes() {
		return componentes;
	}

	public void setComponentes(ArrayList<Artista> componentes) {
		this.componentes = componentes;
	}
}
