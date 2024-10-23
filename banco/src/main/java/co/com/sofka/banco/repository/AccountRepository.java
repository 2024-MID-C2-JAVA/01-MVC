package co.com.sofka.banco.repository;

import co.com.sofka.banco.model.entity.Account;
import co.com.sofka.banco.repository.jpa.JpaAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccountRepository implements IGenericFuntion<Account>, IAccount {

    private final JpaAccountRepository repository;


    @Override
    public Account update(Account item) {
        return repository.save(item);
    }

    @Override
    public Account save(Account item) {
        return repository.save(item);
    }

    @Override
    public Account delete(Account item) {
        return null;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public Long deleteByElementId(Long id) {
        return 0L;
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public Account findByNumber(String accountNumber,String pin) {
        return repository.findByNumberAndPin(accountNumber,pin);
    }
}
