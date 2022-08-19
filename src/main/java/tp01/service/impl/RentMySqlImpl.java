package tp01.service.impl;

import tp01.dto.CreateRentDto;
import tp01.dto.RentWithTitleNameDto;
import tp01.repository.RentRepository;
import tp01.service.RentService;

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
