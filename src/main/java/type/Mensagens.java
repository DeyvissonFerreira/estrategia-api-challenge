package type;

public enum Mensagens {
	
	CAMPO_NAO_PODE_ESTAR_EM_BRANCO("can't be blank"),
	DADO_JA_UTILIZADO("has already been taken"),
	USUARIO_NAO_ENCONTRADO("Resource not found"),
	AUTHENTICATION_FAILED("Authentication failed");

	private final String mensagem;

	private Mensagens(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getMensagem(){
		return mensagem;
	}
}
