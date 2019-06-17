package usuarios;

@SuppressWarnings("serial")
public class UsuarioNaoCadastrado extends Exception {
	public UsuarioNaoCadastrado() {
		super("Usuário não cadastrado");
	}
}
