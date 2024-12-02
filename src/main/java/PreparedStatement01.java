import java.sql.*;

/*
PreparedStatement; önceden derlenmiş tekrar tekrar kullanılabilen
parametreli sorgular oluşturmamızı ve çalıştırmamızı sağlar.

PreparedStatement Statement ı extend eder(statementın gelişmiş halidir.)
 */

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
// Connection: Bu arayüz, bir veritabanı ile iletişim kurmak
        // için tüm yöntemleri içerir. Connection, yani veritabanıyla
        // yapılan tüm iletişim yalnızca bağlantı nesnesi aracılığıyla yapılır.
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt",
                "techpro", "pasword");


        //n03-ADIM:Statement oluşturma:SQL ifadelerini veritabanına
        //iletir ve çalıştırır
        Statement st = connection.createStatement();

        //ÖRNEK1: bolumler tablosunda Matematik bölümünün taban_puanı'nı 475 olarak güncelleyiniz.
        //String sql1 = "UPDATE bolumler SET taban_puani = 475 WHERE bolum ILIKE 'Matematik'";
        //int updated = st.executeUpdate(sql1);
        //System.out.println("UPDATED = " + updated);

       //Prepared Statement kullanarak bolumler tablosunda
        //Matematik bölümünün taban_puanı'nı 499 olarak güncelleyiniz.

      //parametreli sorguyu hazırlayalım,
       /* String sql1 = "UPDATE bolumler SET taban_puani =? where bolum ILIKE ?";
        PreparedStatement prst1 = connection.prepareStatement(sql1);//önceden derlenmiş dinamik bir sorgu oluşturur

        prst1.setInt(1,499);
        prst1.setString(2, "Matematik");
        //UPDATE bolumler SET taban_puani =499 where bolum ILIKE 'Matematik'
        prst1.executeUpdate();*/

        /*//Prepared Statement kullanarak bolumler tablosunda
        // Edebiyat bölümünün taban_puanı'nı 450 olarak güncelleyiniz.
        String sql2 = "UPDATE bolumler SET taban_puani =? where bolum ILIKE ?";
        PreparedStatement prst1 = connection.prepareStatement(sql2);//önceden derlenmiş dinamik bir sorgu oluşturur
        prst2.setInt(1,450);
        prst2.setString(2, "edebiyat");
        //UPDATE bolumler SET taban_puani =450 where bolum ILIKE 'edebiyat'
        prst2.executeUpdate();*/

        //Örnek 3:Prepared Statement kullanarak bolumler tablosuna
        // Yazılım Mühendisliği(id=5006,taban_puanı=475, kampus='Merkez') bölümünü ekleyiniz.

        String sql3 = "INSERT INTO bolumler (bolum_id, bolum, taban_puani, kampus) VALUES (?,?,?,?)";

        PreparedStatement prst3 = connection.prepareStatement(sql3);//önceden derlenmiş dinamik bir sorgu oluşturur
        prst3.setInt(1,5006);
        prst3.setString(2,"Yazılım Mühendisliği");
        prst3.setInt(3,475);
        prst3.setString(4,"Merkez");
        //INSERT INTO bolumler (id, bolum, taban_puani, kampus) VALUES (5006,'Yazılım Mühendisliği',475,'Merkez')
        prst3.executeUpdate();

        //Kullanıcıdan çıkış yapmak için bağlantıyı kapatıyoruz.
        connection.close();
        st.close();
        prst3.close();
        //prst1.close();
        //prst2.close();
        //prst3.close();
        System.out.println("Bağlantı kapatıldı.");


    }
    }
// //Ödev 1:Prepared Statement kullanarak developers tablosundan
//        // prog_lang C# olan kayıtları siliniz.(ALIŞTIRMA)
//
//        //Ödev 2:Prepared Statement kullanarak developers tablosundan
//        // prog_lang C++ olan kayıtları siliniz.(ALIŞTIRMA)