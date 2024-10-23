package com.example.banco_yoder.service;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.banco_yoder.domain.Cuenta;
import com.example.banco_yoder.exception.CuentaNoEncontradaException;
import com.example.banco_yoder.repository.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CuentaService cuentaService;

    @Test
    public void testObtenerCuentaPorNumero_CuentaExistente() {
        Cuenta cuenta = new Cuenta("1", "yoder", "1234567890", 1000.0);
        when(cuentaRepository.findByNumeroCuenta("1234567890")).thenReturn(Mono.just(cuenta));

        Mono<Cuenta> result = cuentaService.obtenerCuentaPorNumero("1234567890");

        StepVerifier.create(result)
                .expectNext(cuenta)
                .verifyComplete();
    }

    @Test
    public void testObtenerCuentaPorNumero_CuentaNoExistente() {
        when(cuentaRepository.findByNumeroCuenta("1234567890")).thenReturn(Mono.empty());

        Mono<Cuenta> result = cuentaService.obtenerCuentaPorNumero("1234567890");

        StepVerifier.create(result)
                .expectError(CuentaNoEncontradaException.class)
                .verify();
    }
}
