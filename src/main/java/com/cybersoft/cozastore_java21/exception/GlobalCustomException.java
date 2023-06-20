package com.cybersoft.cozastore_java21.exception;

import com.cybersoft.cozastore_java21.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalCustomException {

//  Noi dang ky cac Exception se kich hoat code ben trong ham
//    @ExceptionHandler(UserNotFoundException.class)
    @ExceptionHandler(CustomeFileNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(Exception ex) {

        BaseResponse response = new BaseResponse();

//        response.setStatusCode(401);
        response.setStatusCode(500);
        response.setData(ex.getMessage());

        //return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED); // Code 401
        return new ResponseEntity<>("", HttpStatus.OK); // Code 401
    }
}
