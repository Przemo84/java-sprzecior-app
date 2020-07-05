package com.nordgeo.service.tool;

import com.nordgeo.entity.Tool;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ToolService {

    Tool findById(Integer id);

    Iterable<Tool> findAll();

    Page<Tool> findAll(PageRequest page);

    void save(Tool tool);

    void delete(int id) throws AdminOperationNotAllowedException;
}
