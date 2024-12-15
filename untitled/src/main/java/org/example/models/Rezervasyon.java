package org.example.models;

public class Rezervasyon {
    private double rezervasyonNo;
    private String isimSoyisim;
    private String tcKimlik;
    private String  kalinacakTarihler;
    private int odaBilgisi;

    // Constructor
    public Rezervasyon(double rezervasyonNo, String isimSoyisim, String tcKimlik, String kalinacakTarihler, int odaBilgisi) {
        this.rezervasyonNo = rezervasyonNo;
        this.isimSoyisim = isimSoyisim;
        this.tcKimlik = tcKimlik;
        this.kalinacakTarihler = kalinacakTarihler;
        this.odaBilgisi = odaBilgisi;
    }

    // Overloaded Constructor for new reservations
    public Rezervasyon(String isimSoyisim, String tcKimlik, String tarih, int odaBilgisi) {
        this.rezervasyonNo = Math.random(); // Generate a unique ID
        this.isimSoyisim = isimSoyisim;
        this.tcKimlik = tcKimlik;
        this.kalinacakTarihler = kalinacakTarihler;
        this.odaBilgisi = odaBilgisi;
    }

    // Getters and Setters
    public double getRezervasyonNo() {
        return rezervasyonNo;
    }

    public void setRezervasyonNo(double rezervasyonNo) {
        this.rezervasyonNo = rezervasyonNo;
    }

    public String getIsimSoyisim() {
        return isimSoyisim;
    }

    public void setIsimSoyisim(String isimSoyisim) {
        this.isimSoyisim = isimSoyisim;
    }

    public String getTcKimlik() {
        return tcKimlik;
    }

    public void setTcKimlik(String tcKimlik) {
        this.tcKimlik = tcKimlik;
    }




    public int getOdaBilgisi() {
        return odaBilgisi;
    }

    public void setOdaBilgisi(int odaBilgisi) {
        this.odaBilgisi = odaBilgisi;
    }

    public String getKalinacakTarihler() {
        return kalinacakTarihler;
    }


}
