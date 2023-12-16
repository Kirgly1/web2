package rutkirgly.web.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rutkirgly.web.Tables.*;
import rutkirgly.web.dto.*;
@Component
public class MappingUtil {

    private final ModelMapper modelMapper;

    @Autowired
    public MappingUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BrandDTO convertToDto(Brand brand) {
        return modelMapper.map(brand, BrandDTO.class);
    }

    public Brand convertToEntity(BrandDTO brandDto) {
        return modelMapper.map(brandDto, Brand.class);
    }

    public ModelDTO convertToDto(Model model) {
        return modelMapper.map(model, ModelDTO.class);
    }

    public Model convertToEntity(ModelDTO modelDto) {
        return modelMapper.map(modelDto, Model.class);
    }

    public OffersDTO convertToDto(Offers offer) {
        return modelMapper.map(offer, OffersDTO.class);
    }

    public Offers convertToEntity(Offers offerDto) {
        return modelMapper.map(offerDto, Offers.class);
    }

    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserRoleDTO convertToDto(UserRole userRole) {
        return modelMapper.map(userRole, UserRoleDTO.class);
    }

    public UserRole convertToEntity(UserRoleDTO userRoleDto) {
        return modelMapper.map(userRoleDto, UserRole.class);
    }
}