package com.nordgeo.service.history;

import com.nordgeo.entity.ActionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

public interface ActionHistoryService {

    Page<ActionHistory> findAll(PageRequest pageRequest);

    void save(ActionHistory history);

    Page<ActionHistory> findAllBetweenDates(PageRequest page, Date startDate, Date endDate);
}
