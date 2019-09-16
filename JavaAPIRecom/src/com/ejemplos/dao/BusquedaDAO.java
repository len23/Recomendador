package com.ejemplos.dao;

import java.util.ArrayList;
import java.util.List;
import com.ejemplos.modelo.Busqueda;

public class BusquedaDAO {
	
	private List<Busqueda> resultados = new ArrayList<>();

	public List<Busqueda> getResultados() {
		return resultados;
	}

	public void setResultados(List<Busqueda> resultados) {
		this.resultados = resultados;
	}
	
	public void agregarResultados(Busqueda bus) {
		System.out.println("Agregando: " + bus.getTituloArticulo() );
		this.resultados.add(bus);
		
	}

}
