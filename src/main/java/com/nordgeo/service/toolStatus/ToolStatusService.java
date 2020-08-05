package com.nordgeo.service.toolStatus;

import com.nordgeo.entity.ToolStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ToolStatusService {


    Page<ToolStatus> findAllToolStatuses(PageRequest page, Integer toolId);

}
