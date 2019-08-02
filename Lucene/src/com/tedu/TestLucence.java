package com.tedu;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.activation.FileDataSource;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class TestLucence {
	
	public static void main(String[] args) throws Exception {
		//创建一个directory对象存储，索引的位置
		//也可以创建一个内存对象存储，但是不能持久化
		Directory directory = FSDirectory.open
				(new File("C:\\Users\\Tarena\\Desktop\\index").toPath());
		IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig());
		File file = new File("C:\\Users\\Tarena\\Desktop\\lucence");
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
			String name = file2.getName();
			String path = file2.getPath();
			String fileContext = FileUtils.readFileToString(file2, "gbk");
			System.out.println(fileContext);
			long size = FileUtils.sizeOf(file2);
			Field fieldName = new TextField("name", name, Field.Store.YES);
			Field pathField = new TextField("path", path, Field.Store.YES);
			Field contextField = new TextField("fileContext", fileContext, Field.Store.YES);
			TextField sizeField = new TextField("size", size+"", Field.Store.YES);
			Document document = new Document();
			document.add(fieldName);
			document.add(pathField);
			document.add(contextField);
			document.add(sizeField);
			indexWriter.addDocument(document);
		}
		indexWriter.close();
	}

}
