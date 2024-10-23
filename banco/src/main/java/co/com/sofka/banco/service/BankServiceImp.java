package co.com.sofka.banco.service;

import co.com.sofka.banco.model.entity.Bank;
import co.com.sofka.banco.repository.IBanco;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankServiceImp implements IBankService {

    private final IBanco bankRepository;

    @Override
    public List<Bank> getAll() {
        return bankRepository.getAll();
    }
}
