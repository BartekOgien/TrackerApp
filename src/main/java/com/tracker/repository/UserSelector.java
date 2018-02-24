package com.tracker.repository;

import com.tracker.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserSelector {
    @Autowired
    private UserDao userDao;

    public User assignUser() {
        List<User> userList = userDao.findAll();
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(userList.size());

        if(userList.size() > 0) {
            return userList.get(randomNumber);
        }
        else {
            return null;
        }
    }
}
