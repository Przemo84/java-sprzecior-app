package com.nordgeo.service.tool;

import com.nordgeo.entity.Tool;
import com.nordgeo.entity.User;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.exception.ToolAlreadyTakenException;
import com.nordgeo.persistence.repository.ToolRepository;
import com.nordgeo.security.AuthManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ToolServiceImpl implements ToolService {

    private ToolRepository toolRepository;

    private AuthManager authManager;

    public ToolServiceImpl(ToolRepository toolrepository, AuthManager authManager) {
        this.toolRepository = toolrepository;
        this.authManager = authManager;
    }

    @Override
    public Tool findById(Integer id) {
        return toolRepository.findOne(id);
    }

    @Override
    public Iterable<Tool> findAll() {
        return toolRepository.findAll();
    }

    @Override
    public Page<Tool> findAll(PageRequest page) {
        return toolRepository.findAll(page);
    }

    @Override
    public void save(Tool tool) {
        tool.setAvailable(true);
        toolRepository.save(tool);
    }

    @Override
    public void delete(int id) {
        User authUser = authManager.user();

        if (!authUser.getRole().getName().matches("Administrator"))
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
        toolRepository.save(tool);
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
    public Page<Tool> findAllUserTools(PageRequest page) { return toolRepository.findToolsByUser(authManager.user(),page); }

    @Override
    public void returnTool(int id) {
        Tool tool = toolRepository.findOne(id);
        tool.setAvailable(true);
        tool.setUser(null);
        toolRepository.save(tool);
    }

}
