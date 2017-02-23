package com.lx.tools;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lx.macrofiles.MacroConstant;
import com.lx.macrofiles.MacroEnum.KFileFormatType;
import com.lx.model.FileInfo;
import com.lx.service.FileInfoService;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

@Component
public class ToolCreateDocIndex {

	@Autowired
	private FileInfoService fileInfoService;

	// 初始化索引
	public boolean init(HttpServletRequest request) {
		boolean flag = ToolIndexTime.setLatestIndexTime(0 + "", MacroConstant.TIME);

		if (flag) {
			// 删除所有索引
			File[] files = new File(MacroConstant.INDEXDIR).listFiles();
			for (File file : files) {
				file.delete();
			}
			flag = flag && createPDFIndex(request);
		}
		return flag;
	}

	// 创建PDF索引
	public boolean createPDFIndex(HttpServletRequest request) {
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
							document.add(new StringField("id", fileInfo.getFileId() + "", Field.Store.YES));
							document.add(new StringField("fileName", fileInfo.getFileName(), Field.Store.YES));

							switch (fileInfo.getFileStatus()) {
							case MacroConstant.PDF:
								document.add(new StringField("type", KFileFormatType.pdf.toString(), Field.Store.YES));
								break;
							case MacroConstant.DOC:
								document.add(new StringField("type", KFileFormatType.doc.toString(), Field.Store.YES));
								break;
							case MacroConstant.DOCX:
								document.add(new StringField("type", KFileFormatType.docx.toString(), Field.Store.YES));
								break;
							case MacroConstant.PPT:
								document.add(new StringField("type", KFileFormatType.ppt.toString(), Field.Store.YES));
								break;
							case MacroConstant.PPTX:
								document.add(new StringField("type", KFileFormatType.pptx.toString(), Field.Store.YES));
								break;
							case MacroConstant.XLSX:
								document.add(new StringField("type", KFileFormatType.xlsx.toString(), Field.Store.YES));
								break;
							default:
								break;
							}

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

	// 根据document id 删除PDF索引
	public boolean deletePDFIndex(String id) {
		IndexWriter writer = getIndexWriter();
		if (writer == null)
			return false;

		boolean flag = false;
		try {
			writer.deleteDocuments(new Term("id", id));
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
			Directory directory = FSDirectory.open(new File(MacroConstant.INDEXDIR));
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_44, new PaodingAnalyzer());
			/* 会创建write.lock文件 */
			writer = new IndexWriter(directory, iwc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer;
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
