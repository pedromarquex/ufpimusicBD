package estilos;

@SuppressWarnings("serial")
public class EstiloNaoCadastrado extends Exception {
	public EstiloNaoCadastrado() {
		super("Estilo não cadastrado");
	}
}
