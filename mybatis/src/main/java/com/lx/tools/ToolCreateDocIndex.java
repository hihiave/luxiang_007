package com.lx.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.poi.hwpf.extractor.WordExtractor;

import com.lx.macrofiles.MacroConstant;
import com.lx.macrofiles.MacroEnum;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

public class ToolCreateDocIndex {

	//private static IndexWriter writer = null;

	// 初始化索引
	public static boolean init(HttpServletRequest request) {
		boolean flag = ToolIndexTime.setLatestIndexTime(0 + "", MacroConstant.PDF_TIME)
				&& ToolIndexTime.setLatestIndexTime(0 + "", MacroConstant.DOC_TIME);

		if (flag) {
			// 删除所有索引
			File[] files = new File(MacroConstant.INDEXDIR).listFiles();
			for (File file : files) {
				file.delete();
			}
			flag = flag && createPDFIndex(request);
			flag = flag && createWordIndex(request);
		}
		return flag;
	}

	// 创建PDF索引
	public static boolean createPDFIndex(HttpServletRequest request) {
		boolean flag = false;
		String basePath = request.getSession().getServletContext().getRealPath("");
		//File[] files = new File(basePath + MacroConstant.PDFDIR).listFiles();
		
		// TODO 
		
		File[] files = new File("").listFiles();

		if (files.length != 0) {
			String contents = "";
			String filename = "";
			Document document;

			IndexWriter writer = getIndexWriter();
			try {
				for (int i = 0; i < files.length; i++) {
					System.out.println("===ToolIndexTime.getLatestIndexTime(MacroConstant.PDF_TIME==========="
							+ ToolIndexTime.getLatestIndexTime(MacroConstant.PDF_TIME));

					if (files[i].lastModified() / 1000 > ToolIndexTime.getLatestIndexTime(MacroConstant.PDF_TIME)) {
						System.out.println("==========jiu========================");
						contents = XpdfParser.getPDFFileContents(files[i].getCanonicalPath());
						if (contents != null) {
							filename = ToolString.getFilename(files[i].getName());
							document = new Document();
							document.add(new StringField("id", filename, Field.Store.YES));
							document.add(
									new StringField("type", MacroEnum.KFileFormatType.pdf.toString(), Field.Store.YES));
							document.add(new StringField("fileName", filename, Field.Store.YES));
							document.add(new TextField("contents", contents, Field.Store.YES));
							System.out.println("=========writer===========" + writer);
							writer.addDocument(document);
						} else {
							return false;
						}
					}
				}
				flag = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

	// 创建Word索引
	public static boolean createWordIndex(HttpServletRequest request) {
		boolean flag = false;
		String basePath = request.getSession().getServletContext().getRealPath("");
		//File[] files = new File(basePath + MacroConstant.DOCDIR).listFiles();
		
		// TODO 
		File[] files = new File("").listFiles();
		
		
		if (files.length != 0) {
			String contents = "";
			Document document; // 文档
			String filename = "";
			IndexWriter writer = getIndexWriter();
			try {
				for (int i = 0; i < files.length; i++) {
					if (files[i].lastModified() / 1000 > ToolIndexTime.getLatestIndexTime(MacroConstant.DOC_TIME)) {
						filename = ToolString.getFilename(files[i].getName());

						contents = new WordExtractor(new FileInputStream(files[i].getCanonicalPath())).getText();

						// 创建文档
						document = new Document();
						document.add(new StringField("id", filename, Field.Store.YES));
						document.add(
								new StringField("type", MacroEnum.KFileFormatType.doc.toString(), Field.Store.YES));
						document.add(new StringField("fileName", filename, Field.Store.YES));
						document.add(new TextField("contents", contents, Field.Store.YES));
						writer.addDocument(document);
					}
				}
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	private static IndexWriter getIndexWriter() {
		IndexWriter writer = null;
		try {
			Directory directory = FSDirectory.open(new File(MacroConstant.INDEXDIR));
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_44, new PaodingAnalyzer());
			/* 会创建write.lock文件 */
			writer = new IndexWriter(directory, iwc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer;
	}

}
