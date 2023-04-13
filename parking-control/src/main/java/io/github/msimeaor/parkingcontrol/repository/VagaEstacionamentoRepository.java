package io.github.msimeaor.parkingcontrol.repository;

import io.github.msimeaor.parkingcontrol.model.VagaEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VagaEstacionamentoRepository extends JpaRepository<VagaEstacionamento, UUID> {

    boolean existsByPlacaCarro(String placaCarro);
    boolean existsByNumeroVaga(String numeroVaga);
    boolean existsByApartamentoAndBlocoApartamento(String numeroApartamento, String numeroBloco);

}
