package rutkirgly.web.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import rutkirgly.web.Repositories.BrandRepository;
import rutkirgly.web.Tables.Brand;
import rutkirgly.web.dto.BrandDTO;
import rutkirgly.web.util.MappingUtil;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@EnableCaching
@Service
public class BrandService implements BaseService<BrandDTO, Brand> {
    private BrandRepository brandRepository;
    private MappingUtil mappingUtil;
    @CachePut(value = "brands", key = "#result.id")
    @Override
    public BrandDTO create(Brand brand) {
        Brand createBrand = brandRepository.save(brand);
        return mappingUtil.convertToDto(createBrand);
    }
    @CachePut(value = "brands", key = "#result.id")
    @Override
    public BrandDTO update(UUID id, Brand brand) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        existingBrand.setName(brand.getName());
        Brand updateBrand = brandRepository.save(existingBrand);
        return mappingUtil.convertToDto(updateBrand);
    }
    @CacheEvict(value = "brands", key = "#result.id")
    @Override
    public void delete(UUID id) {
        brandRepository.deleteById(id);
    }
    @Cacheable(value = "brands", key = "#id")
    @Override
    public BrandDTO getById(UUID id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        return mappingUtil.convertToDto(brand);
    }
    @Cacheable(value = "brands", key = "'allBrands'")
    @Override
    public List<BrandDTO> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<BrandDTO> brandDTOs = brands.stream()
                .map(mappingUtil::convertToDto)
                .collect(Collectors.toList());

        brandDTOs.sort(Comparator.comparing(BrandDTO::getName));

        return brandDTOs;
    }


    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
    @Autowired
    public void setMappingUtil(MappingUtil mappingUtil) {
        this.mappingUtil = mappingUtil;
    }
}
