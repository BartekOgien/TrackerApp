package com.tracker.validator;

import com.tracker.model.User;
import com.tracker.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

    @Autowired
    private UserDao userDao;

    public boolean validateUser(String validatedUserName, String validatedPassword) {
        User userFromDb = userDao.findByUserName(validatedUserName);
        if(userFromDb == null) {
            return false;
        }
        else {
            String passwordFromDb = userFromDb.getPassword();
            if(passwordFromDb.equals(validatedPassword)) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}
