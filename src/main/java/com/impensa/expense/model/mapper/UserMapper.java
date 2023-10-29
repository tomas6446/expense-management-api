package com.impensa.expense.model.mapper;

import com.impensa.expense.model.User;
import com.impensa.expense.model.dto.DashboardDTO;
import com.impensa.expense.model.dto.RegisterDTO;
import com.impensa.expense.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
/**
 * @author Tomas Kozakas
 */
@Mapper(componentModel = "spring", uses = PasswordEncodingMapper.class)
public interface UserMapper {
    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @Mapping(target = "role", ignore = true)
    User registerDtoToUser(RegisterDTO registerDTO);

    UserDTO toUserDTO(User user);

    DashboardDTO toDashboardDTO(User user);
}
