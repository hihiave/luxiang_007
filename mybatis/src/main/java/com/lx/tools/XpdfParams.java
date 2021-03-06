package com.lx.tools;

public class XpdfParams {
	private String convertor;
	private String layout;
	private String encoding;
	private String source;
	private String target;
	private String firstPage; // -f 2
	private String lastPage; // -l 6

	public String getConvertor() {
		return convertor;
	}

	public void setConvertor(String convertor) {
		this.convertor = convertor;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	public String getLastPage() {
		return lastPage;
	}

	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}

	public String getCommand() {
		return convertor + " " + layout + " " + encoding + " " + source + " " + target;
	}

}
