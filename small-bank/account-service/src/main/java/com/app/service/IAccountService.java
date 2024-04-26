package com.app.service;

import com.app.dto.CustomerDto;

public interface IAccountService {

    public void  createAccount(CustomerDto customerDto);
    public CustomerDto fetchAccountDetail(String mobileNumber);
    public void  updateAccountDetail(CustomerDto customerDto);
    public void deleteAccountDetail(String mobileNumber);

}
