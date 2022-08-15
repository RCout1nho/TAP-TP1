package service;

import dto.CreateTitleDto;
import model.Title;

import java.util.List;

public interface TitleService {
    void createTitle(CreateTitleDto title);

    List<Title> getAll();
}
