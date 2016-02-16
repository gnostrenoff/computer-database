package com.gnostrenoff.cdb.tags;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageTag extends SimpleTagSupport {
	
	private String uri;
	private int totalElements;
	private int nbElementsPerPage;
    private int currentPageIndex;
    private int maxLinks = 10;
    
    private int totalPages;

    private Writer getWriter() {
        JspWriter out = getJspContext().getOut();
        return out;
    }
    
    private void init(){
    	totalPages = (totalElements / nbElementsPerPage);
    	if(totalElements % nbElementsPerPage != 0){
    		totalPages++;
    	}
    }
    
	public void doTag() throws JspException, IOException {
	
		init();
		
		Writer out = getWriter();
		
		boolean lastPage = currentPageIndex == totalPages;
        int pgStart = Math.max(currentPageIndex - maxLinks / 2, 1);
        int pgEnd = pgStart + maxLinks;
        if (pgEnd > totalPages + 1) {
            int diff = pgEnd - totalPages;
            pgStart -= diff - 1;
            if (pgStart < 1)
                pgStart = 1;
            pgEnd = totalPages + 1;
        }
        
        try {
        	out.write("<ul class=\"pagination\">");
 
            if (currentPageIndex > 1)
                out.write(constructLinkPage(currentPageIndex - 1, "Previous", false));
 
            for (int i = pgStart; i < pgEnd; i++) {
            	if(currentPageIndex == i){
            		out.write(constructLinkPage(i,String.valueOf(i), true));
            	}
            	else{
            		out.write(constructLinkPage(i,String.valueOf(i), false));
            	}	           
            }
 
            if (!lastPage)
                out.write(constructLinkPage(currentPageIndex + 1, "Next", false));
 
            out.write("</ul>");           
            out.write("<div class=\"btn-group btn-group-sm pull-right\" role=\"group\">");
			
		    out.write(constructLinknbElement(10));
		    out.write(constructLinknbElement(50));
		    out.write(constructLinknbElement(100));			
				
			out.write("</div>");
			
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
		
	  }

	private String constructLinkPage(int page, String text, boolean current) {
		StringBuilder link = new StringBuilder("<li><a href=\"");
        if(!current){
        	link.append(uri.replace("$1", String.valueOf(nbElementsPerPage)).replace("$2", String.valueOf(computeOffset(page))));
        }       
        link.append("\">")
            .append(text)
            .append("</a></li>");
        return link.toString();
    }
	
	private String constructLinknbElement(int nbElement) {
        StringBuilder link = new StringBuilder("<li><a href=\"");
        link.append(uri.replace("$1", String.valueOf(nbElement)).replace("$2", String.valueOf(0)))
            .append("\">")
            .append(String.valueOf(nbElement))
            .append("</a></li>");
        return link.toString();
    }
	
	private int computeOffset(int page) {
		int offset;
		offset = (page - 1) * nbElementsPerPage;
		return offset;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public int getNbElementsPerPage() {
		return nbElementsPerPage;
	}

	public void setNbElementsPerPage(int nbElementPerPage) {
		this.nbElementsPerPage = nbElementPerPage;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public int getMaxLinks() {
		return maxLinks;
	}

	public void setMaxLinks(int maxLinks) {
		this.maxLinks = maxLinks;
	}
	
}
