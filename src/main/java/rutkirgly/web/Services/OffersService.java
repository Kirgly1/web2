package rutkirgly.web.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import rutkirgly.web.Repositories.OffersRepository;
import rutkirgly.web.Tables.Model;
import rutkirgly.web.Tables.Offers;
import rutkirgly.web.dto.ModelDTO;
import rutkirgly.web.dto.OffersDTO;
import rutkirgly.web.util.MappingUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OffersService implements BaseService<OffersDTO, Offers> {
    private OffersRepository offersRepository;
    private MappingUtil mappingUtil;

    @Autowired
    public void setOffersRepository(OffersRepository offersRepository) {
        this.offersRepository = offersRepository;
    }
    @Autowired
    public void setMappingUtil(MappingUtil mappingUtil) {
        this.mappingUtil = mappingUtil;
    }

    @CachePut(value = "offers", key = "#result.id")
    @Override
    public OffersDTO create(Offers offers) {
        Offers createOffers = offersRepository.save(offers);
        return mappingUtil.convertToDto(createOffers);
    }
    @CachePut(value = "offers", key = "id")
    @Override
    public OffersDTO update(UUID id, Offers offers) {
        Offers existingOffers = offersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offers not found"));

        existingOffers.setDescription(offers.getDescription());
        existingOffers.setEngine(offers.getEngine());
        existingOffers.setImageUrl(offers.getImageUrl());
        existingOffers.setMileage(offers.getMileage());
        existingOffers.setPrice(offers.getPrice());
        existingOffers.setTransmission(offers.getTransmission());
        existingOffers.setYear(offers.getYear());
        existingOffers.setModel(offers.getModel());
        existingOffers.setSeller(offers.getSeller());

        Offers updateOffers = offersRepository.save(existingOffers);
        return mappingUtil.convertToDto(updateOffers);
    }

    @CacheEvict(value = "offers", key = "#id")
    @Override
    public void delete(UUID id) {
        offersRepository.deleteById(id);
    }

    @Cacheable(value = "offers", key = "#id")
    @Override
    public OffersDTO getById(UUID id) {
        Offers offers = offersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offers not found"));
        return mappingUtil.convertToDto(offers);
    }

    @Cacheable(value = "Offers", key ="#root.methodName" )
    @Override
    public List<OffersDTO> getAll() {
        List<Offers> offers = offersRepository.findAll();
        return offers.stream()
                .map(mappingUtil::convertToDto)
                .collect(Collectors.toList());
    }
}
