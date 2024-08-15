package com.clothing_store.Entity.Role;

public enum FuncionarioRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    FuncionarioRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
