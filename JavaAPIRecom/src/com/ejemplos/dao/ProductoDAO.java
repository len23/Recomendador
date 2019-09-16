package com.ejemplos.dao;

import java.util.ArrayList;
import java.util.List;

import com.ejemplos.modelo.*;

public class ProductoDAO {

	public static List<Producto> getProductos(){
		
		List<Producto> lista = new ArrayList<>();
		
		Producto producto = new Producto(1, "Cerveza", 10);
		Producto producto2 = new Producto(2, "Ron", 100);
		Producto producto3 = new Producto(3, "Whisky", 10);
		
		lista.add(producto);
		lista.add(producto2);
		lista.add(producto3);
		
		return lista;
		
	}
}
