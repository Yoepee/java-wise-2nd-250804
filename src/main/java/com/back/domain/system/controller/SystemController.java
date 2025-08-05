package com.back.domain.system.controller;

import com.back.AppContext;
import com.back.Rq;

import java.util.Scanner;

public class SystemController {
    private Scanner sc;
    private Rq rq;

    public SystemController(){
        this.sc = AppContext.sc;
    }

    public Rq getRq() {
        return rq;
    }

    public void start() {
        System.out.println("== 명언 앱 ==");
    }

    public void stop() {
        sc.close();
    }

    public String getCommand() {
        System.out.print("명령) ");
        String cmd = sc.nextLine().trim();

        rq = new Rq(cmd);

        return rq.getActionName();
    }
}
