package com.project.timeRegistry.model.mapper;

import com.project.timeRegistry.model.domain.User;
import com.project.timeRegistry.model.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

//    @Mapping(source = "numberOfSeats", target = "seatCount")
    User userRequestToUser(UserRequest userRequest);
}
