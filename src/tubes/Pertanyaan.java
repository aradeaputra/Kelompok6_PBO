package tubes;

public class Pertanyaan {
    private String soal;
    private String jawaban;

    public Pertanyaan(String soal, String jawaban) {
        this.soal = soal;
        this.jawaban = jawaban;
    }

    public String getSoal() {
        return soal;
    }

    public String getJawaban() {
        return jawaban;
    }
    
}
