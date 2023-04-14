package io.github.msimeaor.parkingcontrol.service;

import io.github.msimeaor.parkingcontrol.model.VagaEstacionamento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VagaEstacionamentoService {

    VagaEstacionamento save(VagaEstacionamento vagaEstacionamento);
    boolean existsByPlacaCarro(String placaCarro);
    boolean existsByNumeroVaga(String numeroVaga);
    boolean existsByApartamentoAndBlocoApartamento(String numeroApartamento, String numeroBloco);
    List<VagaEstacionamento> findAll();
    Optional<VagaEstacionamento> findById(UUID id);
    void deleteById(UUID id);

}
