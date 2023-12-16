package rutkirgly.web.Services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import rutkirgly.web.Repositories.ModelRepository;
import rutkirgly.web.Tables.Model;
import rutkirgly.web.dto.ModelDTO;
import rutkirgly.web.util.MappingUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ModelService implements BaseService<ModelDTO, Model> {
    private ModelRepository modelRepository;
    private MappingUtil mappingUtil;
    @CachePut(value = "models", key = "#result.id")
    @Override
    public ModelDTO create(Model model) {
        Model createModel = modelRepository.save(model);
        return mappingUtil.convertToDto(createModel);
    }
    @CachePut(value = "models", key = "#result.id")
    @Override
    public ModelDTO update(UUID id, Model model) {
        Model existingModel = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found"));
        existingModel.setName(model.getName());
        existingModel.setCategory(model.getCategory());
        existingModel.setImageUrl(model.getImageUrl());
        existingModel.setStartYear(model.getStartYear());
        existingModel.setEndYear(model.getEndYear());
        existingModel.setBrand(model.getBrand());

        Model updateModel = modelRepository.save(existingModel);
        return mappingUtil.convertToDto(updateModel);
    }
    @CacheEvict(value = "models", key = "#id")
    @Override
    public void delete(UUID id) {
        modelRepository.deleteById(id);
    }

    @Cacheable(value = "models", key = "#id")
    @Override
    public ModelDTO getById(UUID id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found"));
        return mappingUtil.convertToDto(model);
    }
    @Cacheable(value = "models", key ="#root.methodName" )

    @Override
    public List<ModelDTO> getAll() {
        List<Model> models = modelRepository.findAll();
        return models.stream()
                .map(mappingUtil::convertToDto)
                .collect(Collectors.toList());
    }
}
