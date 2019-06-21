package it.unibs.esame;

public class Statistica {
	
	private int id;
	private String nome;
	private int valore;
	
	public Statistica(int id, String name, int valore) {
		super();
		this.id = id;
		this.nome = name;
		this.valore = valore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public int getValore() {
		return valore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}
	
	

}
