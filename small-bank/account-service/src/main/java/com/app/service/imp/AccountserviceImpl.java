package com.app.service.imp;

import com.app.constants.AccountsConstants;
import com.app.dto.CustomerDto;
import com.app.exception.CustomerAlreadyExistException;
import com.app.exception.ResourceNotFoundException;
import com.app.mapper.CustomerMapper;
import com.app.model.Accounts;
import com.app.model.Customer;
import com.app.repo.AccountRepository;
import com.app.repo.CustomerRepository;
import com.app.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountserviceImpl implements IAccountService {

    public AccountRepository accountRepository;
    public CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> opCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
            if(opCustomer.isPresent()){
              throw new CustomerAlreadyExistException("customer already exist");
            }

        Customer customer = customerRepository.save(CustomerMapper.mapToCustomer(customerDto, new Customer()));
        Accounts accounts = createNewAccount(customer);
        accountRepository.save(accounts);
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccountDetail(String mobileNumber) {
        customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Resource Not find"));

        return null;
    }

    @Override
    public void updateAccountDetail(CustomerDto customerDto) {

        Optional<Customer> opCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(opCustomer.isPresent()){
            throw new CustomerAlreadyExistException("customer already exist");
        }

        Customer customer = customerRepository.save(CustomerMapper.mapToCustomer(customerDto, new Customer()));
        Accounts accounts = createNewAccount(customer);
        accountRepository.save(accounts);

    }

    @Override
    public void deleteAccountDetail(String mobileNumber) {

        customerRepository.deleteByMobileNumber(mobileNumber);
    }
}
