package co.com.sofka.banco.controller.banco;

import co.com.sofka.banco.controller.model.request.BankTransactionWithdrawFromATM;
import co.com.sofka.banco.controller.model.response.GenericResponse;
import co.com.sofka.banco.model.entity.BankTransaction;
import co.com.sofka.banco.service.IBankTransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bankTransaction")
@AllArgsConstructor
public class BankTransactionController {

    private static final Logger logger = LoggerFactory.getLogger(BankTransactionController.class);

    private final IBankTransactionService service;

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<BankTransaction>>> getAll() {
        logger.info("Buscando todos los bancos");
        return new ResponseEntity<>( GenericResponse.<List<BankTransaction>>builder().bodyOut(service.getAll())
                .message("Bancos encontrados").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }



    @PostMapping("/retitarCajero")
    public ResponseEntity<GenericResponse<BankTransaction>> bankTransactionWithdrawFromATM(@Valid @RequestBody BankTransactionWithdrawFromATM bankTransaction) {
        logger.info("Guardando transaccion bancaria");
        return new ResponseEntity<>( GenericResponse.<BankTransaction>builder().bodyOut(service.withdrawFromATM(bankTransaction))
                .message("Transaccion bancaria guardada").code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

}
