package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    void run() {
        Scanner scanner = AppContext.scanner;
        WiseSayingController wiseSayingController = AppContext.wiseSayingController;
        SystemController systemController = AppContext.systemController;

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine().trim();

            Rq rq = new Rq(command);
            String actionName = rq.getActionName();

            if (actionName.equals("종료")) {
                systemController.exitAction();
                break;
            }

            switch (actionName) {
                case "등록" -> wiseSayingController.registerAction();
                case "목록" -> wiseSayingController.listAction();
                case "삭제" -> wiseSayingController.deleteAction(rq);
                case "수정" -> wiseSayingController.modifyAction(rq);
            }
        }

        scanner.close();
    }
}
