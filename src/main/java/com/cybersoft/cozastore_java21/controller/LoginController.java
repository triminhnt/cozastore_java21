package com.cybersoft.cozastore_java21.controller;

import com.cybersoft.cozastore_java21.entity.UserEntity;
import com.cybersoft.cozastore_java21.payload.request.SignupRequest;
import com.cybersoft.cozastore_java21.payload.response.BaseResponse;
import com.cybersoft.cozastore_java21.service.UserServiceImp;
import com.cybersoft.cozastore_java21.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    UserServiceImp userServiceImp;

    /**
     *
     *  statusCode : 200
     *  message : ""
     *  data : jwt
     *
     */
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signin(
            @RequestParam String email,
            @RequestParam String password
    ) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);

        authenticationManager.authenticate(token);

        // Neu chung thuc thanh cong se chay code tiep theo
        // Con that bai thi se bao loi khi chung thuc

        String jwt = jwtHelper.generateToken(email);
        BaseResponse response = new BaseResponse();

        response.setStatusCode(200);
        response.setData(jwt);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> save(@Valid SignupRequest request) {

        boolean isSuccess = userServiceImp.addUser(request);

        BaseResponse response = new BaseResponse();

        response.setStatusCode(200);
        response.setData(isSuccess);

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
