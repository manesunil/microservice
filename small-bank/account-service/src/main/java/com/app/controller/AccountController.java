package com.app.controller;

import com.app.dto.CustomerDto;
import com.app.service.IAccountService;
import com.app.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private IAccountService accountServiceImpl;



    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {

        accountServiceImpl.createAccount(customerDto);
        return new ResponseEntity<>(ResponseDto.builder()
                .statusMsg("Request process successfully")
                .statusCode("201")
                .build(), HttpStatus.CREATED);
    }

    @GetMapping(path = "/fetch/{mobileNumber}")
    public ResponseEntity<CustomerDto> fetchAccountDetail(@PathVariable("mobileNo") String mobileNo) {

        return ResponseEntity.ok(accountServiceImpl.fetchAccountDetail(mobileNo));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto) {

        accountServiceImpl.updateAccountDetail(customerDto);
        return new ResponseEntity<>(ResponseDto.builder()
                .statusMsg("Request process successfully")
                .statusCode("200")
                .build(), HttpStatus.OK);
    }

    @GetMapping(path = "/fetch/{mobileNumber}")
    public ResponseEntity<ResponseDto> deleteAccountDetail(@PathVariable("mobileNo") String mobileNo) {

        accountServiceImpl.deleteAccountDetail(mobileNo);
        return new ResponseEntity<>(ResponseDto.builder()
                .statusMsg("Request process successfully")
                .statusCode("200")
                .build(), HttpStatus.OK);
    }


}
