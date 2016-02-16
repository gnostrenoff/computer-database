package com.gnostrenoff.cdb.tags;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * class implementing link taglib
 * @author excilys
 */
public class LinkTag extends SimpleTagSupport {

	private String baseUri;
	private String text;
	private String styleClass;

	private Writer getWriter() {
		JspWriter out = getJspContext().getOut();
		return out;
	}

	public void doTag() throws JspException, IOException {
		Writer out = getWriter();

		StringBuilder link = new StringBuilder("<a href=\"");
		link.append(baseUri).append("\" class=\"").append(styleClass).append("\">").append(text).append("</a>");
		out.write(link.toString());
	}

	public String getBaseUri() {
		return baseUri;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
}
