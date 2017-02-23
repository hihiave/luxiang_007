package com.lx.test;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.lx.macrofiles.MacroEnum;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

public class TestIndex {

	private static final String contents = "contents"; // <contents:~>

	@Test
	public void ddd() throws Exception {
		 IndexWriter writer = getIndexWriter();
		 writer.deleteDocuments(new Term("id", "430"));
		 writer.commit();
		 writer.close();
		
		IndexSearcher indexSearcher = getIndexSearcher();
		Query query = getQuery("数据");
		ScoreDoc[] scoreDocs = indexSearcher.search(query, 100).scoreDocs;

		for (int i = 0; i < scoreDocs.length; i++) {
			System.out.println("====1111111==" + scoreDocs[i]);
		}
		Document document = null;
		for (int i = 0; i < scoreDocs.length; i++) {
			document = indexSearcher.doc(scoreDocs[i].doc);

			System.out.println("=======id=====" + document.get("id"));
			System.out.println("=======type=====" + document.get("type"));
			System.out.println("=======fileName=====" + document.get("fileName"));
		}

		// 创建
		// Document document = new Document();
		// document.add(new StringField("id", "" + 4444, Field.Store.YES));
		// document.add(new StringField("type", "pdf", Field.Store.YES));
		// document.add(new StringField("fileName", "156460167641",
		// Field.Store.YES));
		// document.add(new TextField("contents", "哈哈哈扥的发热 ", Field.Store.YES));
		// writer.addDocument(document);
		// String d = document.get("contents");
		// System.out.println("====d3w======" + d);

	}

	private static IndexWriter getIndexWriter() {
		IndexWriter writer = null;
		try {
			// "C:\\lucene\\luceneindex\\";
			Directory directory = FSDirectory.open(new File("C:\\zzzz\\"));
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_44, new PaodingAnalyzer());
			/* 会创建write.lock文件 */
			writer = new IndexWriter(directory, iwc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer;
	}

	// 获取索引搜索
	private static IndexSearcher getIndexSearcher() {
		try {
			Directory directory = FSDirectory.open(new File("C:\\zzzz\\"));
			DirectoryReader directoryReader = DirectoryReader.open(directory);
			return new IndexSearcher(directoryReader);
		} catch (IOException e) {
			e.printStackTrace();
			MacroEnum.ErrMessage = "获取索引搜索错误!";
		}
		return null;
	}

	// 获取查询
	private static Query getQuery(String queryStr) {
		Query query = null;
		try {
			query = new QueryParser(Version.LUCENE_44, contents, new PaodingAnalyzer()).parse(queryStr);
		} catch (ParseException e) {
			e.printStackTrace();
			MacroEnum.ErrMessage = "获取查询错误!";
		}
		return query;
	}

}
