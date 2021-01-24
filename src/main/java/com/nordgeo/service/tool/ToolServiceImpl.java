package com.nordgeo.service.tool;

import com.nordgeo.data.dto.ToolDto;
import com.nordgeo.data.mapper.ToolDtoMapper;
import com.nordgeo.entity.Tool;
import com.nordgeo.entity.ToolStatus;
import com.nordgeo.entity.User;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.exception.ItemNotFoundException;
import com.nordgeo.exception.ToolAlreadyTakenException;
import com.nordgeo.repository.ToolRepository;
import com.nordgeo.security.AuthManager;
import com.nordgeo.service.toolStatus.ToolStatusService;
import com.nordgeo.service.user.activity.UserActivitiesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class ToolServiceImpl implements ToolService {

    private final ToolRepository toolRepository;

    private final AuthManager authManager;

    private final UserActivitiesService userActivitiesService;

    private final ToolStatusService toolStatusService;

    private final ToolDtoMapper toolDtoMapper;

    public ToolServiceImpl(ToolRepository toolrepository, AuthManager authManager, ToolDtoMapper toolDtoMapper,
                           UserActivitiesService userActivitiesService, ToolStatusService toolStatusService) {
        this.toolRepository = toolrepository;
        this.authManager = authManager;
        this.userActivitiesService = userActivitiesService;
        this.toolDtoMapper = toolDtoMapper;
        this.toolStatusService = toolStatusService;
    }

    @Override
    public Tool findById(Integer id) {

        Tool tool = toolRepository.findOne(id);

        if (tool == null)
            throw new ItemNotFoundException();

        return tool;
    }


    @Override
    public Page<Tool> findAll(PageRequest page) {
        return toolRepository.findToolByUnusableIsFalseOrUnusableNull(page);
    }

    @Override
    public void save(ToolDto toolDto) throws ParseException {
        toolDto.setAvailable(true);
        Tool tool = toolDtoMapper.map(toolDto);

        String action = "Dodanie";
        if (toolDto.getId() != null)
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
        return toolRepository.findToolsByAvailableIsFalseAndUnusableIsFalse(page);
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
    public void makeUnusable(int id, String unusableReason) {
        Tool tool = findById(id);
        tool.setAvailable(false);
        tool.setUnusable(true);
        tool.setUnusableDate(new Date());
        tool.setUnusableReason(unusableReason);

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
        tool.setUnusableReason(null);
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
