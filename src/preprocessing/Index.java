package preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.LockObtainFailedException;

public class Index {
	public static void main(String[] args) throws CorruptIndexException, LockObtainFailedException, IOException{
		//build index on matrix
	//	String iFileNameMatrix = "/home/qingcai/Tud51534/Data/clueweb_rel_mining/all.prob_word_given_rel.grow-diag-final-and.txt";
	//	buildIndex(iFileNameMatrix, "/home/qingcai/Tud51534/Data/clueweb_rel_mining/IndexMatrix");
		
		//build index on idx2rel.txt
		String iFileNameRel = "/home/qingcai/Tud51534/Data/clueweb_rel_mining/idx2rel.txt";
		buildIndexOnRel(iFileNameRel, "/home/qingcai/Tud51534/Data/clueweb_rel_mining/IndexRel");
	}
	
	public static void buildIndexOnMatrix(String iFileName, String indexPath) throws CorruptIndexException, LockObtainFailedException, IOException{
		//initialize
		  int mergeFactor = 1000;
		  int maxMergeDocs = Integer.MAX_VALUE;
		  Analyzer luceneAnalyzer = new StandardAnalyzer();
		  boolean bFile = false;
		 
		//Build index for INS-index
		  File indexDir_INS = null;
		  IndexWriter indexWriter_INS = null;
		  indexDir_INS = new File(indexPath);
		  bFile = indexDir_INS.exists();
		  if(bFile==false){
		   	bFile = indexDir_INS.mkdir();
		  }		  
		  indexWriter_INS = new IndexWriter(indexDir_INS,luceneAnalyzer,true);
		  indexWriter_INS.setMergeFactor(mergeFactor);
		  indexWriter_INS.setMaxMergeDocs(maxMergeDocs);
		  
		 //read the file
		  File iFile = new File(iFileName);
		  BufferedReader br = null;
		  int rowIndex = 1;
			try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){
				
				if((rowIndex-1)%500==0){
					System.out.print(rowIndex+" ");
					if((rowIndex-1)%5000==0)
						System.out.println();
				}
			
				String[] arr = line.split(" ");
				String rel = arr[0];
				int index = Integer.parseInt(arr[1])+1;
				String occur = arr[2];
				String weight = arr[3];
				Document document = new Document();
				Field col1_field = new Field("rel", rel,
  							Field.Store.YES, Field.Index.ANALYZED);
				Field col2_field = new Field("index", Integer.toString(index),
						Field.Store.YES, Field.Index.ANALYZED);
				Field col3_field = new Field("occur", occur, 
						Field.Store.YES, Field.Index.NO);
				Field col4_field = new Field("weight", weight, 
						Field.Store.YES, Field.Index.NO);
			
				document.add(col1_field);
				document.add(col2_field);
				document.add(col3_field);
				document.add(col4_field);
				indexWriter_INS.addDocument(document);
				
				line = br.readLine();
				rowIndex++;
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException el){
					
				}
			}
		}
		
	  	System.out.println("number of index lines in INS-index = "+rowIndex);
	  	indexWriter_INS.optimize();
	  	indexWriter_INS.close();
	}
	
	public static void buildIndexOnRel(String iFileName, String indexPath) throws CorruptIndexException, LockObtainFailedException, IOException{
		//initialize
		  int mergeFactor = 1000;
		  int maxMergeDocs = Integer.MAX_VALUE;
		  Analyzer luceneAnalyzer = new StandardAnalyzer();
		  boolean bFile = false;
		 
		//Build index for INS-index
		  File indexDir_INS = null;
		  IndexWriter indexWriter_INS = null;
		  indexDir_INS = new File(indexPath);
		  bFile = indexDir_INS.exists();
		  if(bFile==false){
		   	bFile = indexDir_INS.mkdir();
		  }		  
		  indexWriter_INS = new IndexWriter(indexDir_INS,luceneAnalyzer,true);
		  indexWriter_INS.setMergeFactor(mergeFactor);
		  indexWriter_INS.setMaxMergeDocs(maxMergeDocs);
		  
		 //read the file
		  
		  File iFile = new File(iFileName);
		  BufferedReader br = null;
		  int rowIndex = 1;
			try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){
				
		//		System.out.print(rowIndex+"\t");
				if((rowIndex-1)%500==0){
					System.out.print(rowIndex+" ");
					if((rowIndex-1)%5000==0)
						System.out.println();
				}
			
				String[] arr = line.split(" ");
				int colIndex = 1;
				for(String ele : arr){
					Document document = new Document();
					Field col1_field = new Field("col1", Integer.toString(rowIndex),
	  							Field.Store.YES, Field.Index.ANALYZED);
  					Field col2_field = new Field("col2", Integer.toString(colIndex),
  							Field.Store.YES, Field.Index.ANALYZED);
  					Field col3_field = new Field("col3", ele, 
  							Field.Store.YES, Field.Index.ANALYZED);
	  				
  					document.add(col1_field);
  					document.add(col2_field);
  					document.add(col3_field);
  					indexWriter_INS.addDocument(document);
	  					
					colIndex++;
				}
		//		System.out.println(colIndex);
				
				line = br.readLine();
				rowIndex++;
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException el){
					
				}
			}
		}
		
	  	System.out.println("number of index lines in INS-index = "+rowIndex);
	  	indexWriter_INS.optimize();
	  	indexWriter_INS.close();
	}
}
