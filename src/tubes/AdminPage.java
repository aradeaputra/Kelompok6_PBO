package tubes;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;

public class AdminPage extends javax.swing.JFrame {
    static final String URL = "jdbc:mysql://localhost:3306/tubes";
    static final String USER = "root";
    static final String PASS = "";
    private Connection conn;
    private String nama;
    
    public AdminPage(String name) throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASS);
        initComponents();
        labelWelcome.setText("Selamat Datang, " + name + "!");
        this.nama = name;
        setTitle("Admin Page");
        this.populateList();
        setLocationRelativeTo(null);
    }
    
    public void populateList() throws SQLException{
        DefaultListModel<String> listModel = new DefaultListModel<>();
        DefaultListModel<String> listModel2 = new DefaultListModel<>();
        DefaultListModel<String> listModel3 = new DefaultListModel<>();
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM mata_kuliah");
        Statement stmt2 = conn.createStatement();
        ResultSet rs2 = stmt2.executeQuery("SELECT * FROM dosen");
        Statement stmt3 = conn.createStatement();
        ResultSet rs3 = stmt3.executeQuery("SELECT * FROM mahasiswa");
        while(rs.next()){
            String data1 = rs.getString("kode_mk");
            String data2 = rs.getString("nama");
            listModel.addElement(data1 + " - " + data2);
        }
        while(rs2.next()){
            int data1 = rs2.getInt("nip");
            String data2 = rs2.getString("nama");
            listModel2.addElement(data1 + " - " + data2);
        }
        while(rs3.next()){
            int data1 = rs3.getInt("nip");
            String data2 = rs3.getString("nama");
            listModel3.addElement(data1 + " - " + data2);
        }
        listMK.setModel(listModel);
        listDosen.setModel(listModel2);
        listMhs.setModel(listModel3);
        stmt.close(); stmt2.close(); stmt3.close();
        rs.close(); rs2.close(); rs3.close();
    }
    
    private void insertMK(String kodeMK, String namaMK) throws SQLException{
        String sql = "INSERT INTO mata_kuliah (kode_mk, nama) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, kodeMK);
        ps.setString(2, namaMK);
        ps.executeUpdate();
        populateList();
        ps.close();
    }
    
    private void insertUser(int nip, String password, String nama, String role) throws SQLException {
        String sql = "INSERT INTO " + role + " (nip, password, nama) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, nip);
        ps.setString(2, password);
        ps.setString(3, nama);
        ps.executeUpdate();
        populateList();
        ps.close();
    }
    
    private void hapusMK(String kode) throws SQLException{
        String sql = "DELETE FROM mata_kuliah WHERE kode_mk=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, kode);
        ps.executeUpdate();
        populateList();
        ps.close();
    }
    
    private void hapusUser(int nip, String kolom) throws SQLException{
        String sql = "DELETE FROM " + kolom + " WHERE nip=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, nip);
        ps.executeUpdate();
        populateList();
        ps.close();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelWelcome = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnAddMK = new javax.swing.JButton();
        btnAddDosen = new javax.swing.JButton();
        btnAddMhs = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listMK = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listDosen = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listMhs = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnDelMK = new javax.swing.JButton();
        btnDelDosen = new javax.swing.JButton();
        btnDelMhs = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelWelcome.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        labelWelcome.setText("SELAMAT DATANG, !");

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Silahkan pilih menu yang diinginkan");

        btnAddMK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnAddMK.setText("Tambah Mata Kuliah");
        btnAddMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMKActionPerformed(evt);
            }
        });

        btnAddDosen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnAddDosen.setText("Tambah Dosen");
        btnAddDosen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDosenActionPerformed(evt);
            }
        });

        btnAddMhs.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnAddMhs.setText("Tambah Mahasiswa");
        btnAddMhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMhsActionPerformed(evt);
            }
        });

        listMK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(listMK);

        listDosen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(listDosen);

        listMhs.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane3.setViewportView(listMhs);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Daftar Mata Kuliah");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Daftar Dosen");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Daftar Mahasiswa");

        btnDelMK.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnDelMK.setText("Hapus Mata Kuliah");
        btnDelMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelMKActionPerformed(evt);
            }
        });

        btnDelDosen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnDelDosen.setText("Hapus Dosen");
        btnDelDosen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelDosenActionPerformed(evt);
            }
        });

        btnDelMhs.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnDelMhs.setText("Hapus Mahasiswa");
        btnDelMhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelMhsActionPerformed(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnLogout.setText("LogOut");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelWelcome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLogout))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAddMK, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelMK, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAddDosen, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAddMhs, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnDelDosen, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelMhs, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelWelcome)
                    .addComponent(btnLogout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMK)
                    .addComponent(btnAddDosen)
                    .addComponent(btnAddMhs))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelMK)
                    .addComponent(btnDelMhs)
                    .addComponent(btnDelDosen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMKActionPerformed
        try{
            JPanel panel = new JPanel(new GridLayout(2, 2));
            panel.add(new JLabel("Kode Mata Kuliah:"));
            JTextField kode = new JTextField();
            panel.add(kode);
            panel.add(new JLabel("Nama Mata Kuliah:"));
            JTextField nama = new JTextField();
            panel.add(nama);
            int result = JOptionPane.showConfirmDialog(null, panel, "Masukkan Data Mata Kuliah",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                insertMK(kode.getText(), nama.getText());
                JOptionPane.showMessageDialog(null, "Data Mata Kuliah berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddMKActionPerformed

    private void btnDelMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelMKActionPerformed
        try{
            JPanel panel = new JPanel(new GridLayout(2, 2));
            panel.add(new JLabel("Kode Mata Kuliah:"));
            JTextField kode = new JTextField();
            panel.add(kode);
            int result = JOptionPane.showConfirmDialog(null, panel, "Masukkan Kode Mata Kuliah",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                hapusMK(kode.getText());
                JOptionPane.showMessageDialog(null, "Data Mata Kuliah berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDelMKActionPerformed

    private void btnAddDosenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDosenActionPerformed
        try{
            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("NIP:"));
            JTextField nipField = new JTextField();
            panel.add(nipField);
            panel.add(new JLabel("Password:"));
            JTextField passwordField = new JTextField();
            panel.add(passwordField);
            panel.add(new JLabel("Nama:"));
            JTextField namaField = new JTextField();
            panel.add(namaField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Masukkan Data Dosen",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int nip = Integer.parseInt(nipField.getText());
                insertUser(nip, passwordField.getText(), namaField.getText(), "dosen");
                JOptionPane.showMessageDialog(null, "Data Dosen berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddDosenActionPerformed

    private void btnDelDosenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelDosenActionPerformed
        try{
            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("NIP:"));
            JTextField nipField = new JTextField();
            panel.add(nipField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Masukkan NIP Dosen",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int nip = Integer.parseInt(nipField.getText());
                hapusUser(nip, "dosen");
                JOptionPane.showMessageDialog(null, "Data Dosen berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDelDosenActionPerformed

    private void btnAddMhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMhsActionPerformed
        try{
            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("NIP:"));
            JTextField nipField = new JTextField();
            panel.add(nipField);
            panel.add(new JLabel("Password:"));
            JTextField passwordField = new JTextField();
            panel.add(passwordField);
            panel.add(new JLabel("Nama:"));
            JTextField namaField = new JTextField();
            panel.add(namaField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Masukkan Data Mahasiswa",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int nip = Integer.parseInt(nipField.getText());
                insertUser(nip, passwordField.getText(), namaField.getText(), "mahasiswa");
                JOptionPane.showMessageDialog(null, "Data Mahasiswa berhasil ditambahkan", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddMhsActionPerformed

    private void btnDelMhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelMhsActionPerformed
        try{
            JPanel panel = new JPanel(new GridLayout(3, 2));
            panel.add(new JLabel("NIP:"));
            JTextField nipField = new JTextField();
            panel.add(nipField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Masukkan NIP Mahasiswa",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                int nip = Integer.parseInt(nipField.getText());
                hapusUser(nip, "mahasiswa");
                JOptionPane.showMessageDialog(null, "Data Mahasiswa berhasil dihapus", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDelMhsActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        try{
            dispose();
            new HomePage().setVisible(true);
        } catch(SQLException e) {
            e.printStackTrace();
        }       
    }//GEN-LAST:event_btnLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDosen;
    private javax.swing.JButton btnAddMK;
    private javax.swing.JButton btnAddMhs;
    private javax.swing.JButton btnDelDosen;
    private javax.swing.JButton btnDelMK;
    private javax.swing.JButton btnDelMhs;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelWelcome;
    private javax.swing.JList<String> listDosen;
    private javax.swing.JList<String> listMK;
    private javax.swing.JList<String> listMhs;
    // End of variables declaration//GEN-END:variables

}
