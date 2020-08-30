package com.nordgeo.controller.app;


import com.nordgeo.controller.AppAbstractController;
import com.nordgeo.entity.Tool;
import com.nordgeo.entity.ToolStatus;
import com.nordgeo.exception.ToolAlreadyTakenException;
import com.nordgeo.service.tool.ToolService;
import com.nordgeo.utils.Flash;
import com.nordgeo.utils.PageSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


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
        model.addAttribute("toolStatus", new ToolStatus());
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

    @PostMapping(value = "/return")
    public String returnOne(@Valid @ModelAttribute("toolStatus") ToolStatus toolStatus,
                            BindingResult result, final RedirectAttributes redirectAttributes) {

        try {
            toolService.returnTool(toolStatus);
        } catch (ToolAlreadyTakenException e) {
            Flash.error(redirectAttributes, "Sprzęt już wcześniej pobrany");
            return "redirect:/app/tools/user";
        }

        Flash.success(redirectAttributes);
        return "redirect:/app/tools/user";
    }

    @PreAuthorize("hasAuthority('Editor')")
    @RequestMapping("/form")
    public String form(Model model) {
        model.addAttribute("tool", new Tool());

        return "app.tools.form";
    }

    @PreAuthorize("hasAuthority('Editor')")
    @RequestMapping("/form/{id}")
    public String form(@PathVariable("id") int id, Model model) {
        Tool tool = toolService.findById(id);

        model.addAttribute("tool", tool);

        return "app.tools.form";
    }

    @PreAuthorize("hasAuthority('Editor')")
    @PostMapping(value = "/save")
    public String submit(
            @Valid @ModelAttribute("tool") Tool tool,
            BindingResult result,
            final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return "app.tools.form";

        toolService.save(tool);
        Flash.success(redirectAttributes);

        return "redirect:/app/tools/available";
    }
}
