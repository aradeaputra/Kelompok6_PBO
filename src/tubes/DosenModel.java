package tubes;

public class DosenModel extends UserModel {
    public DosenModel(int nip, String password, String nama) {
        this.nip = nip; this.password = password; this.nama = nama;
    }

    public int getNip() {
        return nip;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
