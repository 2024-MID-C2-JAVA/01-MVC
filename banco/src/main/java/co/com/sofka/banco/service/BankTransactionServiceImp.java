package co.com.sofka.banco.service;

import co.com.sofka.banco.configuration.middleware.AccountNotExistException;
import co.com.sofka.banco.configuration.middleware.AccountNotHaveBalanceException;
import co.com.sofka.banco.controller.model.request.BankTransactionWithdrawFromATM;
import co.com.sofka.banco.model.entity.Account;
import co.com.sofka.banco.model.entity.Bank;
import co.com.sofka.banco.model.entity.BankTransaction;
import co.com.sofka.banco.model.entity.TypeTransaction;
import co.com.sofka.banco.repository.AccountRepository;
import co.com.sofka.banco.repository.BankRepository;
import co.com.sofka.banco.repository.BankTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BankTransactionServiceImp implements IBankTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(BankTransactionServiceImp.class);

    private final BankTransactionRepository repository;

    private final AccountRepository accountRepository;

    private final BankRepository bankRepository;

    @Override
    public List<BankTransaction> getAll() {
        return repository.getAll();
    }

    @Override
    @Transactional
    public BankTransaction withdrawFromATM(BankTransactionWithdrawFromATM bankTransaction) {
        Account byNumber = accountRepository.findByNumber(bankTransaction.getAccountNumber(), bankTransaction.getPin());
        if (byNumber == null) {
            throw new AccountNotExistException("La cuenta no existe");
        }

        logger.info("Cuenta encontrada: {} {} {}", byNumber,byNumber.getBalance(),bankTransaction.getAmount()+1);

        if (byNumber.getBalance()-(bankTransaction.getAmount()+1) < 0) {
            throw new AccountNotHaveBalanceException("No tiene saldo suficiente.");
        }

        byNumber.setBalance(byNumber.getBalance() - (bankTransaction.getAmount()+1));
        accountRepository.save(byNumber);

        Bank bankById = bankRepository.findById(1L);
        bankById.setBalance(bankById.getBalance()+1);
        bankRepository.save(bankById);

        BankTransaction bankTransaction1 = new BankTransaction();
        bankTransaction1.setOriginAccount(byNumber);
        bankTransaction1.setDestinationAccount(byNumber);
        bankTransaction1.setAmount(bankTransaction.getAmount());

        TypeTransaction typeTransaction = new TypeTransaction();
        typeTransaction.setId(2);

        bankTransaction1.setTypeTransaction(typeTransaction);
        bankTransaction1.setCreatedAt(new Date(System.currentTimeMillis()));
        return repository.save(bankTransaction1);
    }
}
