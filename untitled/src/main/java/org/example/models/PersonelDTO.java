package org.example.models;

public class PersonelDTO {
    private String isimSoyisim;
    private String gorevAlani;

    // Constructor
    public PersonelDTO(String isimSoyisim, String gorevAlani) {
        this.isimSoyisim = isimSoyisim;
        this.gorevAlani = gorevAlani;
    }

    // Getters and Setters
    public String getIsimSoyisim() {
        return isimSoyisim;
    }

    public void setIsimSoyisim(String isimSoyisim) {
        this.isimSoyisim = isimSoyisim;
    }

    public String getGorevAlani() {
        return gorevAlani;
    }

    public void setGorevAlani(String gorevAlani) {
        this.gorevAlani = gorevAlani;
    }

    @Override
    public String toString() {
        return "PersonelDTO [isimSoyisim=" + isimSoyisim + ", gorevAlani=" + gorevAlani + "]";
    }
}
