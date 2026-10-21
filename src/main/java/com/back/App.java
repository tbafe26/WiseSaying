package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            if (command.equals("종료")) break;
            else if (command.equals("등록")) registerAction();
            else if (command.equals("목록")) listAction();
            else if (command.startsWith("삭제?id=")) deleteAction(command);
            else if (command.startsWith("수정?id=")) modifyAction(command);
        }

        scanner.close();
    }

    private void registerAction() {
        System.out.print("명언 : ");
        String quote = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = register(quote, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    private WiseSaying register(String quote, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, quote, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    private void listAction() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> registeredWiseSayings = list();

        for (WiseSaying wiseSaying : registeredWiseSayings) {
            System.out.printf("%d / %s / %s%n",
                    wiseSaying.getId(),
                    wiseSaying.getAuthor(),
                    wiseSaying.getQuote());
        }
    }

    private List<WiseSaying> list() {
        return wiseSayings.reversed();
    }

    private Integer parseId(String command) {
        String[] parts = command.split("=", 2);

        try {
            return Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("올바른 id를 입력해주세요.");
            return null;
        }
    }

    private int findIndexById(int id) {
        return IntStream.range(0, wiseSayings.size())
                .filter(index -> wiseSayings.get(index).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    private WiseSaying findById(int id) {
        int index = findIndexById(id);

        if (index == -1) return null;

        return wiseSayings.get(index);
    }

    private void deleteAction(String command) {
        Integer id = parseId(command);

        if (id == null) return;

        boolean deleted = delete(id);

        if (!deleted) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    private boolean delete(int id) {
        int index = findIndexById(id);

        if (index == -1) return false;

        wiseSayings.remove(index);

        return true;
    }

    private void modifyAction(String command) {
        Integer id = parseId(command);

        if (id == null) return;

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.printf("명언(기존) : %s%n", wiseSaying.getQuote());
        System.out.print("명언 : ");
        String quote = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s%n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        modify(wiseSaying, quote, author);
    }

    private void modify(WiseSaying wiseSaying, String quote, String author) {
        wiseSaying.setQuote(quote);
        wiseSaying.setAuthor(author);
    }
}