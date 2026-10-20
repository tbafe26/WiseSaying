package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);

        WiseSaying[] wiseSayings = new WiseSaying[100];
        int wiseSayingIdx = -1;

        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("종료")) break;
            else if (command.equals("등록")) {
                System.out.print("명언 : ");
                String quote = scanner.nextLine().trim();

                System.out.print("작가 : ");
                String author = scanner.nextLine().trim();

                int wiseSayingId = ++wiseSayingIdx + 1;

                WiseSaying wiseSaying = new WiseSaying();
                wiseSaying.id = wiseSayingId;
                wiseSaying.quote = quote;
                wiseSaying.author = author;
                wiseSayings[wiseSayingIdx] = wiseSaying;

                System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSayingId));
            } else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                for (int i = wiseSayingIdx; i >= 0; i--) {
                    System.out.printf("%d / %s / %s\n", wiseSayings[i].id, wiseSayings[i].author, wiseSayings[i].quote);
                }
            }
        }

        scanner.close();
    }
}