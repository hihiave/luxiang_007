package com.lx.tools;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.lx.macrofiles.MacroConstant;
import com.lx.macrofiles.MacroEnum;
import com.lx.macrofiles.MacroEnum.KSearchType;
import com.lx.model.DocumentEntity;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

public class ToolSearchDocIndex {


	// 获得索引结果
	public static List<DocumentEntity> getSearchResult(String queryStr, String searchType, String filetype, int topNum,
			Page page) {
		List<DocumentEntity> list = new ArrayList<DocumentEntity>();// 初始化列表

		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'><b>", "</b></font>");
		SimpleHTMLFormatter formatTitle = new SimpleHTMLFormatter("<FONT color=#c60a00>", "</FONT>");

		Query query = getQuery(queryStr, searchType, filetype);
		// 高亮显示设置
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(query));
		Highlighter highlighterTitle = new Highlighter(formatTitle, new QueryScorer(query));
		/* 这个keyContextNum是指定关键字字符串的context的长度，你可以自己设定，因为不可能返回整篇正文内容 */
		highlighter.setTextFragmenter(new SimpleFragmenter(MacroConstant.DOC_CONTEXT_NUM));

		IndexSearcher searcher = getIndexSearcher();

		try {
			if (searcher != null) {
				ScoreDoc[] scoreDocs = searcher.search(query, topNum).scoreDocs;

				page.setTotalCount(scoreDocs.length);
				page.init();

				Document doc;
				DocumentEntity docEntity;
				String filename = ""; // 文件名
				String formatT = null; // 高亮标题

				for (int i = page.getStartPos(); i < page.getEndPos(); i++) {
					docEntity = new DocumentEntity();

					// 根据doc获取文档
					doc = searcher.doc(scoreDocs[i].doc);

					// 高亮出显示文本
					PaodingAnalyzer analyzer1 = new PaodingAnalyzer();
					TokenStream tokenStream = analyzer1.tokenStream("contents", new StringReader(doc.get(MacroConstant.DOC_CONTENTS)));
					docEntity.setContents(highlighter.getBestFragment(tokenStream, doc.get(MacroConstant.DOC_CONTENTS)));

					// 高亮出显示标题
					PaodingAnalyzer analyzer2 = new PaodingAnalyzer();
					filename = doc.get(MacroConstant.DOC_NAME);
					tokenStream = analyzer2.tokenStream("fileName", new StringReader(filename));
					// 需要注意：在处理时如果文本检索结果中不包含对应的关键字返回一个null
					formatT = highlighterTitle.getBestFragment(tokenStream, filename);
					if (formatT == null)
						formatT = filename;

					docEntity.setFilename(formatT);
					docEntity.setType(doc.get(MacroConstant.DOC_TYPE));
					docEntity.setId(doc.get("id"));
					docEntity.setFileUrl(doc.get("fileUrl"));

					list.add(docEntity);
					analyzer1.close();
					analyzer2.close();
				}
				return list;

			}

		} catch (Exception e) {
			e.printStackTrace();
			MacroEnum.ErrMessage = "获得索引结果错误!";
		}
		return null;
	}

	// 获取查询
	private static Query getQuery(String queryStr, String searchType, String filetype) {
		Query query = null;
		switch (KSearchType.valueOf(searchType)) {
		case accurate:
			try {
				query = new QueryParser(Version.LUCENE_44, MacroConstant.DOC_CONTENTS, new PaodingAnalyzer()).parse(queryStr);
			} catch (ParseException e) {
				e.printStackTrace();
				MacroEnum.ErrMessage = "获取查询错误!";
				return null;
			}
			break;
		case fuzzy:
			query = new FuzzyQuery(new Term(MacroConstant.DOC_CONTENTS, queryStr));
			break;
		case prefix:
			query = new PrefixQuery(new Term(MacroConstant.DOC_CONTENTS, queryStr));
			break;
		default:
			break;
		}

		BooleanQuery booleanQuery = new BooleanQuery();
		if (!"all".equals(filetype)) {
			booleanQuery.add(new TermQuery(new Term("type", filetype)), BooleanClause.Occur.MUST);
		}
		booleanQuery.add(query, BooleanClause.Occur.MUST);
		return booleanQuery;
	}

	// 获取索引搜索
	private static IndexSearcher getIndexSearcher() {
		try {
			Directory directory = FSDirectory.open(new File(MacroConstant.INDEX_DIR));
			DirectoryReader directoryReader = DirectoryReader.open(directory);
			return new IndexSearcher(directoryReader);
		} catch (IOException e) {
			e.printStackTrace();
			MacroEnum.ErrMessage = "获取索引搜索错误!";
		}
		return null;
	}

}
