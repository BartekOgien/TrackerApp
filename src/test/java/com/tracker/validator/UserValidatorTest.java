package com.tracker.validator;

import com.tracker.mapper.Mapper;
import com.tracker.model.domain.User;
import com.tracker.model.dto.UserDto;
import com.tracker.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {
    @InjectMocks
    private UserValidator userValidator;

    @Mock
    private UserDao userDao;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private Mapper mapper;

    @Test
    public void shouldCheckedLoginIsUnique() {
        //Given
        String username = "user1";
        when(userDao.findByUserName(username)).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        doNothing().when(session).setAttribute(anyString(), anyObject());

        //When
        boolean isUniqueUsername = userValidator.checkLoginIsUnique(request, username);

        //Then
        assertTrue(isUniqueUsername);
    }

    @Test
    public void shouldCheckedLoginIsNotUnique() {
        //Given
        User user = new User("username", "password");
        when(userDao.findByUserName(user.getUserName())).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        doNothing().when(session).setAttribute(anyString(), anyObject());

        //When
        boolean isUniqueUsername = userValidator.checkLoginIsUnique(request, user.getUserName());

        //Then
        assertFalse(isUniqueUsername);
    }

    @Test
    public void shouldValidateUserOk() {
        //Given
        User user = new User("username", "password");
        UserDto userDto = new UserDto(1, "username");
        when(userDao.findByUserName(user.getUserName())).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        when(mapper.mapToUserDto(user)).thenReturn(userDto);
        doNothing().when(session).setAttribute(anyString(), anyObject());

        //When
        boolean isValidateOk = userValidator.validateUser(request, user.getUserName(), user.getPassword());

        //Then
        assertTrue(isValidateOk);
    }
}
