package com.nordgeo.service.toolStatus;

import com.nordgeo.entity.ToolStatus;
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
}
