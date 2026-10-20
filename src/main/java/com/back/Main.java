package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("종료")) break;
        }

        scanner.close();
    }
}