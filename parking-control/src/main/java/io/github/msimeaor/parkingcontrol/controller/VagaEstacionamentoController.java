package io.github.msimeaor.parkingcontrol.controller;

import io.github.msimeaor.parkingcontrol.dtos.VagaEstacionamentoDto;
import io.github.msimeaor.parkingcontrol.model.VagaEstacionamento;
import io.github.msimeaor.parkingcontrol.service.VagaEstacionamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vagaEstacionamento")
public class VagaEstacionamentoController {

    private VagaEstacionamentoService vagaEstacionamentoService;

    public VagaEstacionamentoController(VagaEstacionamentoService vagaEstacionamentoService) {
        this.vagaEstacionamentoService = vagaEstacionamentoService;
    }

    @PostMapping
    public ResponseEntity<Object> saveVagaEstacionamento(@RequestBody @Valid VagaEstacionamentoDto vagaEstacionamentoDto) {

        if(vagaEstacionamentoService.existsByPlacaCarro(vagaEstacionamentoDto.getPlacaCarro())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Placa do carro já cadastrada!");
        }

        if(vagaEstacionamentoService.existsByNumeroVaga(vagaEstacionamentoDto.getNumeroVaga())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Numero da vaga já cadastrado!");
        }

        if(vagaEstacionamentoService.existsByApartamentoAndBlocoApartamento(vagaEstacionamentoDto.getApartamento(),
                                                                            vagaEstacionamentoDto.getBlocoApartamento())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Numero do apartamento ou numero do bloco já existem");
        }

        var vagaEstacionamento = new VagaEstacionamento();
        BeanUtils.copyProperties(vagaEstacionamentoDto, vagaEstacionamento);
        vagaEstacionamento.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC-3")));
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaEstacionamentoService.save(vagaEstacionamento));
    }

    @GetMapping
    public ResponseEntity<List<VagaEstacionamento>> getAllVagaEstacionamento() {
        return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneVagaEstacionamento(@PathVariable UUID id) {
        Optional<VagaEstacionamento> vagaEstacionamento = vagaEstacionamentoService.findById(id);
        if(!vagaEstacionamento.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: vaga não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamento.get());
    }

}
