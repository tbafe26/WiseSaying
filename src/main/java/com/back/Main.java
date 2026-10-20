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
            else if (command.equals("등록")) {
                System.out.print("명언 : ");
                String quote = scanner.nextLine().trim();

                System.out.print("작가 : ");
                String author = scanner.nextLine().trim();

                System.out.println("1번 명언이 등록되었습니다.");
            }
        }

        scanner.close();
    }
}