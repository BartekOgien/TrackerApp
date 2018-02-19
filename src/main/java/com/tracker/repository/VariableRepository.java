package com.tracker.repository;

public class VariableRepository {
    private static String currentUsername;

    public static void setCurrentUsername(String currentUsername) {
        VariableRepository.currentUsername = currentUsername;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }
}
