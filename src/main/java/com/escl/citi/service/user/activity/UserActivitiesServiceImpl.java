package com.escl.citi.service.user.activity;

import com.escl.citi.entity.ActionHistory;
import com.escl.citi.security.AuthManager;
import com.escl.citi.service.history.ActionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActivitiesServiceImpl implements UserActivitiesService {

    @Autowired
    private AuthManager authManager;

    @Autowired
    ActionHistoryService actionHistoryService;

    @Override
    public void saveActivity(String action) {
        ActionHistory history = new ActionHistory();
        history.setActionName(action);
        history.setUser(authManager.user());

        actionHistoryService.save(history);
    }

}
