package org.example;

import java.sql.*;

public class Market {
    /*
    CREATE TABLE market(
    urun_isim VARCHAR(50),
    urun_fiyat int,
    urun_kategori varchar(50)
    )
    CREATE TABLE kasa(
    urun_isim VARCHAR(50),
    urun_fiyat int,
    urun_kategori varchar(50),
    kac_adet_kilo int
    )
     */
    DatabaseUtils databaseUtils = new DatabaseUtils();
    public void urunEKle(String urun_isim,int urun_fiyat,String urun_kategori) throws SQLException {
        Connection con = databaseUtils.Db_connection();
        String sql = "INSERT INTO market VALUES (?,?,?)";
        PreparedStatement prS = con.prepareStatement(sql);
        prS.setString(1,urun_isim);
        prS.setInt(2,urun_fiyat);
        prS.setString(3,urun_kategori);
        int isUpdate = prS.executeUpdate();
        if (isUpdate > 0){
            System.out.println("EKleme basarili...");
        }
    }
    public void urunSil(String urun_ismi) throws SQLException {
        Statement st = databaseUtils.db_statement();
        String sql = "DELETE FROM market WHERE urun_isim = '"+ urun_ismi + "'";
        int isUpdate = st.executeUpdate(sql);
        if (isUpdate > 0){
            System.out.println("EKleme basarili...");
        }
    }
    public void kategoriGoruntule() throws SQLException {
        Statement st = databaseUtils.db_statement();
        String sql = "SELECT urun_kategori FROM market";
        ResultSet rs =  st.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString(1));
        }
    }

    public int urunSatisTutar(String urun_adi, int kac_kilo) throws SQLException {
        Statement st = databaseUtils.db_statement();
        String sql = "SELECT urun_fiyat FROM market WHERE urun_isim = '" + urun_adi + "'";
        ResultSet rs = st.executeQuery(sql);
        int fiyat = 0;
        while (rs.next()){
           fiyat = rs.getInt(1);
        }
        fiyat *= kac_kilo;
        return fiyat;
    }
    public void urunGoruntule(String kategori) throws SQLException {
        Statement st = databaseUtils.db_statement();
        String sql = "SELECT * FROM market WHERE urun_kategori = '" + kategori + "'";
        ResultSet rs =  st.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getString(3));
        }
    }
}
