import java.sql.*;

public class ExecuteUpdate02 {
    public static void main(String[] args) throws SQLException {
//n02-ADIM:bağlantıyı oluşturma: db url,kullanıcı adı,password

        // Connection: Bu arayüz, bir veritabanı ile iletişim kurmak
        // için tüm yöntemleri içerir. Connection, yani veritabanıyla
        // yapılan tüm iletişim yalnızca bağlantı nesnesi aracılığıyla yapılır.
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt",
                "techpro", "pasword");


        //n03-ADIM:Statement oluşturma:SQL ifadelerini veritabanına
        //iletir ve çalıştırır
        Statement st = connection.createStatement();


        //ÖRNEK2:developers tablosuna yeni bir developer ekleyiniz.

        /*String sql1 = "INSERT INTO developers(name,salary,prog_lang) VALUES('Jack Sarrow',5000,'Java') ";

        int inserted =st.executeUpdate(sql1);
        System.out.println("Eklenen kayıt sayısı : "  + inserted);
        ResultSet rs = st.executeQuery("SELECT * FROM developers");

        while (rs.next()) {

            System.out.println("id : "+rs.getInt("id")+" isim : " + rs.getString("name") + " maaş : " + rs.getDouble("salary"));
        }*/


        //ÖRNEK3:developers tablosundan id'si 9 olanı siliniz.

      /*  int deleted = st.executeUpdate("DELETE FROM developers WHERE id =9 ");
        System.out.println("Silinen kayıt sayısı :" + deleted);

        //tüm kayıtları görelim..
        ResultSet rs = st.executeQuery("SELECT * FROM developers");

        while (rs.next()) {

            System.out.println("id : " + rs.getInt("id") + " isim : " + rs.getString("name") + " maaş : " + rs.getDouble("salary"));*/

           //ÖRNEK4:developers tablosundan id'si 15 den büyük olanları siliniz.
            int deleted1 = st.executeUpdate("DELETE FROM developers WHERE id >14 ");
            System.out.println("Silinen kayıt sayısı :" + deleted1);

            //tüm kayıtları görelim..
            ResultSet rs = st.executeQuery("SELECT * FROM developers");

            while (rs.next()) {

                System.out.println("id : " + rs.getInt("id") + " isim : " + rs.getString("name") + " maaş : " + rs.getDouble("salary"));


            }

        }
    }

