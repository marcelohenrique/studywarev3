package br.com.guarasoft.studyware.excecao;

public class CampoObrigatorioNaoInformado extends RuntimeException {

	private static final long serialVersionUID = -6799832538623034592L;

	private final String campo;

	public CampoObrigatorioNaoInformado(String campo) {
		super();
		this.campo = campo;
	}

	@Override
	public String getMessage() {
		return "O campo " + this.campo + " é obrigatório";
	}

}
