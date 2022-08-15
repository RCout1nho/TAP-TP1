package service.impl;

import dto.CreateRent;
import repository.RentRepository;
import service.RentService;

public class RentMySqlImpl implements RentService {
    RentRepository rentRepository;

    public RentMySqlImpl() {
        this.rentRepository = new RentRepository();
    }

    @Override
    public void createRent(CreateRent rent) {
        this.rentRepository.createRent(rent);
    }
}
