package com.tracker.repository;

import com.tracker.model.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserSelectorTest {
    @InjectMocks
    private UserSelector userSelector;

    @Mock
    private UserDao userDao;

    @Test
    public void shouldSelectUser() {
        //Given
        User user1 = new User(1, "User1", "password");
        User user2 = new User(2, "User2", "password");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        when(userDao.findAll()).thenReturn(userList);

        //When
        User selectedUser = userSelector.assignUser();

        //Then
        assertEquals(User.class, selectedUser.getClass());
        assertTrue(selectedUser.getUserName().equals("User1") || selectedUser.getUserName().equals("User2"));
    }
}