package com.tracker.validator;

import com.tracker.constants.Constants;
import com.tracker.mapper.Mapper;
import com.tracker.model.User;
import com.tracker.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserValidator {
    private final static String WRONG_USER = "Wrong username!";
    private final static String WRONG_PASSWORD = "Wrong password!";
    private final static String USER_EXIST = "User with this username exist";
    private final static String USER_VARIABLE = "user";
    private final static String LOGIN_ERROR = "loginError";
    private final static String REGISTER_ERROR = "registerError";

    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapper mapper;

    public boolean validateUser(HttpServletRequest request, String validatedUserName, String validatedPassword) {
        User userFromDb = userDao.findByUserName(validatedUserName);
        if(userFromDb == null) {
            request.getSession().setAttribute(LOGIN_ERROR, WRONG_USER);
            return false;
        }
        else {
            if(userFromDb.getPassword().equals(validatedPassword)) {
                request.getSession().setAttribute(USER_VARIABLE, mapper.mapToUserDto(userFromDb));
                request.getSession().setAttribute(LOGIN_ERROR, Constants.STRING_EMPTY);
                return true;
            }
            else {
                request.getSession().setAttribute(LOGIN_ERROR, WRONG_PASSWORD);
                return false;
            }
        }
    }

    public boolean checkLoginIsUnique(HttpServletRequest request, String username) {
        if(userDao.findByUserName(username) == null) {
            request.getSession().setAttribute(REGISTER_ERROR, Constants.STRING_EMPTY);
            request.getSession().setAttribute(LOGIN_ERROR, Constants.STRING_EMPTY);
            return true;
        }
        else {
            request.getSession().setAttribute(REGISTER_ERROR, USER_EXIST);
            return false;
        }
    }
}
