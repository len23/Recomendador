package com.recomendador.clases;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.ejemplos.dao.ArticuloDAO;
import com.ejemplos.modelo.Articulo;

public class Indexer {

   private IndexWriter writer;

   public Indexer(String indexDirectoryPath) throws IOException {
      //this directory will contain the indexes
	   //Primero crea el directorio para luego indicarle al indexador donde almacenar los índices.
	      //System.out.println("BIENNNNN");

      Directory indexDirectory = 
         FSDirectory.open(new File(indexDirectoryPath));

      //create the indexer
      //Acá se crea el índexador con el IndexerWriter, se la pasa como parámetro al directorio.
      writer = new IndexWriter(indexDirectory, 
         new StandardAnalyzer(Version.LUCENE_36),
         true, 
         IndexWriter.MaxFieldLength.UNLIMITED);
   }

   public void close() throws CorruptIndexException, IOException {
      writer.close();
   }

   private Document getDocument(Articulo articulo) throws IOException {
      Document document = new Document();
      
      //Campos
      
      Field tituloField = new Field("titulo",articulo.getTitulo(),Field.Store.YES, Field.Index.NOT_ANALYZED);
      Field abstractoField = new Field("abstracto",articulo.getAbstracto(),Field.Store.YES, Field.Index.NOT_ANALYZED);
      
      document.add(tituloField);
      document.add(abstractoField);
      
      String textoCompleto = articulo.getTitulo()+ " " + articulo.getAbstracto();
      Field textField = new Field("textoBuscado",textoCompleto,Field.Store.YES, Field.Index.ANALYZED);
      document.add(textField);
      
      return document;
   }   

   private void indexArticulo(Articulo articulo) throws IOException {
      Document document = getDocument(articulo);
      writer.addDocument(document);
   }


   
   public int createIndex(ArticuloDAO artDao) throws IOException {
      
       // Indexa todas las entradas
       //
	   
	   List<Articulo>  articulos = artDao.getArticulos();
       for(Articulo articulo : articulos) {
           indexArticulo(articulo);              
       }
       //System.out.println("Todo Bien");
       //
       // Cierra el indexado mientras se realiza
       //
       //closeIndexadoEscrito();
       return writer.numDocs();

  }    
 

}