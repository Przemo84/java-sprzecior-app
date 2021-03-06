package com.nordgeo.service.user.activity;

import com.nordgeo.entity.ActionHistory;
import com.nordgeo.entity.User;
import com.nordgeo.service.history.ActionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActivitiesServiceImpl implements UserActivitiesService {

    @Autowired
    ActionHistoryService actionHistoryService;

    @Override
    public void saveActivity(User user, String action) {
        ActionHistory history = new ActionHistory();
        history.setActionName(action);
        history.setUser(user);

        actionHistoryService.save(history);
    }

}
