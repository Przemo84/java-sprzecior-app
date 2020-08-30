package com.nordgeo.service.toolStatus;

import com.nordgeo.entity.Tool;
import com.nordgeo.entity.ToolStatus;
import com.nordgeo.entity.User;
import com.nordgeo.persistence.repository.ToolStatusRepository;
import com.nordgeo.security.AuthManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ToolStatusServiceImpl implements ToolStatusService {

    private ToolStatusRepository toolStatusRepository;

    private AuthManager authManager;

    public ToolStatusServiceImpl(ToolStatusRepository toolStatusRepository, AuthManager authManager) {
        this.toolStatusRepository = toolStatusRepository;
        this.authManager = authManager;
    }


    @Override
    public Page<ToolStatus> findAllToolStatuses(PageRequest page, Integer toolId) {
        return toolStatusRepository.findAllToolStatuses(page, toolId);
    }

    @Override
    public void save(ToolStatus toolStatus, Tool tool) {
        User user = authManager.user();
        toolStatus.setUser(user);
        toolStatus.setAction(ToolStatus.Action.TAKE_OUT);
        toolStatus.setTool(tool);

        toolStatusRepository.save(toolStatus);
    }

    @Override
    public Double getToolAverageOfRatings(int id) {

        return toolStatusRepository.findAverageOfRatings(id);
    }
}
