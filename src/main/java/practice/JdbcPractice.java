package practice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPractice {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //0-adım:Veri tabanı sürücü sınıfını kaydetme
        Class.forName("org.postgresql.Driver");
        //0-adım:Bağlantıyı oluşturma
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt","techpro","pasword");
        //0-adım:Sorgu çalıştırma
        //statement veya preparedstatement
        Statement st = con.createStatement();
        //0-adım:kaynakları kapatma


       //01-adım:countries tablosunda, ülke kodu 'TR' olan ülkenin telefon kodunu bulun.
        String sql1 = "SELECT phone_code FROM countries WHERE country_code='TR'";
        Statement st1 = con.createStatement();
        ResultSet rs1 = st.executeQuery(sql1);
        if (rs1.next()){
            System.out.println("Telefon kodu : "+rs1.getInt("phone_code"));
        }
        //while (rs1.next()){//if yerine eğer sorguladığımız değer kesin ise while kullanabiliriz.
        //   ResultSet rs1 = st.executeQuery(sql1);
        //}

        System.out.println("--------------------------------------------------------------2");
        //n02: ogrenciler tablosunda, puanı 500’den büyük olan öğrencilerin isimlerini
        // ve şehirlerini getirin.
        String sql2 = "SELECT isim,sehir FROM ogrenciler WHERE puan>?";
        PreparedStatement pst = con.prepareStatement(sql2);
        pst.setInt(1,500);
        ResultSet rs2 = pst.executeQuery();
        while (rs2.next()){
        System.out.println("Isim : "+rs2.getString(1)+
        " --- sehir : "+rs2.getString(2));
        }
        System.out.println("--------------------------------------------------------------3");
        //n03: bolumler tablosuna yeni bir bölüm ekleyin.
        // Bölüm adı 'Mimarlık', taban puanı 510, kampüsü 'Hisar' olsun.
        String sql3 = "INSERT INTO bolumler (bolum,taban_puani,kampus) VALUES (?,?,?)";
        PreparedStatement pst3 = con.prepareStatement(sql3);
        pst3.setString(1,"Mimarlık");
        pst3.setInt(2,510);
        pst3.setString(3,"Hisar");
        pst3.executeUpdate();
        System.out.println("Bölüm eklendi");

        System.out.println("--------------------------------------------------------------4");
        //n04: bolumler tablosundaki Mimarlık bölümünün taban puanını 520 olarak güncelleyin.
        String sql4 = "UPDATE bolumler SET taban_puani=? WHERE bolum=?";
        PreparedStatement pst4 = con.prepareStatement(sql4);
        pst4.setInt(1,520);
        pst4.setString(2,"Mimarlık");
        pst4.executeUpdate();
        System.out.println("Bölüm güncellendi");

        System.out.println("---------------------------------------------------------5");

        String sql5="SELECT name FROM developers WHERE salary>5000";
        ResultSet rs5=st.executeQuery(sql5);

        List<String> names=new ArrayList<>();

        while (rs5.next()){
           names.add(rs5.getString(1));
        }
        System.out.println(names);

        System.out.println("---------------------------------------------------------6");
        //n05. ogrenciler tablosundaki her öğrenciye 50 puan bonus ekleyin.
        PreparedStatement pst6=con.prepareStatement("UPDATE ogrenciler SET puan=puan+?");
        pst6.setInt(1,50);
        int updated = pst6.executeUpdate();
        //String sql6="UPDATE ogrenciler SET puan=puan+50";
        //st.executeUpdate(sql6);
        System.out.println("Ogrencilerin puanları güncellendi");

        System.out.println("---------------------------------------------------------7");

        //n06. developers tablosunda 'Java' programlama diliyle çalışan
        // en yüksek maaşlı geliştiriciyi bulun.
        String sql7="SELECT name FROM developers WHERE prog_lang='Java' ORDER BY salary DESC LIMIT 1";
        ResultSet rs7=st.executeQuery(sql7);

        if (rs7.next()){
            System.out.println("En yüksek maaşlı Java programlama diliyle çalışan geliştirici : "+rs5.getString(1));
        }
        System.out.println("---------------------------------------------------------8");

        //n07. countries tablosundaki toplam ülke sayısını bulun.
        String sql8="SELECT COUNT(*) AS ulke_sayisi FROM countries";
        ResultSet rs8=st.executeQuery(sql8);

        if (rs8.next()){
            System.out.println("Toplam ülke sayısı : "+rs8.getInt("ulke_sayisi"));
        }
        System.out.println("---------------------------------------------------------9");
        //n08. developers tablosunda maaşı en düşük olan geliştiricinin bilgilerini silin.
        String sql9="DELETE FROM developers WHERE salary=(SELECT MIN(salary) FROM developers)";
        int deleted =st.executeUpdate(sql9);
        System.out.println("Maaşı en düşük olan geliştirici silindi"+ deleted);

        System.out.println("---------------------------------------------------------10");
        //n09. ogrenciler ve bolumler tablolarını birleştirerek,
        // öğrencilerin isimlerini ve okudukları bölümün kampüslerini getirin.
        String sql10="SELECT o.isim, b.bolum, b.kampus FROM ogrenciler o INNER JOIN bolumler b ON o.bolum=b.bolum";
        ResultSet rs10=st.executeQuery(sql10);


        while (rs10.next()){
            System.out.println("Isim : "+rs10.getString(1)+
            " --- Bölüm : "+rs10.getString(2)+
            " --- Kampüs : "+rs10.getString(3));        }
        System.out.println("---------------------------------------------------------11");
        //10. ogrenciler tablosunda her şehirdeki öğrenci sayısını bulun
        // ve azalan sırayla listeleyin.
        String sql11="SELECT sehir, COUNT(*) AS ogrenci_sayisi FROM ogrenciler GROUP BY sehir ORDER BY ogrenci_sayisi DESC";
        ResultSet rs11=st.executeQuery(sql11);

        while (rs11.next()){
            System.out.println("sehir : "+rs11.getString(1)+
            " --- Öğrenci Sayısı : "+rs11.getInt("ogrenci_sayisi"));        }
        System.out.println("---------------------------------------------------------12");








        }



        //n00:kaynakları kapatalım







        //ödev 1: developers tablosunda her programlama dili için ortalama maaşı bulun
        // ve sonuçları büyükten küçüğe sıralayın.

        //ödev 2: developers tablosunda programlama dillerine göre geliştirici sayısını
        // ve toplam maaşı hesaplayın. Sonuçları geliştirici sayısına göre azalan sırada sıralayın.









    }

