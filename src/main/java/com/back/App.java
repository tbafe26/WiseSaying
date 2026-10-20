package com.back;

import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    WiseSaying[] wiseSayings = new WiseSaying[100];

    int lastIndex = -1;
    int lastId = 0;

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("종료")) break;
            else if (command.equals("등록")) register();
            else if (command.equals("목록")) list();
        }

        scanner.close();
    }

    void register() {
        if (lastIndex + 1 >= wiseSayings.length) {
            System.out.println("더 이상 명언을 등록할 수 없습니다.");
            return;
        }

        System.out.print("명언 : ");
        String quote = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = create(quote, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.id));
    }

    WiseSaying create(String quote, String author) {
        WiseSaying wiseSaying = new WiseSaying();
        wiseSaying.id = ++lastId;
        wiseSaying.quote = quote;
        wiseSaying.author = author;

        wiseSayings[++lastIndex] = wiseSaying;

        return wiseSaying;
    }

    void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        WiseSaying[] registeredWiseSayings = getWiseSayings();

        for (WiseSaying wiseSaying : registeredWiseSayings) {
            System.out.printf("%d / %s / %s%n", wiseSaying.id, wiseSaying.author, wiseSaying.quote);
        }
    }

    WiseSaying[] getWiseSayings() {
        WiseSaying[] result = new WiseSaying[lastIndex + 1];

        int index = 0;

        for (int i = lastIndex; i >= 0; i--) {
            result[index++] = wiseSayings[i];
        }

        return result;
    }
}