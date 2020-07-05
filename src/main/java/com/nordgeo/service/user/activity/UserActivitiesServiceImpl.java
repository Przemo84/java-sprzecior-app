package com.nordgeo.service.user.activity;

import com.nordgeo.entity.ActionHistory;
import com.nordgeo.security.AuthManager;
import com.nordgeo.service.history.ActionHistoryService;
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
