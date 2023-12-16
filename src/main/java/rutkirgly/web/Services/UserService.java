package rutkirgly.web.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import rutkirgly.web.Repositories.UserRepository;
import rutkirgly.web.Tables.Offers;
import rutkirgly.web.Tables.User;
import rutkirgly.web.dto.OffersDTO;
import rutkirgly.web.dto.UserDTO;
import rutkirgly.web.util.MappingUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements BaseService<UserDTO, User> {
    private UserRepository userRepository;
    private MappingUtil mappingUtil;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setMappingUtil(MappingUtil mappingUtil) {
        this.mappingUtil = mappingUtil;
    }

    @Override
    public UserDTO create(User user) {
        User createUser = userRepository.save(user);
        return mappingUtil.convertToDto(createUser);
    }
    @Override
    public UserDTO update(UUID id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setImageUrl(user.getImageUrl());
        existingUser.setIsActive(user.getIsActive());
        existingUser.setLastName(user.getLastName());
        existingUser.setFirstName(user.getFirstName());
        User updateUser = userRepository.save(existingUser);
        return mappingUtil.convertToDto(updateUser);
    }


    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserDTO getById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mappingUtil.convertToDto(user);
    }


    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(mappingUtil::convertToDto)
                .collect(Collectors.toList());
    }
}
