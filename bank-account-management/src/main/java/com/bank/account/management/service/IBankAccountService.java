package com.bank.account.management.service;

import com.bank.account.management.model.BankAccountDTO;
import com.bank.account.management.model.DepositDTO;
import com.bank.account.management.model.PurchaseDTO;
import com.bank.account.management.model.WithdrawalDTO;

import java.math.BigDecimal;

public interface IBankAccountService {
    BankAccountDTO createAccount(BankAccountDTO account);
    BankAccountDTO getAccount(Long id);
    void delete(Long id);
    void processDeposit(DepositDTO depositDTO);
    void processCardPurchase(PurchaseDTO purchaseDTO);
    void processWithdrawal(WithdrawalDTO withdrawalDTO);
}
