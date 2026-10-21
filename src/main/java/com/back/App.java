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
            else if (command.equals("등록")) registerAction();
            else if (command.equals("목록")) listAction();
            else if (command.startsWith("삭제?id=")) deleteAction(command);
        }

        scanner.close();
    }

    void registerAction() {
        if (lastIndex + 1 >= wiseSayings.length) {
            System.out.println("더 이상 명언을 등록할 수 없습니다.");
            return;
        }

        System.out.print("명언 : ");
        String quote = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = register(quote, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.id));
    }

    WiseSaying register(String quote, String author) {
        WiseSaying wiseSaying = new WiseSaying();
        wiseSaying.id = ++lastId;
        wiseSaying.quote = quote;
        wiseSaying.author = author;

        wiseSayings[++lastIndex] = wiseSaying;

        return wiseSaying;
    }

    void listAction() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        WiseSaying[] registeredWiseSayings = list();

        for (WiseSaying wiseSaying : registeredWiseSayings) {
            System.out.printf("%d / %s / %s%n", wiseSaying.id, wiseSaying.author, wiseSaying.quote);
        }
    }

    WiseSaying[] list() {
        WiseSaying[] result = new WiseSaying[lastIndex + 1];

        int index = 0;

        for (int i = lastIndex; i >= 0; i--) {
            result[index++] = wiseSayings[i];
        }

        return result;
    }

    void deleteAction(String command) {
        String[] parts = command.split("=", 2);

        int id;

        try {
            id = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("올바른 id를 입력해주세요.");
            return;
        }

        int deleteIndex = delete(id);

        if (deleteIndex == -1) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    int delete(int id) {
        int deleteIndex = -1;

        for (int i = 0; i <= lastIndex; i++) {
            if (wiseSayings[i].id == id) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex == -1) return deleteIndex;

        for (int i = deleteIndex + 1; i <= lastIndex; i++) {
            wiseSayings[i - 1] = wiseSayings[i];
        }

        wiseSayings[lastIndex] = null;
        lastIndex--;

        return deleteIndex;
    }
}