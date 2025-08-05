package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppContext.sc = new Scanner(System.in);
        new App().run();
    }
}
