package com.escl.citi.controller.admin.history;


import com.escl.citi.controller.AbstractPublishController;
import com.escl.citi.entity.ActionHistory;
import com.escl.citi.error.DatesSelectValidator;
import com.escl.citi.service.history.ActionHistoryService;
import com.escl.citi.service.importers.exporters.ActionHistoryExporter;
import com.escl.citi.utils.Flash;
import com.escl.citi.utils.PageSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@PreAuthorize("hasAuthority('Administrator') or hasAuthority('Developer')")
@RequestMapping(value = "/admin/history")
public class ActionHistoryController extends AbstractPublishController {

    @Autowired
    private ActionHistoryService actionHistoryService;

    @Autowired
    private ActionHistoryExporter actionHistoryExporter;

    @Autowired
    private DatesSelectValidator datesValidator;


    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/admin/history";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "history.title";
    }


    @RequestMapping("")
    public String index(PageSort pageSort, Model model, HttpServletRequest request) throws ParseException {

        Page<ActionHistory> actionHistoryPage;

        if ((request.getParameter("startDate") != null && !request.getParameter("startDate").isEmpty()) ||
                (request.getParameter("endDate") != null && !request.getParameter("endDate").isEmpty())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = formatter.parse(request.getParameter("startDate"));
            Date endDate = formatter.parse(request.getParameter("endDate"));

            actionHistoryPage = actionHistoryService.findAllBetweenDates(pageSort.getPage(model), startDate, endDate);

        } else {

            actionHistoryPage = actionHistoryService.findAll(pageSort.getPage(model));
        }

        model.addAttribute("page", actionHistoryPage);

        return "history.index";
    }

    @GetMapping(value = "/export")
    public String export(HttpServletResponse response, HttpServletRequest request, final RedirectAttributes redirectAttributes) throws IOException, ParseException {
        if (!datesValidator.validate(request.getParameter("startDate"), request.getParameter("endDate"))) {
            Flash.error(redirectAttributes, "Akcja zakończona niepowodzeniem. Brak początkowej lub końcowej daty");
            return "redirect:/admin/history";
        }

        actionHistoryExporter.xlsxExport(response, request);

        Flash.success(redirectAttributes, "Akcja zakończona powodzeniem");

        return "";
    }

}
