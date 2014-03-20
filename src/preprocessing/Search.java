package preprocessing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.demo.html.ParseException;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

public class Search {
	public static void main(String[] args) throws CorruptIndexException, LockObtainFailedException, IOException, ParseException, org.apache.lucene.queryParser.ParseException{
		String indexPath = "/home/qingcai/Tud51534/Data/clueweb_rel_mining/Index";
		
		doSearch(indexPath, "1");
	}
	
	public static void doSearch(String indexPath, String query) throws CorruptIndexException, IOException, ParseException, org.apache.lucene.queryParser.ParseException{
        int hitsPerPage = 10;
        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, false);
        IndexSearcher searcher = new IndexSearcher(indexPath);
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
        QueryParser parser = new QueryParser("col1", analyzer);    //"sentence" is the index name
        org.apache.lucene.search.Query phraseQuery = parser.parse(query);
        searcher.search(phraseQuery, collector);
        ScoreDoc[] hits = collector.topDocs().scoreDocs;
        int numTotalHits = collector.getTotalHits();
        
        String col = null;
        String score = null;
       for(int i=0; i<numTotalHits && i< hits.length; i++)
        {    
            Document document = searcher.doc(hits[i].doc);
            col = document.get("col2");
            score = document.get("col3");
            
            System.out.println("------------------------------------------");
            System.out.println("query: " + query);
            System.out.println("info1: " + col);
            System.out.println("info2: " + score);
            System.out.println("------------------------------------------");
        }    
        
        searcher.close();
        analyzer.close();
        hits = null;
        System.gc();
    }
}
