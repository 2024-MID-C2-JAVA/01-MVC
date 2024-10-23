package co.com.sofka.banco.service;

import co.com.sofka.banco.controller.model.request.BankTransactionBuys;
import co.com.sofka.banco.controller.model.request.BankTransactionWithdrawFromATM;
import co.com.sofka.banco.model.entity.BankTransaction;

import java.util.List;

public interface IBankTransactionService {
    List<BankTransaction> getAll();
    BankTransaction withdrawFromATM(BankTransactionWithdrawFromATM bankTransaction);

    BankTransaction buys(BankTransactionBuys item);
}
