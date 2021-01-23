package com.nordgeo.controller.admin;



import com.nordgeo.controller.AdminAbstractController;
import com.nordgeo.data.dto.ToolDto;
import com.nordgeo.data.mapper.ToolDtoMapper;
import com.nordgeo.entity.Tool;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.exception.ItemNotFoundException;
import com.nordgeo.service.tool.ToolService;
import com.nordgeo.utils.Flash;
import com.nordgeo.utils.PageSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;


@Controller
@RequestMapping(value = "/admin/tools")
public class ToolControllerAdmin extends AdminAbstractController {

    @Autowired
    private ToolService toolService;

    @Autowired
    private ToolDtoMapper toolDtoMapper;

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

    @RequestMapping("/unusable")
    public String indexUnusable(PageSort pageSort, Model model) {
        Page<Tool> toolPage = toolService.findAllUnusable(pageSort.getPage(model));
        model.addAttribute("page", toolPage);

        return "tools.index.unusable";
    }

    @RequestMapping("/form")
    public String form(Model model) {
        model.addAttribute("tool", new ToolDto());

        return "tools.form";
    }

    @RequestMapping("/form/{id}")
    public String form(@PathVariable("id") int id, Model model,
                       final RedirectAttributes redirectAttributes) {

        try {
            Tool tool = toolService.findById(id);
            model.addAttribute("tool", toolDtoMapper.map(tool));

        } catch (ItemNotFoundException e) {
            Flash.error(redirectAttributes, "tool.not.found");
            return "redirect:/admin/tools";
        } catch (ParseException e) {
            Flash.error(redirectAttributes, "action.failed");
        }

        return "tools.form";
    }

    @PostMapping(value = "/save")
    public String submit(@Valid @ModelAttribute("tool") ToolDto toolDto,
                         BindingResult result, final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return "tools.form";
        try {
            toolService.save(toolDto);
        } catch (ParseException e) {
            Flash.error(redirectAttributes, "action.failed");
        }
        Flash.success(redirectAttributes);

        return "redirect:/admin/tools";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        try {
            toolService.delete(id);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "action.only.admin.allowed");
            return "redirect:/admin/tools";
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/tools";
    }

    @PostMapping(value = "/make-unusable")
    public String makeUnusable(@RequestParam String id , @RequestParam String unusableReason, final RedirectAttributes redirectAttributes) {

        try {
            toolService.makeUnusable(Integer.parseInt(id), unusableReason);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "action.only.admin.allowed");
            return "redirect:/admin/tools";
        }

        Flash.success(redirectAttributes, "action.success");
        return "redirect:/admin/tools/unusable";
    }

    @RequestMapping(value = "/make-usable/{id}")
    public String makeUsable(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        try {
            toolService.makeUsable(id);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "action.only.admin.allowed");
            return "redirect:/admin/tools";
        }

        Flash.success(redirectAttributes, "action.success");
        return "redirect:/admin/tools/unusable";
    }

    @RequestMapping("/unusable/{id}")
    public String unusableInfo(@PathVariable("id") int id, Model model,
                       final RedirectAttributes redirectAttributes) {

        try {
            Tool tool = toolService.findById(id);
            model.addAttribute("tool", tool);

        } catch (ItemNotFoundException e) {
            Flash.error(redirectAttributes, "tool.not.found");
            return "redirect:/admin/tools/unusable";
        }

        return "tools.unusable.info";
    }


}
