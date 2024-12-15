package org.example.models;

public class OdaDTO {
    private int odaNumarasi;
    private int kapasite;
    private double fiyat;
    private String durum; // "Dolu" أو "Boş"
    private String manzara;

    // Constructors
    public OdaDTO() {}

    public OdaDTO(int odaNumarasi, int kapasite, double fiyat, String durum, String manzara) {
        this.odaNumarasi = odaNumarasi;
        this.kapasite = kapasite;
        this.fiyat = fiyat;
        this.durum = durum;
        this.manzara = manzara;
    }

    // Getters and Setters
    public int getOdaNumarasi() {
        return odaNumarasi;
    }

    public void setOdaNumarasi(int odaNumarasi) {
        this.odaNumarasi = odaNumarasi;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getManzara() {
        return manzara;
    }

    public void setManzara(String manzara) {
        this.manzara = manzara;
    }

    @Override
    public String toString() {
        return "Oda No: " + odaNumarasi + ", Kapasite: " + kapasite + ", Fiyat: " + fiyat +
                ", Durum: " + durum + ", Manzara: " + manzara;
    }
}
