package io.github.msimeaor.parkingcontrol.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VagaEstacionamentoDto {

    @NotBlank
    private String numeroVaga;

    @NotBlank
    private String nomeResponsavel;

    @NotBlank
    private String apartamento;

    @NotBlank
    private String blocoApartamento;

    @NotBlank
    @Size(max = 8)
    private String placaCarro;

    @NotBlank
    private String modeloCarro;

    @NotBlank
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

    public String getNumeroVaga() {
        return numeroVaga;
    }
    public void setNumeroVaga(String numeroVaga) {
        this.numeroVaga = numeroVaga;
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
