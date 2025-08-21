package com.iskiradar.iskiradarapi.dto;

public class OverviewData {

    private double genelDolulukOraniOrtalamasi;
    private int barajSayisi;
    private String sonGuncellemeZamani;

    // --- Otomatik Oluşturulmuş Getter ve Setter'lar ---

    public double getGenelDolulukOraniOrtalamasi() {
        return genelDolulukOraniOrtalamasi;
    }

    public void setGenelDolulukOraniOrtalamasi(double genelDolulukOraniOrtalamasi) {
        this.genelDolulukOraniOrtalamasi = genelDolulukOraniOrtalamasi;
    }

    public int getBarajSayisi() {
        return barajSayisi;
    }

    public void setBarajSayisi(int barajSayisi) {
        this.barajSayisi = barajSayisi;
    }

    public String getSonGuncellemeZamani() {
        return sonGuncellemeZamani;
    }

    public void setSonGuncellemeZamani(String sonGuncellemeZamani) {
        this.sonGuncellemeZamani = sonGuncellemeZamani;
    }
}