package rutkirgly.web.Services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import rutkirgly.web.Repositories.UserRepository;
import rutkirgly.web.Repositories.UserRoleRepository;
import rutkirgly.web.Tables.User;
import rutkirgly.web.Tables.UserRole;
import rutkirgly.web.constants.Role;
import rutkirgly.web.dto.UserDTO;
import rutkirgly.web.util.MappingUtil;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements BaseService<UserDTO, User> {
    private UserRepository userRepository;
    private MappingUtil mappingUtil;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;

    private UserRoleService userRoleService;
    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository){this.userRoleRepository = userRoleRepository;}
    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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

    private UserDTO saveOrUpdate(UserDTO userDTO) throws EntityExistsException {

        userDTO.setModified(LocalDateTime.now());
        try {
            return mappingUtil.convertToDto(userRepository.saveAndFlush(mappingUtil.convertToEntity(userDTO)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getLocalizedMessage());
        }
    }

    public List<UserDTO> findAllByUsername(String username) {
        return userRepository.findAllByUsername(username)
                .stream()
                .map(mappingUtil::convertToDto)
                .toList();
    }

    public UserDTO registerNewUser(UserDTO userDTO) throws IllegalArgumentException {
        if(userRepository.findAllByUsername(userDTO.getUsername()).isEmpty()) {
            userDTO.setUserRoleDTO(userRoleService.findByRole(Role.ADMIN));
            userDTO.setCreated(LocalDateTime.now());
            userDTO.setIsActive(true);
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            return saveOrUpdate(userDTO);
        } else {
            throw new EntityExistsException(MessageFormat.format("User with username {0} already exists", userDTO.getUsername()));
        }
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

    @Transactional
    public void updateUserRole(UUID userId, UUID newRoleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserRole role = userRoleRepository.findById(newRoleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));


        user.setRoles(Collections.singleton(role.getRole()));

        userRepository.save(user);
    }



}
