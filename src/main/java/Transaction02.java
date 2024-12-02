import java.sql.*;

public class Transaction02 {
    public static void main(String[] args) throws SQLException {
// Connection: Bu arayüz, bir veritabanı ile iletişim kurmak
        // için tüm yöntemleri içerir. Connection, yani veritabanıyla
        // yapılan tüm iletişim yalnızca bağlantı nesnesi aracılığıyla yapılır.
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_dt",
                "techpro", "pasword");


        //n03-ADIM:Statement oluşturma:SQL ifadelerini veritabanına
        //iletir ve çalıştırır
        Statement st = connection.createStatement();

        st.execute("CREATE TABLE IF NOT EXISTS hesaplar2 (hesap_no INT UNIQUE, isim VARCHAR(50), bakiye REAL)");

        String sql1 = "INSERT INTO hesaplar2 VALUES (?,?,?) ";
        PreparedStatement prst1 = connection.prepareStatement(sql1);
        prst1.setInt(1, 1234);
        prst1.setString(2, "Fred");
        prst1.setDouble(3, 9000);
        prst1.executeUpdate();

        prst1.setInt(1, 5678);
        prst1.setString(2, "Barnie");
        prst1.setDouble(3, 6000);
        prst1.executeUpdate();

        //TASK: hesap no:1234 ten hesap no:5678 e 1000$ para transferi olsun.
        //ayrı iki hesabın update işlemlerini birbiri ile bağımlı old. için
        //tek bir transaction altında toplayalım

        try{
            connection.setAutoCommit(false);
            //transaction yönetimi bizde, yeni bir transaction başlattık.

            //1.yol
            String sql = "UPDATE hesaplar2 SET bakiye=bakiye+? WHERE hesap_no=?";
            PreparedStatement prst2 = connection.prepareStatement(sql);
            //1-işlem:gönderen hesabın bakiyesi azalacak
            prst2.setDouble(1, -1000);
            prst2.setInt(2, 1234);
            prst2.executeUpdate();

            //Savepoint sp=connection.setSavepoint();
            //connection.rollback(sp);
            //işlemler arasında kayıt nok. belirleyip daha sonra
            //belirli durumda rollback ile bu noktoya dönülebilir.



            //sistemde hata oluştu kabul edelim...
            if (true) {
                throw new RuntimeException();

            }

            //2-işlem:alıcı hesabın bakiyesi artacak
            prst2.setDouble(1, 1000);
            prst2.setInt(2, 5678);
            prst2.executeUpdate();
            prst2.close();

            //tüm işlemler başarılı
            System.out.println("İşlem başarılı");
            connection.commit();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Sistemde hata oluştu...");
            //connection.rollback();//işlemlerden en az biri başarısız
            //o halde tüm işlemleri geri al
            //en son commit yapılan duruma döner
        }
/*//2.yol
        //1.işlem
        String sql2 = "UPDATE hesaplar SET bakiye=bakiye-1000 WHERE hesap_no=1234";
        st.executeUpdate(sql2);

        //2.işlem
        String sql3 = "UPDATE hesaplar SET bakiye=bakiye+1000 WHERE hesap_no=5678";
        st.executeUpdate(sql3);

        //Transaction başarılı olduğunu varsayıyoruz.
        //Bir veya birden fazla işlemi bir araya getirerek tek bir transaction başlatabiliriz.
        //Bu işlemlerden en az 1 i başarısız olduğunda yada herhangi bir koşulda
        //transactionı ROLLBACK ile geri alabiliriz
        connection.commit();

        //n04-ADIM:Kaynakları kapatma*/

    }
}
