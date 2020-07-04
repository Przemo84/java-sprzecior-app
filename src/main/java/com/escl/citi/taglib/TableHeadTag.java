package com.escl.citi.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class TableHeadTag extends TagSupport {

    private String param;

    private Writer getWriter() {
        return pageContext.getOut();
    }

    public int doStartTag() throws JspException {
        Writer out = getWriter();

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String baseUrl = (String) request.getAttribute("javax.servlet.forward.request_uri");
        String sort = "";
        String page = "";
        String size = "";
        String order = "";

        Map<String, String[]> parameters = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String[] entryValue = entry.getValue();

            switch (key) {
                case "sort":
                    sort = entryValue[0];
                    continue;
                case "page":
                    page = entryValue[0];
                    continue;
                case "size":
                    size = entryValue[0];
                    continue;
                case "order":
                    order = entryValue[0];
            }

        }

        final StringBuilder bodyContent = new StringBuilder();

        if (!param.equals(sort)) {
            bodyContent.append("<a href=\"" + baseUrl + "?sort=" + param + "&order=ASC&size=" + size + "&page=" + page + "\" >");
            bodyContent.append("<i class=\"fa fa-sort-amount-asc\" aria-hidden=\\\"true\\\" style=\\\"color: gray\\\"></i>");
        } else {
            String orderForIcon = order;
            if (order.equals("ASC"))
                order = "DESC";
            else
                order = "ASC";

            bodyContent.append("<a href=\"" + baseUrl + "?sort=" + param + "&order=" + order + "&size=" + size + "&page=" + page + "\" style=\"color: black;font-size: 14px\">");
            bodyContent.append("<i class=\"fa fa-sort-amount-" + orderForIcon + "\" aria-hidden=\\\"true\\\" style=\\\"color: black\\\"></i>");
        }

        try {
            out.write(bodyContent.toString());
        } catch (IOException e) {
            throw new JspException("Error in Tale Header tag", e);
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        Writer out = getWriter();

        try {
            out.write("</a>");
        } catch (IOException e) {
            throw new JspException("Error in Tale Header tag", e);
        }


        return super.doEndTag();
    }

    public void setParam(String param) {
        this.param = param;
    }
}
