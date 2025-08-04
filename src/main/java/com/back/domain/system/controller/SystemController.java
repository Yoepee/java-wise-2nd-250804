package com.back.domain.system.controller;

import com.back.AppContext;

import java.util.Scanner;

public class SystemController {
    private Scanner sc;

    public SystemController(){
        this.sc = AppContext.sc;
    }

    public void start() {
        System.out.println("== 명언 앱 ==");
    }

    public void stop() {
        sc.close();
    }
}
