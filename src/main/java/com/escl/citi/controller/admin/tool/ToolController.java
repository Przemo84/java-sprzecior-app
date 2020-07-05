package com.escl.citi.controller.admin.tool;


import com.escl.citi.controller.AbstractPublishController;
import com.escl.citi.entity.Tool;
import com.escl.citi.exception.AdminOperationNotAllowedException;
import com.escl.citi.service.tool.ToolService;
import com.escl.citi.utils.Flash;
import com.escl.citi.utils.PageSort;
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
public class ToolController extends AbstractPublishController {

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
