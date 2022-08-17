package service;

import dto.CreateRentDto;
import dto.RentWithTitleNameDto;

import java.util.List;

public interface RentService {
    boolean createRent(CreateRentDto rent);
    List<RentWithTitleNameDto> getAllByClientId(Integer clientId);
    boolean returnARent(RentWithTitleNameDto rent);
}
