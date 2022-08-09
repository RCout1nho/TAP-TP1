package service.impl;

import dto.CreateTitleDto;
import repository.TitleRepository;
import service.TitleService;

public class TitleMySqlImp implements TitleService {
    TitleRepository titleRepository;

    public TitleMySqlImp() {
        this.titleRepository = new TitleRepository();
    }

    @Override
    public void createTitle(CreateTitleDto title) {
        this.titleRepository.createTitle(title);
    }
}
