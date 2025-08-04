package com.back;

import com.back.domain.system.controller.SystemController;

import java.util.Scanner;

public class AppContext {
    public static final Scanner sc;
    public static final SystemController systemController;
    static {
        sc = new Scanner(System.in);
        systemController = new SystemController();
    }
}
