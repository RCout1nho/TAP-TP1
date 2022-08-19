package tp01.service;

import tp01.dto.CreateRentDto;
import tp01.dto.RentWithTitleNameDto;

import java.util.List;

public interface RentService {
    boolean createRent(CreateRentDto rent);
    List<RentWithTitleNameDto> getAllByClientId(Integer clientId);
    boolean returnARent(RentWithTitleNameDto rent);
}
