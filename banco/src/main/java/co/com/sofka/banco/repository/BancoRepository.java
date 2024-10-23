package co.com.sofka.banco.repository;

import co.com.sofka.banco.model.entity.Bank;
import co.com.sofka.banco.repository.jpa.JpaBancoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class BancoRepository implements IBanco<Bank> {

    private final JpaBancoRepository jpaBancoRepository;


    @Override
    public Bank update(Bank item) {
        return null;
    }

    @Override
    public Bank save(Bank item) {
        return null;
    }

    @Override
    public Bank delete(Bank item) {
        return null;
    }

    @Override
    public Bank findById(Long id) {
        return null;
    }

    @Override
    public Long deleteByElementId(Long id) {
        return 0L;
    }

    @Override
    public List<Bank> getAll() {
        return jpaBancoRepository.findAll();
    }
}
