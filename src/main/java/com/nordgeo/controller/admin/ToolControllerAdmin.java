package com.nordgeo.controller.admin;


import com.nordgeo.controller.AdminAbstractController;
import com.nordgeo.entity.Tool;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.service.tool.ToolService;
import com.nordgeo.utils.Flash;
import com.nordgeo.utils.PageSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping(value = "/admin/tools")
public class ToolControllerAdmin extends AdminAbstractController {

    @Autowired
    private ToolService toolService;

    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/admin/tools";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "tools.title";
    }


    @RequestMapping("")
    public String index(PageSort pageSort, Model model) {
        Page<Tool> toolPage = toolService.findAll(pageSort.getPage(model));
        model.addAttribute("page", toolPage);

        return "tools.index";
    }

    @RequestMapping("/form")
    public String form(Model model) {
        model.addAttribute("tool", new Tool());

        return "tools.form";
    }

    @RequestMapping("/form/{id}")
    public String form(@PathVariable("id") int id, Model model) {
        Tool tool = toolService.findById(id);

        model.addAttribute("tool", tool);

        return "tools.form";
    }

    @PostMapping(value = "/save")
    public String submit(
            @Valid @ModelAttribute("tool") Tool tool,
            BindingResult result,
            final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return "tool.form";

        toolService.save(tool);
        Flash.success(redirectAttributes);

        return "redirect:/admin/tools";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteOne(
            @PathVariable("id") int id,
            final RedirectAttributes redirectAttributes) {

        try {
            toolService.delete(id);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/tools";
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/tools";
    }


}