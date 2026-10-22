package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WiseSayingRepository {
    private final List<WiseSaying> wiseSayings = new ArrayList<>();
    private int lastId = 0;

    public WiseSaying register(String quote, String author) {
        WiseSaying wiseSaying = new WiseSaying(++lastId, quote, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> list() {
        return wiseSayings.reversed();
    }

    private int findIndexById(int id) {
        return IntStream.range(0, wiseSayings.size())
                .filter(index -> wiseSayings.get(index).getId() == id)
                .findFirst()
                .orElse(-1);
    }

    public WiseSaying findById(int id) {
        int index = findIndexById(id);

        if (index == -1) return null;

        return wiseSayings.get(index);
    }

    public boolean delete(int id) {
        int index = findIndexById(id);

        if (index == -1) return false;

        wiseSayings.remove(index);
        return true;
    }

    public void modify(WiseSaying wiseSaying, String quote, String author) {
        wiseSaying.setQuote(quote);
        wiseSaying.setAuthor(author);
    }
}