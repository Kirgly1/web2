package rutkirgly.web.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rutkirgly.web.Repositories.UserRoleRepository;
import rutkirgly.web.Tables.UserRole;
import rutkirgly.web.constants.Role;
import rutkirgly.web.dto.UserRoleDTO;
import rutkirgly.web.util.MappingUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserRoleService implements BaseService<UserRoleDTO, UserRole> {
    private UserRoleRepository userRoleRepository;
    private MappingUtil mappingUtil;

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
    @Autowired
    public void setMappingUtil(MappingUtil mappingUtil) {
        this.mappingUtil = mappingUtil;
    }

    @Override
    public UserRoleDTO create(UserRole userRole) {
        UserRole createUserRole = userRoleRepository.save(userRole);
        return mappingUtil.convertToDto(createUserRole);

    }



    @Override
    public UserRoleDTO update(UUID id, UserRole userRole) {
        UserRole existingUserRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new  RuntimeException("UserRole not found"));
        existingUserRole.setRole(userRole.getRole());
        UserRole updateUserRole = userRoleRepository.save(existingUserRole);
        return mappingUtil.convertToDto(updateUserRole);
    }

    @Override
    public void delete(UUID id) {
        userRoleRepository.deleteById(id);
    }

    @Override
    public UserRoleDTO getById(UUID id) {
        UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRole not found"));
        return mappingUtil.convertToDto(userRole);
    }

    @Override
    public List<UserRoleDTO> getAll() {
        List<UserRole> userRole = userRoleRepository.findAll();
        return userRole.stream()
                .map(mappingUtil::convertToDto)
                .collect(Collectors.toList());
    }

    public UserRoleDTO findByRole(Role role) {
        return mappingUtil.convertToDto(userRoleRepository.findUserRoleByRole(role).orElseThrow(() -> new NoSuchElementException("No such role " + role)));
    }
}
