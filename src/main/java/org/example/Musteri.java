package org.example;

import java.sql.*;

public class Musteri {
    DatabaseUtils databaseUtils = new DatabaseUtils();
    /*
    CREATE TABLE musteri(
    isim VARCHAR(30),
    soyisim VARCHAR(30),
    urun_adi VARCHAR(30),
    kilo int,
    tutar int
    );
     */
    public void faturaOlustur(String isim, String soyIsim, String urun_adi, int kilo, int tutar) throws SQLException {
        Connection con = databaseUtils.Db_connection();
        String sql =  "INSERT INTO musteri VALUES(?,?,?,?,?)";
        PreparedStatement prS = con.prepareStatement(sql);
        prS.setString(1,isim);
        prS.setString(2,soyIsim);
        prS.setString(3,urun_adi);
        prS.setInt(4,kilo);
        prS.setInt(5,tutar);
        int isUpdate = prS.executeUpdate();
        if (isUpdate > 0){
            System.out.println("Fatura ekleme basarili...");
        }
    }
    public void faturaGoruntule(String isim, String soyIsim) throws SQLException {
        Statement st = databaseUtils.db_statement();
        String sql = "SELECT isim, soyisim FROM musteri WHERE isim = '" + isim +"' AND isim = '" + soyIsim + "'";
        ResultSet rS = st.executeQuery(sql);
        while (rS.next()){
            System.out.println("Faturaniz: " + rS.getString(1) + rS.getString(2) + rS.getString(3) + rS.getInt(4)
                    + rS.getInt(5));
        }
    }
}
