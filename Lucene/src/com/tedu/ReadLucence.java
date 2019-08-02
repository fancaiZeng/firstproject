package com.tedu;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class ReadLucence {
	public static void main(String[] args) throws IOException {
		Directory directory = FSDirectory.open
				(new File("C:\\Users\\Tarena\\Desktop\\index").toPath());
		DirectoryReader reader = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(reader);
		Query query = new TermQuery(new Term("fileContext", "1"));
		TopDocs searches = indexSearcher.search(query, 10);
		System.out.println("总记录数="+searches.totalHits);
		
		ScoreDoc[] scoreDocs = searches.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int docId = scoreDoc.doc;
			Document doc = indexSearcher.doc(docId);
			String name = doc.get("name");
			String path = doc.get("path");
			String fileContext = doc.get("fileContext");
			String size = doc.get("size");
			System.out.println("文件名="+name);
			System.out.println("文件路径="+path);
			System.out.println("文件内容="+fileContext);
			System.out.println("文件长度="+size);
			System.out.println();
			System.out.println();
		}
		reader.close();
	}
}
