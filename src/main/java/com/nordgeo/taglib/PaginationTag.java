package com.nordgeo.taglib;

import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.Writer;
import java.net.URI;
import java.util.Map;
import java.util.TreeMap;

public class PaginationTag extends TagSupport {
    private String uri;
    private int offset;
    private int count;
    private int page = 1;
    private String previous = "&laquo;";
    private String next = "&raquo;";

    private Page pageData;

    private Map<Integer, String> pages = new TreeMap<>();

    private Writer getWriter() {
        return pageContext.getOut();
    }

    public int doStartTag() throws JspException {
        Writer out = getWriter();

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String query = request.getQueryString();

        String x = (String) request.getAttribute("javax.servlet.forward.request_uri");
        uri = x + "?" + query;

        setUri(uri);

        count = pageData.getTotalPages();

        buildPagesMap();

        try {
            out.write("<ul class=\"pagination\">");

            if (page <= 1) {
                out.write(constructLink(1, previous, "disabled", true));
            } else {
                out.write(constructLink(page - 1, previous, null, false));
            }


            for (Map.Entry<Integer, String> item : pages.entrySet()) {
                Integer key = item.getKey();
                String value = item.getValue();

                if (page == key) {
                    out.write(constructLink(key, value, "active", true));
                } else {
                    out.write(constructLink(key, value, null, false));
                }
            }

            if (page >= count - 1) {
                out.write(constructLink(count, next, "disabled", true));
            } else {
                out.write(constructLink(page + 1, next, null, false));
            }

            out.write("</ul>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }

        return SKIP_BODY;//will not evaluate the body content of the tag
    }


    private String constructLink(int page, String text, String className, boolean disabled) {
        StringBuilder link = new StringBuilder("<li");
        if (className != null) {
            link.append(" class=\"");
            link.append(className);
            link.append("\"");
        }

        UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder
                .fromUri(URI.create(uri))
                .replaceQueryParam("page", page);

        if (disabled)
            link.append(">").append("<a href=\"#\">" + text + "</a></li>");
        else
            link.append(">").append("<a href=\"" + uriBuilder.toUriString() + "\">" + text + "</a></li>");
        return link.toString();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;

        MultiValueMap<String, String> params = ServletUriComponentsBuilder
                .fromUri(URI.create(uri))
                .build()
                .getQueryParams();

        if (params.getFirst("size") != null && !params.getFirst("size").isEmpty()) {
            offset = Integer.parseInt(params.getFirst("size"));
        } else {
            offset = 10;
        }

        if (params.getFirst("page") != null && !params.getFirst("page").isEmpty()) {
            page = Integer.parseInt(params.getFirst("page"));
        } else {
            page = 0;
        }
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Page getPageData() {
        return pageData;
    }

    public void setPageData(Page pageData) {
        this.pageData = pageData;
    }

    private void buildPagesMap() {
        int countOut = 4;
        int countIn = 5;

        int startFirst = 0;
        int startLast = Math.min(countOut, count);

        int endFirst = Math.max(1, count - countOut);
        int endLast = count;

        int middleFirst = Math.max(startLast + 1, page - countIn);
        int middleLast = Math.min(endFirst - 1, page + countIn);

        boolean useMiddle = (middleLast >= middleFirst);

        int startSeparator = (startLast + middleFirst) / 2;
        boolean useStartSeparator = (useMiddle && ((middleFirst - startLast) > 1));

        int endSeparator = (middleLast + endFirst) / 2;
        boolean useEndSeparator = (useMiddle && ((endFirst - middleLast) > 1));

        for (int i = startFirst; i < startLast; i++) {
            pages.put(i, String.valueOf(i + 1));
        }

        if (useStartSeparator) {
            pages.put(startSeparator, "&hellip;");
        }

        for (int i = middleFirst; i < middleLast; i++) {
            pages.put(i, String.valueOf(i + 1));
        }

        if (useEndSeparator) {
            pages.put(endSeparator, "&hellip;");
        }

        for (int i = endFirst; i < endLast; i++) {
            pages.put(i, String.valueOf(i + 1));
        }
    }
}