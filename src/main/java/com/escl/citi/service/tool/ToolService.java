package com.escl.citi.service.tool;

import com.escl.citi.entity.Tool;
import com.escl.citi.exception.AdminOperationNotAllowedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ToolService {

    Tool findById(Integer id);

    Iterable<Tool> findAll();

    Page<Tool> findAll(PageRequest page);

    void save(Tool tool);

    void delete(int id) throws AdminOperationNotAllowedException;
}
