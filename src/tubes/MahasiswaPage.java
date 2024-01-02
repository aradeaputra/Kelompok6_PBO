package tubes;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;

public class MahasiswaPage extends javax.swing.JFrame {
    static final String URL = "jdbc:mysql://localhost:3306/tubes";
    static final String USER = "root";
    static final String PASS = "";
    private Connection conn;
    private String nama;
    private int iKuis = 0;
    private double totalKuis = 0.0;
    
    public MahasiswaPage(String name) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASS);
        initComponents();
        jLabel1.setText("Selamat Datang, " + name + "!");
        this.nama = name;
        setTitle("Mahasiswa Page");
        populateList();
        setLocationRelativeTo(null);
    }
    
    public void populateList() throws SQLException{
        DefaultListModel<String> listModel = new DefaultListModel<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM mata_kuliah");
        while(rs.next()){
            String data = rs.getString("nama");
            listModel.addElement(data);
        }
        listMK.setModel(listModel);
        stmt.close(); rs.close();
    }
    
    public String getKodeMK() throws SQLException {
        String selectedVal = listMK.getSelectedValue();
        String sql = "SELECT kode_mk FROM mata_kuliah WHERE nama=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, selectedVal);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return rs.getString("kode_mk");
        }
        return null;
    }
    
    public int getIdKuis() throws SQLException {
        String selectedVal = listKuis.getSelectedValue();
        String[] parts = selectedVal.split(" - ");
        String sql = "SELECT * FROM kuis WHERE kode_mk=? AND minggu_kuis=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, parts[0]);
        stmt.setInt(2, Integer.parseInt(parts[2]));
        ResultSet rs = stmt.executeQuery();
        int id_kuis = -1;
        if(rs.next()){
            return rs.getInt("id");
        }
        return -1;
    }
    
    public void showKuis(String kodeMK) throws SQLException{
        DefaultListModel<String> listModel = new DefaultListModel<>();
        String sql = "SELECT * FROM kuis WHERE kode_mk=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, kodeMK);
        ResultSet rs = stmt.executeQuery();
        if(rs!=null){
            while(rs.next()){
                String data1 = rs.getString("kode_mk");
                String data2 = rs.getString("nama_kuis");
                int data3 = rs.getInt("minggu_kuis");
                listModel.addElement(data1 + " - " + data2 + " - " + data3);
            }
            listKuis.setModel(listModel);
        } else {
            JOptionPane.showMessageDialog(null, "Kuis " + kodeMK + " belum tersedia");
        }
        listKuis.setModel(listModel);
        stmt.close(); rs.close();
    }
    
    public double kerjakanKuis(int id) throws SQLException {
        double nilai = 0.0;
        double i = 0.0;
        String sql = "SELECT * FROM pertanyaan WHERE id_kuis=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("Soal: "));
            panel.add(new JLabel(rs.getString("soal")));
            panel.add(new JLabel("Jawaban: "));
            JTextField jawabanField = new JTextField();
            panel.add(jawabanField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Pertanyaan",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if(jawabanField.getText().equals(rs.getString("jawaban"))){
                    nilai += 1.0;
                }              
            }
            i++;
        }
        nilai = (nilai/i)*100.0;
        JOptionPane.showMessageDialog(null, "Anda mendapatkan nilai : " + nilai);
        return nilai;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMK = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listKuis = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnMK = new javax.swing.JButton();
        btnKuis = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 204)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        jLabel1.setText("SELAMAT DATANG, !");

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnLogout.setText("LogOut");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        listMK.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane1.setViewportView(listMK);

        listKuis.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane2.setViewportView(listKuis);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Anda telah mengerjakan kuis 0 kali dan mendapatkan total nilai 0.");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Pilih Mata Kuliah terlebih dahulu sebelum memulai Kuis.");

        btnMK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnMK.setText("Pilih Mata Kuliah");
        btnMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMKActionPerformed(evt);
            }
        });

        btnKuis.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnKuis.setText("Pilih Kuis");
        btnKuis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKuisActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Rata-rata nilai : NaN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLogout))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(btnMK))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnKuis)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnLogout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMK)
                    .addComponent(btnKuis))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        try{
            dispose();
            new HomePage().setVisible(true);
        } catch(SQLException e) {
            e.printStackTrace();
        }       
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMKActionPerformed
        try{
            String kodeMK = getKodeMK();
            populateList();
            showKuis(kodeMK);
            jLabel2.setText("Rata-rata nilai : " + (totalKuis / (double) iKuis));
            jLabel4.setText("Anda telah mengerjakan kuis " + iKuis + " kali dan mendapatkan total nilai " + totalKuis);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnMKActionPerformed

    private void btnKuisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKuisActionPerformed
        try{
            iKuis++;
            totalKuis += kerjakanKuis(getIdKuis());
            jLabel2.setText("Rata-rata nilai : " + (totalKuis/(double)iKuis));
            jLabel4.setText("Anda telah mengerjakan kuis " + iKuis + " kali dan mendapatkan total nilai " + totalKuis);
            populateList();
            showKuis(getKodeMK());
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnKuisActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKuis;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listKuis;
    private javax.swing.JList<String> listMK;
    // End of variables declaration//GEN-END:variables
}
