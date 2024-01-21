package rutkirgly.web.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import rutkirgly.web.Repositories.OffersRepository;
import rutkirgly.web.Tables.Offers;
import rutkirgly.web.dto.OffersDTO;
import rutkirgly.web.util.MappingUtil;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@EnableCaching
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
    @Cacheable(value = "offers", key = "'getAllByModelId_' + #modelId")
    public List<OffersDTO> getAllByModelId(UUID modelId) {
        List<OffersDTO> offers = offersRepository.findAllByModelId(modelId).stream()
                .map(mappingUtil::convertToDto)
                .collect(Collectors.toList());
        offers.sort(Comparator.comparing(OffersDTO::getPrice));
        for (OffersDTO offer : offers) {
            System.out.println("Offer Price: " + offer.getPrice());
        }
        return offers;
    }
}
