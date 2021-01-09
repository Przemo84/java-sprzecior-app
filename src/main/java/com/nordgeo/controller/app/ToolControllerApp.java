package com.nordgeo.controller.app;


import com.nordgeo.controller.AppAbstractController;
import com.nordgeo.data.dto.ToolDto;
import com.nordgeo.data.mapper.ToolDtoMapper;
import com.nordgeo.entity.Tool;
import com.nordgeo.entity.ToolStatus;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.exception.ItemNotFoundException;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.text.ParseException;


@Controller
@RequestMapping(value = "/app/tools")
public class ToolControllerApp extends AppAbstractController {

    @Autowired
    private ToolService toolService;

    @Autowired
    private ToolDtoMapper toolDtoMapper;

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

    @RequestMapping("/unusable")
    public String indexUnusable(PageSort pageSort, Model model) {
        Page<Tool> toolPage = toolService.findAllUnusable(pageSort.getPage(model));
        model.addAttribute("page", toolPage);

        return "app.tools.index.unusable";
    }

    @RequestMapping(value = "/append/{id}")
    public String collectOne(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        try {
            toolService.append(id);
        } catch (ToolAlreadyTakenException e) {
            Flash.error(redirectAttributes, "tool.already.taken");
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
            Flash.error(redirectAttributes, "tool.already.taken");
            return "redirect:/app/tools/user";
        }

        Flash.success(redirectAttributes);
        return "redirect:/app/tools/user";
    }

    @PreAuthorize("hasAuthority('Editor')")
    @RequestMapping("/form")
    public String form(Model model) {
        model.addAttribute("tool", new ToolDto());

        return "app.tools.form";
    }

    @PreAuthorize("hasAuthority('Editor')")
    @RequestMapping("/form/{id}")
    public String form(@PathVariable("id") int id, Model model) {
        Tool tool = toolService.findById(id);

        try {
            model.addAttribute("tool", toolDtoMapper.map(tool));
        } catch (ParseException e) {
            e.printStackTrace(); // TODO
        }

        return "app.tools.form";
    }

    @PreAuthorize("hasAuthority('Editor')")
    @PostMapping(value = "/save")
    public String submit(
            @Valid @ModelAttribute("tool") ToolDto toolDto,
            BindingResult result,
            final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return "app.tools.form";

        try {
            toolService.save(toolDto);
        } catch (ParseException e) {
            e.printStackTrace(); // TODO
        }
        Flash.success(redirectAttributes);

        return "redirect:/app/tools/available";
    }

    @PostMapping(value = "/make-unusable")
    public String makeUnusable(@RequestParam String id , @RequestParam String unusableReason, final RedirectAttributes redirectAttributes) {

        try {
            toolService.makeUnusable(Integer.parseInt(id), unusableReason);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "action.only.admin.allowed");
            return "redirect:/app/tools/available";
        }

        Flash.success(redirectAttributes, "action.success");
        return "redirect:/app/tools/unusable";
    }

    @RequestMapping(value = "/make-usable/{id}")
    public String makeUsable(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        try {
            toolService.makeUsable(id);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "action.only.admin.allowed");
            return "redirect:/app/tools/available";
        }

        Flash.success(redirectAttributes, "action.success");
        return "redirect:/app/tools/unusable";
    }

    @RequestMapping("/unusable/{id}")
    public String unusableInfo(@PathVariable("id") int id, Model model,
                               final RedirectAttributes redirectAttributes) {

        try {
            Tool tool = toolService.findById(id);
            model.addAttribute("tool", tool);

        } catch (ItemNotFoundException e) {
            Flash.error(redirectAttributes, "tool.not.found");
            return "redirect:/app/tools/unusable";
        }

        return "app.tools.unusable.info";
    }
}
