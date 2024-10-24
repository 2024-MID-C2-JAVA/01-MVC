package com.bank.account.management.service;


import com.bank.account.management.model.BankAccountDTO;
import com.bank.account.management.model.CustomerDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long id);

    List<BankAccountDTO> getAllBankAccounts(Long id);

}
