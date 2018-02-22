package com.tracker.repository;

import com.tracker.constants.Constants;
import com.tracker.model.User;

public class VariableRepository {
    private static User currentUser;
    private static String currentLoginError = Constants.STRING_EMPTY;
    private static String currentRegisterError = Constants.STRING_EMPTY;

    public static void setCurrentUser(User currentUser) {
        VariableRepository.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentLoginError(String currentLoginError) {
        VariableRepository.currentLoginError = currentLoginError;
    }

    public static String getCurrentRegisterError() {
        return currentRegisterError;
    }

    public static void setCurrentRegisterError(String currentRegisterError) {
        VariableRepository.currentRegisterError = currentRegisterError;
    }

    public static String getCurrentLoginError() {
        return currentLoginError;
    }
}
