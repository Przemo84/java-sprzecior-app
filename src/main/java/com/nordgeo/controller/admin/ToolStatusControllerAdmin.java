package com.nordgeo.controller.admin;


import com.nordgeo.controller.AdminAbstractController;
import com.nordgeo.entity.Tool;
import com.nordgeo.entity.ToolStatus;
import com.nordgeo.service.tool.ToolService;
import com.nordgeo.service.toolStatus.ToolStatusService;
import com.nordgeo.utils.PageSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/admin/tools/status")
public class ToolStatusControllerAdmin extends AdminAbstractController {

    @Autowired
    private ToolStatusService toolStatusService;

    @Autowired
    private ToolService toolService;

    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/admin/tools/status";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "tools-status.title";
    }

    @RequestMapping("/{id}")
    public String form(@PathVariable("id") int id, PageSort pageSort, Model model) {
        Page<ToolStatus> toolStatusesPage = toolStatusService.findAllToolStatuses(pageSort.getPage(model), id);
        Tool tool = toolService.findById(id);
        model.addAttribute("page", toolStatusesPage);
        model.addAttribute("tool", tool);

        return "tool.status.index";
    }


}
