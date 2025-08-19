package com.iskiradar.iskiradarapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DamGraphData {

    @JsonProperty("tarih")
    private String tarih;

    @JsonProperty("dolulukOrani")
    private Double dolulukOrani;

    // --- Manuel Getter ve Setter'lar ---
    public String getTarih() {
        return tarih;
    }
    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
    public Double getDolulukOrani() {
        return dolulukOrani;
    }
    public void setDolulukOrani(Double dolulukOrani) {
        this.dolulukOrani = dolulukOrani;
    }
}