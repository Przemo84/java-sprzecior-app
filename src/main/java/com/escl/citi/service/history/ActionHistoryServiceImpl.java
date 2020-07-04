package com.escl.citi.service.history;

import com.escl.citi.entity.ActionHistory;
import com.escl.citi.persistence.repository.ActionHistoryRepository;
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

