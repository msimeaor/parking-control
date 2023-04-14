package io.github.msimeaor.parkingcontrol.controller;

import io.github.msimeaor.parkingcontrol.dtos.VagaEstacionamentoDto;
import io.github.msimeaor.parkingcontrol.model.VagaEstacionamento;
import io.github.msimeaor.parkingcontrol.service.impl.VagaEstacionamentoServiceImpl;
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

    private VagaEstacionamentoServiceImpl vagaEstacionamentoServiceImpl;

    public VagaEstacionamentoController(VagaEstacionamentoServiceImpl vagaEstacionamentoServiceImpl) {
        this.vagaEstacionamentoServiceImpl = vagaEstacionamentoServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Object> saveVagaEstacionamento(@RequestBody @Valid VagaEstacionamentoDto vagaEstacionamentoDto) {

        if(vagaEstacionamentoServiceImpl.existsByPlacaCarro(vagaEstacionamentoDto.getPlacaCarro())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Placa do carro já cadastrada!");
        }

        if(vagaEstacionamentoServiceImpl.existsByNumeroVaga(vagaEstacionamentoDto.getNumeroVaga())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Numero da vaga já cadastrado!");
        }

        if(vagaEstacionamentoServiceImpl.existsByApartamentoAndBlocoApartamento(vagaEstacionamentoDto.getApartamento(),
                                                                            vagaEstacionamentoDto.getBlocoApartamento())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Numero do apartamento ou numero do bloco já existem.");
        }

        var vagaEstacionamento = new VagaEstacionamento();
        BeanUtils.copyProperties(vagaEstacionamentoDto, vagaEstacionamento);
        vagaEstacionamento.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaEstacionamentoServiceImpl.save(vagaEstacionamento));
    }

    @GetMapping
    public ResponseEntity<List<VagaEstacionamento>> getAllVagaEstacionamento() {
        return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneVagaEstacionamento(@PathVariable UUID id) {
        Optional<VagaEstacionamento> vagaEstacionamentoOptional = vagaEstacionamentoServiceImpl.findById(id);
        if(!vagaEstacionamentoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: vaga não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoOptional.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteVagaEstacionamento(@PathVariable UUID id) {
        Optional<VagaEstacionamento> vagaEstacionamentoOptional = vagaEstacionamentoServiceImpl.findById(id);
        if(vagaEstacionamentoOptional.isPresent()) {
            vagaEstacionamentoServiceImpl.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Registro deletado com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga nao encontrada.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateVagaEstacionamento(@PathVariable UUID id, @RequestBody @Valid VagaEstacionamentoDto vagaEstacionamentoDto) {
        Optional<VagaEstacionamento> vagaEstacionamentoOptional = vagaEstacionamentoServiceImpl.findById(id);

        if(vagaEstacionamentoOptional.isPresent()) {
            VagaEstacionamento vagaEstacionamento = new VagaEstacionamento();
            BeanUtils.copyProperties(vagaEstacionamentoDto, vagaEstacionamento);
            vagaEstacionamento.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
            vagaEstacionamento.setId(vagaEstacionamentoOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoServiceImpl.save(vagaEstacionamento));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrado.");
    }

}
