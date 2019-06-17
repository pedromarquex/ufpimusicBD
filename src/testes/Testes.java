package testes;

import static org.junit.Assert.fail;

import org.junit.Test;

import estilos.DAOEstilo;
import estilos.EstiloJaCadastrado;
import exceptions.ValorInvalido;

public class Testes {
	@Test
	public void TesteCadastroEstilo() {
		DAOEstilo daoe = new DAOEstilo();
		daoe.removerTodos();
		try {
			daoe.inserir("rap");
		} catch (EstiloJaCadastrado | ValorInvalido e) {
			fail("Nao era esperada nenhuma exceção");
		}
	}
	
	@Test 
	public void TesteCadastroEstiloJaCadastrado() {
		DAOEstilo daoe = new DAOEstilo();
		daoe.removerTodos();
		try {
			daoe.inserir("rap");
			daoe.inserir("rap");
		} catch (EstiloJaCadastrado | ValorInvalido e) {
			// Ok, a exceção era esperada
		}
	}
	
	@Test
	public void TesteCadastroEstiloValorInvalido() {
		DAOEstilo daoe = new DAOEstilo();
		daoe.removerTodos();
		try {
			daoe.inserir("");
			fail("Era esperada a exceção ValorInvalido");
		} catch (EstiloJaCadastrado | ValorInvalido e) {
			// Ok, a exceção era esperada
		}
		try {
			daoe.inserir(null);
			fail("Era esperada a exceção ValorInvalido");
		} catch (EstiloJaCadastrado | ValorInvalido e) {
			// Ok, a exceção era esperada
		}
	}
	
	@Test
	public void TesteCadastroUsuario() {
		
	}
}
