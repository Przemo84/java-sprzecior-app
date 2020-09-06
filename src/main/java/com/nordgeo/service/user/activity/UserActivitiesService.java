package com.nordgeo.service.user.activity;


import com.nordgeo.entity.User;

public interface UserActivitiesService {

    void saveActivity(User user, String action);

}
