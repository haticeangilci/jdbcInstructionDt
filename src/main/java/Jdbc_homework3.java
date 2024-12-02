

       //Ödev 3:Prepared Statement kullanarak developers tablosundan
        // prog_lang C++ olan kayıtları siliniz.(ALIŞTIRMA)

import java.sql.*;

public class Jdbc_homework3 {
    public static void main(String[] args) throws SQLException {
// Connection: Bu arayüz, bir veritabanı ile iletişim kurmak
        // için tüm yöntemleri içerir. Connection, yani veritabanıyla
        // yapılan tüm iletişim yalnızca bağlantı nesnesi aracılığıyla yapılır.
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt",
                "techpro", "pasword");


        //n03-ADIM:Statement oluşturma:SQL ifadelerini veritabanına
        //iletir ve çalıştırır
        Statement st = connection.createStatement();

        //Kullanıcıdan alınan C++ olan prog_lang kayıtlarını silmek için SQL sorgusu
        String sql1 = "DELETE FROM developers WHERE prog_lang = 'C++'";

        //1.yol//Statement oluşturma:SQL ifadelerini veritabanı
        //Statement st = connection.createStatement();
        //int deleted1 = st.executeUpdate(sql1);
        //System.out.println("Silinen kayıt sayısı : " + deleted1);
        //st.close();
        //connection.close();

        //2.yol//PreparedStatement oluşturma:SQL ifadelerini veritabanı
        PreparedStatement prst1 = connection.prepareStatement(sql1);
        int deleted1 = prst1.executeUpdate();
        System.out.println("Silinen kayıt sayısı : " + deleted1);



        //Kullanıcıdan çıkış yapmak için bağlantıyı kapatıyoruz.
        connection.close();
        st.close();
        prst1.close();


        System.out.println("Bağlantı kapatıldı.");


        }
    }





