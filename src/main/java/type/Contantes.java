package type;

public enum Contantes {
	
	ACCESS_TOKEN("Bearer d433978bebd2b8e4739b550e484f76fc0dd30d34e80eebdd9949d8eafca235a4"),
	URL_BASE("https://gorest.co.in/public-api/");

	private final String nome;

	private Contantes(String nome) {
		this.nome = nome;
	}
	
	public String getnome(){
		return nome;
	}
}
