package io.github.msimeaor.parkingcontrol.service;

import io.github.msimeaor.parkingcontrol.model.VagaEstacionamento;
import io.github.msimeaor.parkingcontrol.repository.VagaEstacionamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VagaEstacionamentoService {

    private final VagaEstacionamentoRepository vagaEstacionamentoRepository;

    public VagaEstacionamentoService(VagaEstacionamentoRepository vagaEstacionamentoRepository) {
        this.vagaEstacionamentoRepository = vagaEstacionamentoRepository;
    }

    @Transactional
    public VagaEstacionamento save(VagaEstacionamento vagaEstacionamento) {
        return vagaEstacionamentoRepository.save(vagaEstacionamento);
    }

    public boolean existsByPlacaCarro(String placaCarro) {
        return vagaEstacionamentoRepository.existsByPlacaCarro(placaCarro);
    }

    public boolean existsByNumeroVaga(String numeroVaga) {
        return vagaEstacionamentoRepository.existsByNumeroVaga(numeroVaga);
    }

    public boolean existsByApartamentoAndBlocoApartamento(String numeroApartamento, String numeroBloco) {
        return vagaEstacionamentoRepository.existsByApartamentoAndBlocoApartamento(numeroApartamento, numeroBloco);
    }

    public List<VagaEstacionamento> findAll() {
        return vagaEstacionamentoRepository.findAll();
    }

    public Optional<VagaEstacionamento> findById(UUID id) {
        return vagaEstacionamentoRepository.findById(id);
    }
}
