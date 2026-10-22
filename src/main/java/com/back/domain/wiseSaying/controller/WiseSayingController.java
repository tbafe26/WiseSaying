
package com.back.domain.wiseSaying.controller;

import com.back.Rq;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner scanner;
    private final WiseSayingService service;

    public WiseSayingController(Scanner scanner, WiseSayingService service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void registerAction() {
        System.out.print("명언 : ");
        String quote = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = service.register(quote, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    public void listAction() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> registeredWiseSayings = service.list();

        for (WiseSaying wiseSaying : registeredWiseSayings) {
            System.out.printf("%d / %s / %s%n",
                    wiseSaying.getId(),
                    wiseSaying.getAuthor(),
                    wiseSaying.getQuote());
        }
    }

    public void deleteAction(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("올바른 id를 입력해주세요.");
            return;
        }

        boolean deleted = service.delete(id);

        if (!deleted) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    public void modifyAction(Rq rq) {
        int id = rq.getParamAsInt("id", -1);

        if (id == -1) {
            System.out.println("올바른 id를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = service.findById(id);

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

        service.modify(wiseSaying, quote, author);
    }
}
