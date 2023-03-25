package com.example.app_for_task.model.tasks;

public enum Permission {
    TASKS_READ("tasks:read"),
    TASKS_CREATE("tasks:create"),
    TASKS_WRITE("tasks:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}