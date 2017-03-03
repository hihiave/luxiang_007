package com.lx.tools;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lx.macrofiles.MacroConstant;
import com.lx.macrofiles.MacroEnum;
import com.lx.macrofiles.MacroEnum.KFileFormatType;
import com.lx.macrofiles.MacroEnum.KSearchType;
import com.lx.model.DocumentEntity;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

@Component
public class ToolDocIndex {

	@Autowired
	private FileInfoService fileInfoService;

	// 初始化索引
	public boolean initDocIndex(HttpServletRequest request) {
		boolean flag = ToolIndexTime.setLatestIndexTime(0 + "", MacroConstant.TIME);

		if (flag) {
			// 删除所有索引
			File[] files = new File(MacroConstant.INDEX_DIR).listFiles();
			for (File file : files) {
				file.delete();
			}
			flag = flag && createDocIndex(request);
		}
		return flag;
	}

	// 创建文档索引
	public boolean createDocIndex(HttpServletRequest request) {
		boolean flag = false;
		String basePath = request.getSession().getServletContext().getRealPath("");

		File[] files = new File(basePath + MacroConstant.PUBLIC_DIR).listFiles();

		if (files.length != 0) {
			String contents = "";
			Document document;

			IndexWriter writer = getIndexWriter();
			int latestIndexTime = ToolIndexTime.getLatestIndexTime(MacroConstant.TIME);
			try {
				for (int i = 0; i < files.length; i++) {
					if (files[i].lastModified() / 1000 > latestIndexTime) {
						System.out.println("==========jiu========================");
						contents = XpdfParser.getPDFFileContents(files[i].getCanonicalPath());
						if (contents != null) {

							FileInfo fileInfo = fileInfoService
									.getFileByUploadTime(Integer.valueOf(ToolString.getFilename(files[i].getName())));

							document = new Document();

							document.add(
									new StringField(MacroConstant.DOC_ID, fileInfo.getFileId() + "", Field.Store.YES));
							document.add(
									new StringField(MacroConstant.DOC_NAME, fileInfo.getFileName(), Field.Store.YES));

							switch (fileInfo.getFileStatus()) {
							case MacroConstant.PDF:
								document.add(new StringField(MacroConstant.DOC_TYPE, KFileFormatType.pdf.toString(),
										Field.Store.YES));
								break;
							case MacroConstant.DOC:
								document.add(new StringField(MacroConstant.DOC_TYPE, KFileFormatType.doc.toString(),
										Field.Store.YES));
								break;
							case MacroConstant.DOCX:
								document.add(new StringField(MacroConstant.DOC_TYPE, KFileFormatType.docx.toString(),
										Field.Store.YES));
								break;
							case MacroConstant.PPT:
								document.add(new StringField(MacroConstant.DOC_TYPE, KFileFormatType.ppt.toString(),
										Field.Store.YES));
								break;
							case MacroConstant.PPTX:
								document.add(new StringField(MacroConstant.DOC_TYPE, KFileFormatType.pptx.toString(),
										Field.Store.YES));
								break;
							case MacroConstant.XLSX:
								document.add(new StringField(MacroConstant.DOC_TYPE, KFileFormatType.xlsx.toString(),
										Field.Store.YES));
								break;
							default:
								break;
							}

							document.add(
									new StringField(MacroConstant.DOC_URL, fileInfo.getFileUrl(), Field.Store.YES));
							document.add(new TextField(MacroConstant.DOC_CONTENTS, contents, Field.Store.YES));
							writer.addDocument(document);
						} else {
							return false;
						}
					}
				}
				flag = true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (writer != null) {
						writer.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	// 根据document id 删除文档索引
	public boolean deleteDocIndex(int id) {
		IndexWriter writer = getIndexWriter();
		if (writer == null)
			return false;

		boolean flag = false;
		try {
			writer.deleteDocuments(new Term(MacroConstant.DOC_ID, id + ""));
			writer.commit();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}

	private static IndexWriter getIndexWriter() {
		IndexWriter writer = null;
		try {
			Directory directory = FSDirectory.open(new File(MacroConstant.INDEX_DIR));
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_44, new PaodingAnalyzer());
			/* 会创建write.lock文件 */
			writer = new IndexWriter(directory, iwc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer;
	}

	// 查询文档索引,获得索引结果
	public static List<DocumentEntity> searchDocIndex(String queryStr, String searchType, String filetype, int topNum,
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
					TokenStream tokenStream = analyzer1.tokenStream("contents", new StringReader(
							doc.get(MacroConstant.DOC_CONTENTS).replace("\r\n", "<br>").replace(" ", "&nbsp;")));
					docEntity.setContents(highlighter.getBestFragment(tokenStream,
							doc.get(MacroConstant.DOC_CONTENTS).replace("\r\n", "<br>").replace(" ", "&nbsp;")));

					// 高亮出显示标题
					PaodingAnalyzer analyzer2 = new PaodingAnalyzer();
					filename = doc.get(MacroConstant.DOC_NAME);
					tokenStream = analyzer2.tokenStream("fileName", new StringReader(filename));
					// 需要注意：在处理时如果文本检索结果中不包含对应的关键字返回一个null
					formatT = highlighterTitle.getBestFragment(tokenStream, filename);
					if (formatT == null)
						formatT = filename;

					docEntity.setFilename(formatT);
					docEntity.setId(doc.get(MacroConstant.DOC_ID));
					docEntity.setType(doc.get(MacroConstant.DOC_TYPE));
					docEntity.setFileUrl(doc.get(MacroConstant.DOC_URL));

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
				query = new QueryParser(Version.LUCENE_44, MacroConstant.DOC_CONTENTS, new PaodingAnalyzer())
						.parse(queryStr);
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
			booleanQuery.add(new TermQuery(new Term(MacroConstant.DOC_TYPE, filetype)), BooleanClause.Occur.MUST);
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

	// 创建Word索引
	// public static boolean createWordIndexQQ(HttpServletRequest request) {
	// boolean flag = false;
	// String basePath =
	// request.getSession().getServletContext().getRealPath("");
	// // File[] files = new File(basePath + MacroConstant.DOCDIR).listFiles();
	//
	// File[] files = new File("").listFiles();
	//
	// if (files.length != 0) {
	// String contents = "";
	// Document document; // 文档
	// String filename = "";
	// IndexWriter writer = getIndexWriter();
	// try {
	// for (int i = 0; i < files.length; i++) {
	// if (files[i].lastModified() / 1000 >
	// ToolIndexTime.getLatestIndexTime(MacroConstant.DOC_TIME)) {
	// filename = ToolString.getFilename(files[i].getName());
	//
	// contents = new WordExtractor(new
	// FileInputStream(files[i].getCanonicalPath())).getText();
	//
	// // 创建文档
	// document = new Document();
	// document.add(new StringField("id", filename, Field.Store.YES));
	// document.add(
	// new StringField("type", MacroEnum.KFileFormatType.doc.toString(),
	// Field.Store.YES));
	// document.add(new StringField("fileName", filename, Field.Store.YES));
	// document.add(new TextField("contents", contents, Field.Store.YES));
	// writer.addDocument(document);
	// }
	// }
	// flag = true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// return flag;
	// }

}
