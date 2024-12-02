
// //Ödev 2:Prepared Statement kullanarak developers tablosundan
//        // prog_lang C# olan kayıtları siliniz.(ALIŞTIRMA)
//


import java.sql.*;

public class Jdbc_homework2 {
    public static void main(String[] args) throws SQLException {
// Connection: Bu arayüz, bir veritabanı ile iletişim kurmak
        // için tüm yöntemleri içerir. Connection, yani veritabanıyla
        // yapılan tüm iletişim yalnızca bağlantı nesnesi aracılığıyla yapılır.
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt",
                "techpro", "pasword");


        //n03-ADIM:Statement oluşturma:SQL ifadelerini veritabanına
        //iletir ve çalıştırır
        Statement st = connection.createStatement();
        String sql1 = "DELETE FROM developers WHERE prog_lang = 'C#'";
        PreparedStatement prst1 = connection.prepareStatement(sql1);//önceden derlenmiş dinamik bir sorgu oluşturur
        int deleted1 = prst1.executeUpdate();

        //Kullanıcıdan çıkış yapmak için bağlantıyı kapatıyoruz.
        connection.close();
        st.close();
        prst1.close();


        System.out.println("Bağlantı kapatıldı.");


        }
    }




        // //Ödev 2:Prepared Statement kullanarak developers tablosundan
       // prog_lang C# olan kayıtları siliniz.(ALIŞTIRMA)
        //1.yol
        //String sql1 = "DELETE FROM developers WHERE prog_lang = 'C#'";
        //Statement st = connection.createStatement();
        //int deleted1 = st.executeUpdate(sql1);

        //2.yol
        //PreparedStatement prst1 = connection.prepareStatement(sql1);
        //int deleted1 = prst1.executeUpdate();

        //3.yol
        //Statement st = connection.createStatement();
        //int deleted1 = st.executeUpdate("DELETE FROM developers WHERE prog_lang = 'C#'");
        //PreparedStatement prst1 = connection.prepareStatement(deleted1);//önceden derlenmiş dinamik bir sorgu oluşturur
        //int deleted1 = prst1.executeUpdate();



