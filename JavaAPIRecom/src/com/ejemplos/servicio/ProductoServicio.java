package com.ejemplos.servicio;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.yasson.internal.model.JsonbAnnotated;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ContainerResponse;

import javax.json.Json;
import javax.json.JsonObject;
import com.ejemplos.dao.ProductoDAO;
import com.ejemplos.modelo.Producto;

import sun.misc.JavaSecurityProtectionDomainAccess.ProtectionDomainCache;

@Path("productos")
public class ProductoServicio  {
	

	  
	private static List<Producto> lista = ProductoDAO.getProductos();
	
	//@OPTIONS
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductos(){
		//return Response.ok(lista).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();	
		return Response.ok(lista).build();	

	}
	

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Response getProducto(@PathParam("id") int id){
		Producto producto = new Producto();
		producto.setId(id);
		if(lista.contains(producto)) {		
			for(Producto obj : lista)
				if(obj.getId()==id)
			return Response.ok(obj).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response guardarProducto(Producto producto){
		
		System.out.println("Entramos");
		//lista.add(producto);
				/*return Response.status(Response.Status.CREATED).status(200).
				header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "content-type").entity(producto).build();*/
		
		/*return Response .status(200).
				header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.header("Access-Control-Allow-Headers", "content-type").entity("").build();
	*/
		
	return getProductos();
	}


	
}
