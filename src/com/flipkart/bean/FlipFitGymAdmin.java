package com.flipkart.bean;

import java.util.List;

public class FlipFitGymAdmin extends FlipFitUser {
    private List<String> adminPrivileges;

    public FlipFitGymAdmin() {}

    public FlipFitGymAdmin(int id, String name, String email, String password, String role,
                           List<String> adminPrivileges) {
        super(id, name, email, password, role);
        this.adminPrivileges = adminPrivileges;
    }

    public List<String> getAdminPrivileges() { return adminPrivileges; }
    public void setAdminPrivileges(List<String> adminPrivileges) { this.adminPrivileges = adminPrivileges; }
}
