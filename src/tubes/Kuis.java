package tubes;

public class Kuis {
   private String kodeMK;
   private String namaKuis;
   private int mingguKuis;

    public Kuis(String kodeMK, String namaKuis, int mingguKuis) {
        this.kodeMK = kodeMK;
        this.namaKuis = namaKuis;
        this.mingguKuis = mingguKuis;
    }

    public String getKodeMK() {
        return kodeMK;
    }

    public String getNamaKuis() {
        return namaKuis;
    }

    public int getMingguKuis() {
        return mingguKuis;
    }
   
   
}
