package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.UserDTO;

import java.util.Map;

public interface IUserService {
    UserDTO findOneByUserNameAndStatus(String name, int status);
    Map<Long,String> getStaffMaps();
}
