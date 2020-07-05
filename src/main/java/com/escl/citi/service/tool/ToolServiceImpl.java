package com.escl.citi.service.tool;

import com.escl.citi.entity.Tool;
import com.escl.citi.entity.User;
import com.escl.citi.exception.AdminOperationNotAllowedException;
import com.escl.citi.persistence.repository.ToolRepository;
import com.escl.citi.security.AuthManager;
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

}
