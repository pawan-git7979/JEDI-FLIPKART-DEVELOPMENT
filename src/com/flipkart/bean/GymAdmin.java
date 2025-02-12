package com.flipkart.bean;

import java.util.List;

public class GymAdmin extends User {
    private List<String> adminPrivileges;

    public GymAdmin() {}
    public GymAdmin(int id, String name, String email, String password, String role, 
                 List<String> adminPrivileges) {
        super(id, name, email, password, role);
        this.adminPrivileges = adminPrivileges;
    }

    public List<String> getAdminPrivileges() { return adminPrivileges; }
    public void setAdminPrivileges(List<String> adminPrivileges) { this.adminPrivileges = adminPrivileges; }
}
