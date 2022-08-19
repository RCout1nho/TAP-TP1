package tp01.service.impl;

import tp01.dto.CreateTitleDto;
import tp01.repository.TitleRepository;
import tp01.service.TitleService;
import tp01.model.Title;

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

    @Override
    public boolean delete(Integer id) {
        return titleRepository.deleteTitle(id);
    }

    @Override
    public boolean update(Title title) {
        return titleRepository.updateTitle(title);
    }
}
