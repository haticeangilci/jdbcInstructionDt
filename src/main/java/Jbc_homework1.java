
//1-Gerekli Kütüphanelerin İçe Aktarılması:
import java.sql.*;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  */
////ÖDEV:Puanı taban puanlarının ortalamasından yüksek olan öğrencilerin isim ve puanlarını listeleyiniz.
//2-Ana Sınıf ve Yöntem:
public class Jbc_homework1 {
    public static void main(String[] args) throws SQLException {

        //3--sql sorgusu
        String sql4 = "SELECT name,score from students where score >(SELECT AVG(score) from students)";

        //4--Veritabanı Bağlantısı:
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt",
                "techpro", "pasword");//Burada, PostgreSQL veritabanına bağlanmak için DriverManager.getConnection kullanılıyor. Bağlantı URL'si, veritabanı adı, kullanıcı adı ve şifre güncel olmalıdır.

             //5-PreparedStatement Kullanımı:
             PreparedStatement pstmt = conn.prepareStatement(sql4);//PreparedStatement nesnesi oluşturularak SQL sorgusu çalıştırılır. Bu sayede sorgunun güvenliği artırılmış olur (SQL enjeksiyonuna karşı koruma sağlar).

             //6-Sonuç Kümesi (ResultSet):
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("Taban puanın ortalamasından yüksek puan alan öğrenciler:");

           //7-while (rs.next()) {
            //    String name = rs.getString("name");
            //    int score = rs.getInt("score");
            //    System.out.println("İsim: " + name + ", Puan: " + score);
            //}
            while (rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("score");
                System.out.println("İsim: " + name + ", Puan: " + score);
            }
        //8- Hata Yönetimi
        } catch (SQLException e) {
            e.printStackTrace(); // Hata durumunda hata mesajını yazdır
        }

        //9-Başarı mesajı
        System.out.println("success");
    }

}
