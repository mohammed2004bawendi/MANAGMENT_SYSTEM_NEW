package org.example.models;

public class Personel {
    private String isimSoyisim;   // اسم الموظف
    private String personelKimlik; // رقم هوية الموظف
    private String gorevAlani;   // الوظيفة
    private double salary;       // الراتب

    // Constructor
    public Personel(String isimSoyisim, String personelKimlik, String gorevAlani, double salary) {
        this.isimSoyisim = isimSoyisim;
        this.personelKimlik = personelKimlik;
        this.gorevAlani = gorevAlani;
        this.salary = salary;
    }

    // Default Constructor (إذا كنت بحاجة لكائن فارغ)
    public Personel() {
    }

    // Getters and Setters
    public String getIsimSoyisim() {
        return isimSoyisim;
    }

    public void setIsimSoyisim(String isimSoyisim) {
        this.isimSoyisim = isimSoyisim;
    }

    public String getPersonelKimlik() {
        return personelKimlik;
    }

    public void setPersonelKimlik(String personelKimlik) {
        this.personelKimlik = personelKimlik;
    }

    public String getGorevAlani() {
        return gorevAlani;
    }

    public void setGorevAlani(String gorevAlani) {
        this.gorevAlani = gorevAlani;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Method to display employee details in a readable format
    @Override
    public String toString() {
        return String.format("İsim Soyisim: %s, Kimlik: %s, Görev: %s, Maaş: %.2f",
                isimSoyisim, personelKimlik, gorevAlani, salary);
    }
}
