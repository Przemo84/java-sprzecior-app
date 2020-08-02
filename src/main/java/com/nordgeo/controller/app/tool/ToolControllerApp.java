package com.nordgeo.controller.app.tool;


import com.nordgeo.controller.AppAbstractController;
import com.nordgeo.entity.Tool;
import com.nordgeo.exception.AdminNotAllowedToDeleteHimselfException;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.exception.ToolAlreadyTakenException;
import com.nordgeo.service.tool.ToolService;
import com.nordgeo.utils.Flash;
import com.nordgeo.utils.PageSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/app/tools")
public class ToolControllerApp extends AppAbstractController {

    @Autowired
    private ToolService toolService;

    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/app/tools";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "tool.title";
    }

    @RequestMapping("/available")
    public String index(PageSort pageSort, Model model) {
        Page<Tool> toolPage = toolService.findAllAvailable(pageSort.getPage(model));
        model.addAttribute("page", toolPage);

        return "app.tools.index";
    }

    @RequestMapping("/unavailable")
    public String indexUnavailable(PageSort pageSort, Model model) {
        Page<Tool> toolPage = toolService.findAllUnavailable(pageSort.getPage(model));
        model.addAttribute("page", toolPage);

        return "app.tools.unavailable.index";
    }

    @RequestMapping("/user")
    public String indexUser(PageSort pageSort, Model model) {
        Page<Tool> toolPage = toolService.findAllUserTools(pageSort.getPage(model));
        model.addAttribute("page", toolPage);

        return "app.tools.my.index";
    }

    @RequestMapping(value = "/append/{id}")
    public String collectOne(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        try {
            toolService.append(id);
        } catch (ToolAlreadyTakenException e) {
            Flash.error(redirectAttributes, "Sprzęt już wcześniej pobrany");
            return "redirect:/app/tools/available";
        }

        Flash.success(redirectAttributes);
        return "redirect:/app/tools/available";
    }

    @RequestMapping(value = "/return/{id}")
    public String returnOne(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        try {
            toolService.returnTool(id);
        } catch (ToolAlreadyTakenException e) {
            Flash.error(redirectAttributes, "Sprzęt już wcześniej pobrany");
            return "redirect:/app/tools/user";
        }

        Flash.success(redirectAttributes);
        return "redirect:/app/tools/user";
    }
}
