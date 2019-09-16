package com.ejemplos.modelo;

public class Articulo {
	
	private String titulo;
	private String abstracto;
	
	public Articulo(String titulo, String abstracto) {
		super();
		this.titulo = titulo;
		this.abstracto = abstracto;
	}
	
	public Articulo() {
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAbstracto() {
		return abstracto;
	}

	public void setAbstracto(String abstracto) {
		this.abstracto = abstracto;
	}

}
