package com.iskiradar.iskiradarapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DamData {

    // JSON'daki gerçek alan adı: baslikAdi
    @JsonProperty("baslikAdi")
    private String barajAdi;

    // JSON'daki gerçek alan adı: dolulukOrani ve tipi String
    @JsonProperty("dolulukOrani")
    private String dolulukOrani;

    // --- Manuel Getter ve Setter'lar ---
    public String getBarajAdi() {
        return barajAdi;
    }

    public void setBarajAdi(String barajAdi) {
        this.barajAdi = barajAdi;
    }

    public String getDolulukOrani() {
        return dolulukOrani;
    }

    public void setDolulukOrani(String dolulukOrani) {
        this.dolulukOrani = dolulukOrani;
    }
}