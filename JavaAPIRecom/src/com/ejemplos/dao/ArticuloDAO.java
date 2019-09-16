package com.ejemplos.dao;

import java.util.ArrayList;
import java.util.List;

import com.ejemplos.modelo.Articulo;
import com.ejemplos.modelo.Producto;

public class ArticuloDAO {
	
	private List<Articulo> articulos = new ArrayList<>();

	
	public ArticuloDAO(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public ArticuloDAO() {
		
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	/*
	public void agregarArticulos(Articulo articulo) {
		
		this.articulos.add(articulo);
		
	}

	public  List<Articulo> getArticulos(){
		
		//List<Articulo> lista = new ArrayList<>();
		
		Articulo articulo1 = new Articulo("Transparent redundancy in the time-triggered architecture", "The time-triggered architecture is an architecture for distributed embedded real-time systems in high dependability applications. The core element of the architecture is the time-triggered communications protocol TTP/C. This paper shows how TTP/C can be extended by a fault-tolerance layer that performs those functions that are necessary for the implementation of application redundancy. The hardware/software interface of the host computer where the application software is executing, is not changed, neither in the value domain, nor in the temporal domain, by this implementation of fault-tolerance in the communications system. Provided the application software has been properly organized it is thus possible to implement application redundancy transparently, i.e., without any modification of the function and timing of the application system. The paper also discusses the experiences gained from a prototype implementation of the fault-tolerance layer in the microprogram of a TTP/C controller chip");
		Articulo articulo2 = new Articulo("Resource scheduling in dependable integrated modular avionics", "In the recent development of avionics systems, integrated modular avionics (IMA) is advocated for next generation architecture that needs integration of mixed criticality real-time applications. These integrated applications meet their own timing constraints while sharing avionics computer resources. To guarantee timing constraints and dependability of each application, an IMA-based system is equipped with the schemes for spatial and temporal partitioning. We refer the model as SP-RTS (strongly partitioned real-time system), which deals with processor partitions and communication channels as its basic scheduling entities. This paper presents a partition and channel-scheduling algorithm for the SP-RTS. The basic idea of the algorithm is to use a two-level hierarchical schedule that activates partitions (or channels) following a distance-constraints guaranteed cyclic schedule and then dispatches tasks (or messages) according to a fixed priority schedule. To enhance schedulability, we devised heuristic algorithms for deadline decomposition and channel combining. The simulation results show the schedulability analysis of the two-level scheduling algorithm and the beneficial characteristics of the proposed deadline decomposition and channel combining algorithms");

	
		lista.add(articulo1);
		lista.add(articulo2);
		
		return articulos;
		
	}*/
}
