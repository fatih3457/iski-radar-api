package com.iskiradar.iskiradarapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DamData {

    @JsonProperty("baslikAdi")
    private String barajAdi;

    @JsonProperty("dolulukOrani")
    private String dolulukOrani;

    // YENİ EKLENEN ALANLAR
    @JsonProperty("mevcutSuHacmi")
    private String mevcutSuHacmi;

    @JsonProperty("biriktirmeHacmi")
    private String biriktirmeHacmi;

    @JsonProperty("azamiSuSeviyesi")
    private String azamiSuSeviyesi;

    @JsonProperty("verim")
    private String verim;

    @JsonProperty("kaynakAdi")
    private String kaynakAdi;


    public String getBarajAdi() { return barajAdi; }
    public void setBarajAdi(String barajAdi) { this.barajAdi = barajAdi; }

    public String getDolulukOrani() { return dolulukOrani; }
    public void setDolulukOrani(String dolulukOrani) { this.dolulukOrani = dolulukOrani; }

    public String getMevcutSuHacmi() { return mevcutSuHacmi; }
    public void setMevcutSuHacmi(String mevcutSuHacmi) { this.mevcutSuHacmi = mevcutSuHacmi; }

    public String getBiriktirmeHacmi() { return biriktirmeHacmi; }
    public void setBiriktirmeHacmi(String biriktirmeHacmi) { this.biriktirmeHacmi = biriktirmeHacmi; }

    public String getAzamiSuSeviyesi() { return azamiSuSeviyesi; }
    public void setAzamiSuSeviyesi(String azamiSuSeviyesi) { this.azamiSuSeviyesi = azamiSuSeviyesi; }

    public String getVerim() { return verim; }
    public void setVerim(String verim) { this.verim = verim; }

    public String getKaynakAdi() { return kaynakAdi; }
    public void setKaynakAdi(String kaynakAdi) { this.kaynakAdi = kaynakAdi; }
}