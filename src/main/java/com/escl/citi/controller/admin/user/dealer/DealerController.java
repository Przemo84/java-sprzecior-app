package com.escl.citi.controller.admin.user.dealer;


import com.escl.citi.controller.AbstractPublishController;
import com.escl.citi.data.dto.DealerDto;
import com.escl.citi.data.mapper.DealerDtoMapper;
import com.escl.citi.entity.User;
import com.escl.citi.exception.*;
import com.escl.citi.service.Role.RoleService;
import com.escl.citi.service.user.UserService;
import com.escl.citi.storage.CroppedImageParams;
import com.escl.citi.storage.StorageException;
import com.escl.citi.utils.Flash;
import com.escl.citi.utils.PageSort;
import com.escl.citi.validation.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;


@Controller
@PreAuthorize("hasAuthority('Administrator') or hasAuthority('Developer')")
@RequestMapping(value = "/admin/dealer")
public class DealerController extends AbstractPublishController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private DealerDtoMapper dealerDtoMapper;

    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/admin/dealer";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "dealer.title";
    }


    @RequestMapping("")
    public String indexDealers(PageSort pageSort, Model model) {
        Page<User> dealerPage = userService.findAllDealers(pageSort.getPage(model));
        model.addAttribute("page", dealerPage);

        return "users.dealers.index";
    }

    @RequestMapping("/form")
    public String form(Model model) {
//        model.addAttribute("user", new User());
        model.addAttribute("user", new DealerDto());

        return "user.dealer.form";
    }

    @RequestMapping("/form/{id}")
    public String form(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id);
        user.setPassword(null);

        DealerDto dealer = dealerDtoMapper.map(user);

        model.addAttribute("user", dealer);

        return "user.dealer.form";
    }

    @PostMapping(value = "/save")
    public String submit(@Valid @ModelAttribute("user") DealerDto dealerDto, BindingResult result, RedirectAttributes redirectAttributes) {
        userValidator.validate(dealerDto, result);

        if (result.hasErrors())
            return "user.dealer.form";

        try {
            userService.save(dealerDto);
        } catch (GenericImageException e) {
            result.rejectValue("icon", e.getMessage());
            return "user.dealer.form";
        } catch (IOException e) {
            e.printStackTrace();
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/dealer";
    }

    @RequestMapping(value = "/lock/{id}")
    public String lockOne(@PathVariable("id") int id,
                          final RedirectAttributes redirectAttributes) {

        try {
            userService.lock(id);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/dealer";
        } catch (AdminNotAllowedToDeleteHimselfException e) {
            Flash.error(redirectAttributes, "Nie możesz zablokować samego siebie");
            return "redirect:/admin/dealer";
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/dealer";
    }


    @PostMapping(value = "/checkbox")
    public String massLock(@PathParam("ids") Integer ids[],
                           @PathParam("action") String action,
                           final RedirectAttributes redirectAttributes) {

        try {
            userService.massAction(ids, action);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/dealer";
        } catch (AdminNotAllowedToDeleteHimselfException e) {
            Flash.error(redirectAttributes, "Nie możesz zablokować samego siebie");
            return "redirect:/admin/dealer";
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/dealer";
    }

    @RequestMapping(value = "/check/{id}")
    public String check(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {


        try {
            userService.check(id);
        } catch (MakerCheckerViolationException e) {
            Flash.error(redirectAttributes, "Maker nie może być Checker'em");
            return "redirect:/admin/dealer";
        }

        Flash.success(redirectAttributes, "Akcja zakończona powodzeniem");
        return "redirect:/admin/dealer";
    }


}
