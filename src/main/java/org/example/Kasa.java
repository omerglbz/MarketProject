package org.example;

import java.sql.*;

public class Kasa {
    DatabaseUtils databaseUtils = new DatabaseUtils();
    /*
    CREATE TABLE kasa(
    urun_isim VARCHAR(50),
    urun_fiyat int,
    urun_kategori varchar(50),
    kac_adet_kilo int
    )
     */
    public void satilanUrun(String urunAdi, int urun_fiyat, String urun_kategori, int kac_adet_kilo) throws SQLException {
        Connection con = databaseUtils.Db_connection();
        String sql = "INSERT INTO kasa VALUES (?,?,?,?)";
        PreparedStatement prS = con.prepareStatement(sql);
        prS.setString(1,urunAdi);
        prS.setInt(2,urun_fiyat);
        prS.setString(3,urun_kategori);
        prS.setInt(4,kac_adet_kilo);
        int isUpdate = prS.executeUpdate();
        if (isUpdate > 0) {
            System.out.println("EKleme basarili...");
        }
    }
    public void kategoriSatilanUrunSayisi() throws SQLException {
        Statement s = databaseUtils.db_statement();
        String sql = "SELECT urun_kategori,COUNT(urun_isim) FROM kasa";
        ResultSet rs = s.executeQuery(sql);
        while(rs.next()){
            System.out.println("Satilan urun sayisi: " + rs.getString(1) + rs.getInt(2));
        }
    }
    public void satisYapilanToplamTutar() throws SQLException {
        Statement s = databaseUtils.db_statement();
        String sql = "SELECT urun_fiyat FROM kasa";
        ResultSet rs = s.executeQuery(sql);
        int toplamTutar = 0;
        while (rs.next()) {
            toplamTutar += rs.getInt(1);
        }
        System.out.println("Simdiye kadar yapilan tum satislarin tutari : " + toplamTutar);
    }

}
