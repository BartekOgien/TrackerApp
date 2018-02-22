package com.tracker.validator;

import com.tracker.constants.Constants;
import com.tracker.model.User;
import com.tracker.repository.UserDao;
import com.tracker.repository.VariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {
    private final static String WRONG_USER = "Wrong username!";
    private final static String WRONG_PASSWORD = "Wrong password!";
    private final static String USER_EXIST = "User with this username exist";

    @Autowired
    private UserDao userDao;

    public boolean validateUser(String validatedUserName, String validatedPassword) {
        User userFromDb = userDao.findByUserName(validatedUserName);
        if(userFromDb == null) {
            VariableRepository.setCurrentLoginError(WRONG_USER);
            return false;
        }
        else {
            if(userFromDb.getPassword().equals(validatedPassword)) {
                VariableRepository.setCurrentUser(userFromDb);
                VariableRepository.setCurrentLoginError(Constants.STRING_EMPTY);
                return true;
            }
            else {
                VariableRepository.setCurrentLoginError(WRONG_PASSWORD);
                return false;
            }
        }
    }

    public boolean checkLoginIsUnique(String username) {
        if(userDao.findByUserName(username) == null) {
            VariableRepository.setCurrentRegisterError(Constants.STRING_EMPTY);
            VariableRepository.setCurrentLoginError(Constants.STRING_EMPTY);
            return true;
        }
        else {
            VariableRepository.setCurrentRegisterError(USER_EXIST);
            return false;
        }
    }
}
