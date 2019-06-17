package estilos;

@SuppressWarnings("serial")
public class EstiloJaCadastrado extends Exception {
	public EstiloJaCadastrado() {
		super("Estilo já cadastrado");
	}
}
