public class Oda {
    private int odaNumarasi;     // Odanın numarası
    private int kapasite;        // Odanın kapasitesi (Kaç kişi alabilir)
    private double fiyat;        // Odanın fiyatı
    private String odaDurumu;    // Odanın durumu ("Boş" ya da "Dolu")
    private String manzara;      // Odanın manzarası ("Deniz" veya "Orman")


    // Oda numarasını döndüren getter
    public int getOdaNumarasi() {
        return odaNumarasi;
    }

    // Oda kapasitesini döndüren getter
    public int getKapasite() {
        return kapasite;
    }

    // Oda fiyatını döndüren getter
    public double getFiyat() {
        return fiyat;
    }

    // Oda durumunu döndüren getter
    public String getOdaDurumu() {
        return odaDurumu;
    }

    // Odanın manzarasını döndüren getter
    public String getManzara() {
        return manzara;
    }

    // Oda durumunu güncelleyen setter
    public void odaDurumuGuncelle(String yeniDurum) {
        this.odaDurumu = yeniDurum;
    }

    // Oda bilgilerini döndüren metod
    public String odaBilgileri() {
        return "Oda Numarası: " + odaNumarasi + ", Kapasite: " + kapasite + ", Fiyat: " + fiyat + " TL, Durum: " + odaDurumu + ", Manzara: " + manzara;
    }

    public void odaEkle(int odaNumarasi, int kapasite, double fiyat, String odaDurumu, String manzara){

    }
    public void odaSil(int odaNumarasi){

    }

    public void odaListele(){

    }
    public void odaListele(int OdaNumarasi){

    }
}