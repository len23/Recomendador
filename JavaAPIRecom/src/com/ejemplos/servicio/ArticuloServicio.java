package com.ejemplos.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ejemplos.dao.ArticuloDAO;
import com.ejemplos.dao.BusquedaDAO;
import com.ejemplos.modelo.Articulo;
import com.ejemplos.modelo.Busqueda;
import com.ejemplos.modelo.Producto;
import com.recomendador.clases.LuceneTester;
import com.sun.org.apache.bcel.internal.generic.AALOAD;

import java.io.IOException;

import org.apache.catalina.ant.ListTask;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;



@Path("articulos")

public class ArticuloServicio {
	
	private ArticuloDAO artDao;	
	private BusquedaDAO busquedaDAO;
	//private  List<Articulo> lista = new ArrayList<>();
	private String indexDir = "Indexados";
	private String indexDirACM = "IndexadosACM";
	private String indexDirDBLP = "IndexadosDBLP";
	private  LuceneTester tester;
	
	
	
	
	//Busqueda para IEEE
	@Path("/getBusquedaIEEE")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBusqueda(@QueryParam("Busqueda") String txtBusqueda){
		System.out.println("Texto Busqueda: "+txtBusqueda);
		busquedaDAO= new BusquedaDAO();
		//Desde aquí se va a realizar la busqueda
		  //LuceneTester tester;
		      try {		          
		          tester = new LuceneTester();
		          tester.search(txtBusqueda,busquedaDAO,indexDir);
		          //tester.search("J. Palmer");
		          //tester.sortUsingRelevance("ieee-compendium-dvd-filenames");
		          //tester.sortUsingIndex("H. Kopetz");
		      } catch (IOException e) {
		          e.printStackTrace();
		      } catch (ParseException e) {
		          e.printStackTrace();
		      }	
		
		return Response.ok(busquedaDAO.getResultados()).build();	
	}
	
	//Busqueda para ACM
	@Path("/getBusquedaACM")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBusquedaACM(@QueryParam("Busqueda") String txtBusqueda){
		System.out.println("Texto Busqueda: "+txtBusqueda);

		busquedaDAO= new BusquedaDAO();
		//Desde aquí se va a realizar la busqueda
		 // LuceneTester tester;
		      try {		          
		          tester = new LuceneTester();
		          tester.search(txtBusqueda,busquedaDAO,indexDirACM);
		          //tester.search("J. Palmer");
		          //tester.sortUsingRelevance("ieee-compendium-dvd-filenames");
		          //tester.sortUsingIndex("H. Kopetz");
		      } catch (IOException e) {
		          e.printStackTrace();
		      } catch (ParseException e) {
		          e.printStackTrace();
		      }	
		
		return Response.ok(busquedaDAO.getResultados()).build();	
	}
	
	//Busqueda para DBLP
	@Path("/getBusquedaDBLP")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBusquedaDBLP(@QueryParam("Busqueda") String txtBusqueda){
		System.out.println("Texto Busqueda: "+txtBusqueda);
		busquedaDAO= new BusquedaDAO();
		//Desde aquí se va a realizar la busqueda
		 // LuceneTester tester;
		      try {		          
		          tester = new LuceneTester();
		          tester.search(txtBusqueda,busquedaDAO,indexDirDBLP);
		          //tester.search("J. Palmer");
		          //tester.sortUsingRelevance("ieee-compendium-dvd-filenames");
		          //tester.sortUsingIndex("H. Kopetz");
		      } catch (IOException e) {
		          e.printStackTrace();
		      } catch (ParseException e) {
		          e.printStackTrace();
		      }	
		
		return Response.ok(busquedaDAO.getResultados()).build();	
	}
	
	//POST para IEEE
	@POST
	@Produces(MediaType.APPLICATION_JSON)	
	@Consumes(MediaType.APPLICATION_JSON)	
	@Path("/indexIEEE")
	public Response guardarArticulo(Articulo[] articulo){
		System.out.println("Lo que entra:"+articulo);
		 List<Articulo> articulos2 = new ArrayList<>();
		for(Articulo art: articulo) {				
			articulos2.add(art);
		}	
		artDao = new ArticuloDAO(articulos2);	
		System.out.println(artDao.getArticulos().size());
		LuceneTester tester;
		      try {          
		          tester = new LuceneTester();
		          //Esta es la indexación
		          tester.createIndex(artDao,indexDir);
		      } catch (IOException e) {
		          e.printStackTrace();
		      }		    
		      
		      return Response.status(Response.Status.CREATED).entity(articulo).build();
	}
	
	
	//POST para ACM
	@POST
	@Produces(MediaType.APPLICATION_JSON)	
	@Consumes(MediaType.APPLICATION_JSON)	
	@Path("/indexACM")
	public Response guardarArticuloACM(Articulo[] articulo){
		 List<Articulo> articulos2 = new ArrayList<>();

		for(Articulo art: articulo) {				
			articulos2.add(art);
		}
		
		artDao = new ArticuloDAO(articulos2);	
		System.out.println(artDao.getArticulos().size());
		//Desde aquí se va a realizar la indexacion
		 // LuceneTester tester;
		      try {
		          
		          tester = new LuceneTester();
		          //Esta es la indexación
		          tester.createIndex(artDao,indexDirACM);
		        
		      } catch (IOException e) {
		          e.printStackTrace();
		      }
		      
		      return Response.status(Response.Status.CREATED).entity(articulo).build();
	}
	
	
	//POST para DBLP
		@POST
		@Produces(MediaType.APPLICATION_JSON)	
		@Consumes(MediaType.APPLICATION_JSON)	
		@Path("/indexDBLP")
		public Response guardarArticuloDBLP(Articulo[] articulo){
			 List<Articulo> articulos2 = new ArrayList<>();

			for(Articulo art: articulo) {				
				articulos2.add(art);
			}
			
			artDao = new ArticuloDAO(articulos2);	
			System.out.println(artDao.getArticulos().size());
			//Desde aquí se va a realizar la recomendación
			  LuceneTester tester;
			      try {
			          
			          tester = new LuceneTester();
			          //Esta es la indexación
			          tester.createIndex(artDao,indexDirDBLP);
			        
			      } catch (IOException e) {
			          e.printStackTrace();
			      }
			      
			      return Response.status(Response.Status.CREATED).entity(articulo).build();
		}
		      
}
