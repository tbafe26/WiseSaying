package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository repository;

    public WiseSayingService(WiseSayingRepository repository) {
        this.repository = repository;
    }

    public WiseSaying register(String quote, String author) {
        return repository.register(quote, author);
    }

    public List<WiseSaying> list() {
        return repository.list();
    }

    public WiseSaying findById(int id) {
        return repository.findById(id);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public void modify(WiseSaying wiseSaying, String quote, String author) {
        repository.modify(wiseSaying, quote, author);
    }
}