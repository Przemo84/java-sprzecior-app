package com.nordgeo.service.tool;

import com.nordgeo.entity.Tool;
import com.nordgeo.entity.ToolStatus;
import com.nordgeo.entity.User;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.exception.ItemNotFoundException;
import com.nordgeo.exception.ToolAlreadyTakenException;
import com.nordgeo.persistence.repository.ToolRepository;
import com.nordgeo.security.AuthManager;
import com.nordgeo.service.toolStatus.ToolStatusService;
import com.nordgeo.service.user.activity.UserActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ToolServiceImpl implements ToolService {

    private ToolRepository toolRepository;

    private AuthManager authManager;

    private UserActivitiesService userActivitiesService;

    @Autowired
    private ToolStatusService toolStatusService;

    public ToolServiceImpl(ToolRepository toolrepository, AuthManager authManager, UserActivitiesService userActivitiesService) {
        this.toolRepository = toolrepository;
        this.authManager = authManager;
        this.userActivitiesService = userActivitiesService;
    }

    @Override
    public Tool findById(Integer id) {

        Tool tool = toolRepository.findOne(id);

        if (tool == null)
            throw new ItemNotFoundException();

        return tool;
    }

    @Override
    public Iterable<Tool> findAll() {
        return toolRepository.findAll();
    }

    @Override
    public Page<Tool> findAll(PageRequest page) {
        return toolRepository.findToolByUnusableIsFalseOrUnusableNull(page);
    }

    @Override
    public void save(Tool tool) {
        tool.setAvailable(true);
        String action = "Dodanie";

        if (tool.getId() != null)
            action = "Edycja";

        toolRepository.save(tool);

        saveToolActionHistory(tool, action);

    }

    @Override
    public void delete(int id) {
        User authUser = authManager.user();

        if (!authUser.getRole().getName().matches("Admin"))
            throw new AdminOperationNotAllowedException(null);
        else {
            toolRepository.delete(id);
        }
    }

    @Override
    public void append(int id) {
        Tool tool = toolRepository.findOne(id);

        if (tool.getUser() != null)
            throw new ToolAlreadyTakenException();

        tool.setAvailable(false);
        tool.setUser(authManager.user());
        tool.setTakenDate(new Date());
        toolRepository.save(tool);

        saveToolActionHistory(tool, "Pobranie: ");
    }

    @Override
    public Page<Tool> findAllAvailable(PageRequest page) {
        return toolRepository.findToolsByAvailableIsTrue(page);
    }

    @Override
    public Page<Tool> findAllUnavailable(PageRequest page) {
        return toolRepository.findToolsByAvailableIsFalse(page);
    }

    @Override
    public Page<Tool> findAllUserTools(PageRequest page) {
        return toolRepository.findToolsByUser(authManager.user(), page);
    }

    @Override
    public void returnTool(ToolStatus toolStatus) {
        Tool tool = toolRepository.findOne(toolStatus.getTool().getId());

        toolStatusService.save(toolStatus, tool);

        tool.setAvailable(true);
        tool.setUser(null);
        tool.setTakenDate(null);
        toolRepository.save(tool);

        saveToolActionHistory(tool, "Zwrot: ");
    }

    @Override
    public void makeUnusable(int id) {
        Tool tool = findById(id);
        tool.setAvailable(false);
        tool.setUnusable(true);
        tool.setUnusableDate(new Date());

        toolRepository.save(tool);

        saveToolActionHistory(tool, "Przeniesienie do nieużytków: ");
    }

    @Override
    public Page<Tool> findAllUnusable(PageRequest page) {
        return toolRepository.findToolsByUnusableTrue(page);
    }

    @Override
    public void makeUsable(int id) {
        Tool tool = findById(id);
        tool.setUnusableDate(null);
        tool.setUnusable(false);
        tool.setAvailable(true);

        toolRepository.save(tool);

        saveToolActionHistory(tool, "Przywrócenie do używalnego sprzętu: ");
    }

    private void saveToolActionHistory(Tool tool, String action) {

        userActivitiesService.saveActivity(authManager.user(), action + ": " + tool.getToolType() + ", Model: " + tool.getModel() + ", Nordgeo ID: <a href=\"/admin/tools/form/"
                + tool.getId() + "\">" + tool.getCompanyId() + "</a>");
    }

}
