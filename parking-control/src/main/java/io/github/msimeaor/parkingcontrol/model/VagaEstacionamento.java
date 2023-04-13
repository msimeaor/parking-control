package io.github.msimeaor.parkingcontrol.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "VAGA_ESTACIONAMENTO")
public class VagaEstacionamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(nullable = false, unique = true, name = "NUMERO_VAGA", length = 10)
    private String numeroVaga;

    @Column(nullable = false, name = "DATA_REGISTRO")
    private LocalDateTime dataRegistro;

    @Column(nullable = false, name = "NOME_RESPONSAVEL", length = 50)
    private String nomeResponsavel;

    @Column(nullable = false, name = "APARTAMENTO", length = 30)
    private String apartamento;

    @Column(nullable = false, name = "BLOCO_APARTAMENTO", length = 30)
    private String blocoApartamento;

    @Column(nullable = false, unique = true, name = "PLACA_CARRO", length = 8)
    private String placaCarro;

    @Column(nullable = false, name = "MODELO_CARRO",length = 50)
    private String modeloCarro;

    @Column(nullable = false, name = "COR_CARRO", length = 50)
    private String corCarro;



    public String getPlacaCarro() {
        return placaCarro;
    }
    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }
    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getCorCarro() {
        return corCarro;
    }
    public void setCorCarro(String corCarro) {
        this.corCarro = corCarro;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroVaga() {
        return numeroVaga;
    }
    public void setNumeroVaga(String numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }
    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }
    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getApartamento() {
        return apartamento;
    }
    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public String getBlocoApartamento() {
        return blocoApartamento;
    }
    public void setBlocoApartamento(String blocoApartamento) {
        this.blocoApartamento = blocoApartamento;
    }
}
