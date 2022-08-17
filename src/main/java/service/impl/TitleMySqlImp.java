package service.impl;

import dto.CreateTitleDto;
import repository.TitleRepository;
import service.TitleService;
import model.Title;

import java.util.List;

public class TitleMySqlImp implements TitleService {
    TitleRepository titleRepository;

    public TitleMySqlImp() {
        this.titleRepository = new TitleRepository();
    }

    @Override
    public void createTitle(CreateTitleDto title) {
        this.titleRepository.createTitle(title);
    }

    @Override
    public List<Title> getAll() {
        return titleRepository.getAll();
    }

    @Override
    public List<Title> getAllAvailable() {
        return titleRepository.getAllAvailable();
    }
}
