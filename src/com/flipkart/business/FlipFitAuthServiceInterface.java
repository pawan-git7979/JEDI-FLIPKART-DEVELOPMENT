package com.flipkart.business;

import com.flipkart.bean.FlipFitUser;
import com.flipkart.exception.AuthException;

import java.util.Scanner;

public interface FlipFitAuthServiceInterface {
    FlipFitUser login(String email, String password);
    boolean registerUser(Scanner scanner);
}
