package com.tracker.repository;

import com.tracker.constants.Constants;

public class VariableRepository {
    private static String currentUsername;
    private static String currentLoginError = Constants.STRING_EMPTY;
    private static String currentRegisterError = Constants.STRING_EMPTY;

    public static void setCurrentUsername(String currentUsername) {
        VariableRepository.currentUsername = currentUsername;
    }

    public static String getCurrentUsername() {
        return currentUsername;
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
