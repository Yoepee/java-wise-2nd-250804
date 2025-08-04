package com.back.standard.util;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class TestUtil {
    public static Scanner genScanner(String input) {
        return new Scanner(input);
    }

    public static ByteArrayOutputStream setOutToByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(byteArrayOutputStream));
        return byteArrayOutputStream;
    }
}
