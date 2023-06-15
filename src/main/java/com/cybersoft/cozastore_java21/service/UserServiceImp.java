package com.cybersoft.cozastore_java21.service;

import com.cybersoft.cozastore_java21.entity.UserEntity;
import com.cybersoft.cozastore_java21.payload.request.SignupRequest;

public interface UserServiceImp {

    boolean addUser(SignupRequest request);

}
