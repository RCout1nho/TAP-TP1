package service.impl;

import dto.CreateRentDto;
import dto.RentWithTitleNameDto;
import model.Rent;
import repository.RentRepository;
import service.RentService;

import java.util.List;

public class RentMySqlImpl implements RentService {
    RentRepository rentRepository;

    public RentMySqlImpl() {
        this.rentRepository = new RentRepository();
    }

    @Override
    public boolean createRent(CreateRentDto rent) {
        return this.rentRepository.createRent(rent);
    }

    @Override
    public List<RentWithTitleNameDto> getAllByClientId(Integer clientId) {
        return rentRepository.getAllByClientId(clientId);
    }

    @Override
    public boolean returnARent(RentWithTitleNameDto rent) {
        return rentRepository.returnARent(rent);
    }
}
