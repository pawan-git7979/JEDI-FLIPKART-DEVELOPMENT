package com.flipkart.bean;

import java.util.List;

public interface GymAdminInterface {
    public List<String> getAdminPrivileges();
    public void setAdminPrivileges(List<String> adminPrivileges);
//    public void GymAdmin(int id, String name, String email, String password, String role,
//                         List<String> adminPrivileges);
}
