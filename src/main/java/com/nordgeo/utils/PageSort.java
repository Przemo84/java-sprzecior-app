package com.nordgeo.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class PageSort {

    private static final String ORDER_ATTR = "order";
    private static final String ORDER_DESC = "desc";
    private static final String ORDER_ASC = "asc";

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 200;
    private static final String DEFAULT_SORT = "id";


    public PageRequest getPage(Model model) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        int page = getPage(request);
        int size = getSize(request);
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");

        if(order == null || order.isEmpty())
            order = ORDER_DESC;

        Sort sortConfig = resolveSort(model, sort, order);
        return new PageRequest(page, size, sortConfig);
    }

    private static Sort resolveSort(Model model, String sort, String order) {
        sort = (sort != null && !sort.isEmpty()) ? sort : DEFAULT_SORT;

        Sort sortConfig = new Sort(Sort.Direction.fromStringOrNull(order), sort);

        model.addAttribute(ORDER_ATTR, ORDER_ASC);

        if (order != null) {
            if (sortConfig.getOrderFor(sort).getDirection() == Sort.Direction.DESC) {
                model.addAttribute(ORDER_ATTR, ORDER_ASC);
            } else if (sortConfig.getOrderFor(sort).getDirection() == Sort.Direction.ASC) {
                model.addAttribute(ORDER_ATTR, ORDER_DESC);
            }
        }

        return sortConfig;
    }

    private int getPage(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            return DEFAULT_PAGE;
        }
    }

    private int getSize(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter("size"));
        } catch (NumberFormatException e) {
            return DEFAULT_SIZE;
        }
    }
}
