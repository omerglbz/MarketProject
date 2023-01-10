package org.example;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    Market market = new Market();
    Kasa kasa = new Kasa();
    Musteri musteri = new Musteri();

    public User() {
        System.out.println("MARKETE HOSGELDINIZ");
    }
    public void userRunner() throws SQLException {
        System.out.println("Musteri iseniz 1\nDukkan sahibi iseniz 2");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if (choice == 1){
             musteri();
        } else if (choice == 2) {
            dukkanSahibi();
        } else {
            System.out.println("Yanlis sayi girdiniz");
            userRunner();
        }
    }

    public void musteri() throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.println("Isim giriniz");
        String isim = input.nextLine();

        System.out.println("Soyisim giriniz");
        String soyIsim = input.nextLine();

        System.out.println("Musteri girisi yapildi...\nUrunleri goruntulemek icin 1'e basiniz.");

        System.out.println("Kategori seciniz");
        market.kategoriGoruntule();
        String kategoriSecim = input.nextLine();

        System.out.println("Urun seciniz");
        market.urunGoruntule(kategoriSecim);
        String urunIsim = input.nextLine();

        System.out.println("Kac adet,kilo ?");
        int urunKilo = input.nextInt();

        int tutar = market.urunSatisTutar(urunIsim,urunKilo);
        musteri.faturaOlustur(isim,soyIsim,urunIsim,urunKilo,tutar);
        kasa.satilanUrun(isim,tutar,kategoriSecim,urunKilo);

        System.out.println("Urun almaya devam etmek icin 1,\nSatin almak icin 2");
        int choice = input.nextInt();

        while (choice == 1){
            System.out.println("Kategori seciniz");
            market.kategoriGoruntule();
            kategoriSecim = input.nextLine();
            market.urunGoruntule(kategoriSecim);

            System.out.println("Urun seciniz");
            urunIsim = input.nextLine();

            System.out.println("Kac kilo ?");
            urunKilo = input.nextInt();

            tutar = market.urunSatisTutar(urunIsim,urunKilo);
            musteri.faturaOlustur(isim,soyIsim,urunIsim,urunKilo,tutar);

            System.out.println("Urun almaya devam etmek icin 1,\nSatin almak icin 2");
            choice = input.nextInt();
        }
        musteri.faturaGoruntule(isim,soyIsim);
    }

    public void dukkanSahibi() throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.println("Market Islemleri \n1- Urun Ekle \n2- Urun Sil \n3- Satilan Urun Sayisi \n4- Toplam satis miktari \n5- Cikis");
        int choice = input.nextInt();

        if (choice == 1 ){
            System.out.println("Urunun ismini giriniz");
            input.nextLine();
            String urunIsim = input.nextLine();
            System.out.println("Urunun fiyatini giriniz");
            int fiyat = input.nextInt();
            System.out.println("Urunun kategorisini giriniz");
            input.nextLine();
            String urunKategori = input.nextLine();
            market.urunEKle(urunIsim,fiyat,urunKategori);
            dukkanSahibi();
        } else if (choice == 2) {
            System.out.println("Silinecek urunun ismini giriniz");
            String urunIsim = input.nextLine();
            market.urunSil(urunIsim);
            dukkanSahibi();
        } else if (choice == 3) {
            kasa.kategoriSatilanUrunSayisi();
            dukkanSahibi();
        } else if (choice == 4) {
            kasa.satisYapilanToplamTutar();
            dukkanSahibi();
        } else if (choice == 5) {
            System.out.println("Degisikler kaydedildi");
        } else {
            System.out.println("Yanlis sayi girdiniz");
            dukkanSahibi();
        }
    }
}
