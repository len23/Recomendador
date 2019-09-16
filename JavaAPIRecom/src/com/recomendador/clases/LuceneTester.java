package com.recomendador.clases;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;

import com.ejemplos.dao.ArticuloDAO;
import com.ejemplos.dao.BusquedaDAO;
import com.ejemplos.modelo.Busqueda;
//import com.tutorialspoint.lucene.Indexer;
//import com.tutorialspoint.lucene.TextFileFilter;


public class LuceneTester {

   private String dataDir = "/home/lenin/Escritorio/2018A/Tesis/Recomendador/DatosRDFaRT/Data";
   private Indexer indexer;
   private Searcher searcher;
   
   public LuceneTester() {
	super();
}


//private Searcher searcher;
   
  /* public static void main(String[] args) {
	   ArticuloDAO artDAO = new ArticuloDAO();
	      LuceneTester tester;
	      try {
	          tester = new LuceneTester();
	          //Esta es la indexación
	          //tester.createIndex(artDAO);
	          tester.search(" Transparent redundancy in the time-triggered architecture");
	        
	      } catch (IOException e) {
	          e.printStackTrace();
	      } catch (ParseException e) {
	          e.printStackTrace();
	      }	
	   }*/

   /*public void sortUsingRelevance(String searchQuery)
      throws IOException, ParseException {
      searcher = new Searcher(indexDir);
      long startTime = System.currentTimeMillis();
      
      //create a term to search file name
      Term term = new Term(LuceneConstants.FILE_NAME, searchQuery);
      //create the term query object
      Query query = new FuzzyQuery(term);
      searcher.setDefaultFieldSortScoring(true, false);
      //do the search
      TopDocs hits = searcher.search(query,Sort.RELEVANCE);
      long endTime = System.currentTimeMillis();

      System.out.println(hits.totalHits +
         " documents found. Time :" + (endTime - startTime) + "ms");
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
         System.out.print("Score: "+ scoreDoc.score + " ");
         System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
      }
      searcher.close();
   }

   public void sortUsingIndex(String searchQuery)
      throws IOException, ParseException {
      searcher = new Searcher(indexDir);
      long startTime = System.currentTimeMillis();
      //create a term to search file name
      Term term = new Term(LuceneConstants.FILE_NAME, searchQuery);
      //create the term query object
      Query query = new FuzzyQuery(term);
      searcher.setDefaultFieldSortScoring(true, false);
      //do the search
      TopDocs hits = searcher.search(query,Sort.INDEXORDER);
      long endTime = System.currentTimeMillis();

      System.out.println(hits.totalHits +
      " documents found. Time :" + (endTime - startTime) + "ms");
      for(ScoreDoc scoreDoc : hits.scoreDocs) {
         Document doc = searcher.getDocument(scoreDoc);
         System.out.print("Score: "+ scoreDoc.score + " ");
         System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH));
      }
      searcher.close();
   }
   */
   
   //Metodo para crear indices de manera remota
   	  public void createIndex(ArticuloDAO artDao , String repo) throws IOException {
	      indexer = new Indexer(repo);
	      int numIndexed;
	      long startTime = System.currentTimeMillis();	
	      numIndexed = indexer.createIndex(artDao);
	      long endTime = System.currentTimeMillis();
	      System.out.println(numIndexed+" File indexed, time taken: "
	         +(endTime-startTime)+" ms");	
	      indexer.close();
	   }

   	  //Metodo para realizar las busquedas con los servicios REST
	   public  void search(String searchQuery, BusquedaDAO busquedaObj, String indexDir) throws IOException, ParseException {
	      searcher = new Searcher(indexDir);
	      long startTime = System.currentTimeMillis();
	      //searcher.setDefaultFieldSortScoring(true, false);
	      TopDocs hits = searcher.search(searchQuery);
	      long endTime = System.currentTimeMillis();
	      
	      DecimalFormat df = new DecimalFormat("#.000");   
	      System.out.println(hits.totalHits +
	         " documents found with word " + searchQuery + ". Time :" + (endTime - startTime));
	      for(ScoreDoc scoreDoc : hits.scoreDocs) {
			  Busqueda busqueda2 = new Busqueda();

	         Document doc = searcher.getDocument(scoreDoc);
	         System.out.println("Titulo Articulo: " + doc.get("titulo"));
	         System.out.println("Score: "+ df.format(scoreDoc.score) + " ");
	         System.out.println("-------------------------------------------");	         
	        busqueda2.setTituloArticulo(doc.get("titulo"));
	        busqueda2.setScore(scoreDoc.score);
	        //System.out.println("Agregando: " + busqueda2.getTituloArticulo() );
	        busquedaObj.agregarResultados(busqueda2);	           
	      }
	      searcher.close();
	   }
   
   
/*   	public  void search(String searchQuery) throws IOException, ParseException {
	      searcher = new Searcher("/home/lenin/Escritorio/2018A/Tesis/Recomendador/WorkSpace/JavaAPIRecom/Indexados");
	      long startTime = System.currentTimeMillis();
	      //searcher.setDefaultFieldSortScoring(true, false);
	      TopDocs hits = searcher.search(searchQuery);
	      long endTime = System.currentTimeMillis();
	      
	      DecimalFormat df = new DecimalFormat("#.000");   
	      System.out.println(hits.totalHits +
	         " documents found with word " + searchQuery + ". Time :" + (endTime - startTime));
	      for(ScoreDoc scoreDoc : hits.scoreDocs) {
			  Busqueda busqueda2 = new Busqueda();

	         Document doc = searcher.getDocument(scoreDoc);
	         System.out.println("Titulo Articulo: " + doc.get("titulo"));
	         System.out.println("Score: "+ df.format(scoreDoc.score) + " ");
	         System.out.println("-------------------------------------------");	         
	        busqueda2.setTituloArticulo(doc.get("titulo"));
	        busqueda2.setScore(scoreDoc.score);
	      }
	      searcher.close();
	   }
   	
   	*/
   	  
   	  
   	  
   	  
   	  
}