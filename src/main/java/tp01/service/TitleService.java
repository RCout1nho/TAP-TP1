package tp01.service;

import tp01.dto.CreateTitleDto;
import tp01.model.Title;

import java.util.List;

public interface TitleService {
    void createTitle(CreateTitleDto title);

    List<Title> getAll();
    List<Title> getAllAvailable();
    boolean delete(Integer id);
    boolean update(Title title);

}
