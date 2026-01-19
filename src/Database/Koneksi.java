package Database;

import java.sql.Connection;
// import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import data_mahasiswa.mahasiswa;

public class Koneksi {
    static String username = "java1";
    static String password = "java1";
    static String url = "jdbc:mysql://localhost:3306/mhsjava1";

    public static Connection getConn(){
        try{
            //ini adalah kode untuk koneksi
            Connection connection = DriverManager.getConnection(url, username, password);
            // jika proses tidak beramsalah maka tampilkan koneksi berhasil
            // System.out.println("Koneksi Berhasil....");
            return connection;
        } catch (SQLException e){
            // jika dalam proses koneksi terjadi masalah, 
            // tampilkan koneksi gagal
            System.err.println("Konesi Gagal...." + e.getMessage().toString());
        }
        return null;
    }

    // Method untuk memasukkan data mahasiswa ke database
    public static boolean insertMahasiswa(mahasiswa m) {
        String sql = "INSERT INTO mahasiswa (nama, nim, umur) VALUES (?, ?, ?)";
        try (Connection conn = getConn();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, m.getNama());
            pstmt.setString(2, m.getNim());
            pstmt.setInt(3, m.getUmur());
            
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Gagal insert data: " + e.getMessage());
            return false;
        }
    }

    // Method untuk mengambil semua data mahasiswa dari database
    public static ResultSet getAllMahasiswa() {
        String sql = "SELECT * FROM mahasiswa";
        try {
            Connection conn = getConn();
            if (conn != null) {
                Statement stmt = conn.createStatement();
                return stmt.executeQuery(sql);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data: " + e.getMessage());
        }
        return null;
    }

    // Method untuk update data mahasiswa
    public static boolean updateMahasiswa(String nim, mahasiswa m) {
        String sql = "UPDATE mahasiswa SET nama = ?, umur = ? WHERE nim = ?";
        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, m.getNama());
            pstmt.setInt(2, m.getUmur());
            pstmt.setString(3, nim);
            
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Gagal update data: " + e.getMessage());
            return false;
        }
    }

    // Method untuk delete data mahasiswa
    public static boolean deleteMahasiswa(String nim) {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        try (Connection conn = getConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nim);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("Gagal delete data: " + e.getMessage());
            return false;
        }
    }
}