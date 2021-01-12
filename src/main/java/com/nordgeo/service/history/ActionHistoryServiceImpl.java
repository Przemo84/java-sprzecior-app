package com.nordgeo.service.history;

import com.nordgeo.entity.ActionHistory;
import com.nordgeo.repository.ActionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActionHistoryServiceImpl implements ActionHistoryService {

    @Autowired
    private ActionHistoryRepository repository;

    @Override
    public Page<ActionHistory> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Override
    public void save(ActionHistory history) { repository.save(history); }

    @Override
    public Page<ActionHistory> findAllBetweenDates(PageRequest page, Date startDate, Date endDate) {
        return repository.findAllBetweenDates(page, startDate, endDate);
    }


}

