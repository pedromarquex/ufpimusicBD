package estilos;

import java.util.ArrayList;

import exceptions.ValorInvalido;

public class ListaEstilos {
	private ArrayList<String> estilos = new ArrayList<String>();

	public void adiciona(String nome) throws EstiloJaCadastrado, ValorInvalido {
		if (nome == null || nome == "") {
			throw new ValorInvalido();
		} else {
			try {
				this.procura(nome);
				throw new EstiloJaCadastrado();
			} catch (EstiloNaoCadastrado e) {
				this.estilos.add(nome);
			}
		}
	}

	public String procura(String nome) throws EstiloNaoCadastrado {
		if (this.estilos.isEmpty()) {
			throw new EstiloNaoCadastrado();
		} else {
			for (String estilo : estilos) {
				if (estilo.equals(nome)) {
					return estilo;
				}
			}
			throw new EstiloNaoCadastrado();
		}
	}

	public ArrayList<String> getEstilos() {
		return estilos;
	}

}
